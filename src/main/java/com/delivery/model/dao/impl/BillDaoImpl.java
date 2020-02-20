package com.delivery.model.dao.impl;

import com.delivery.model.dao.BillDao;
import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.impl.core.AbstractDaoImpl;
import com.delivery.model.entity.Bill;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;
import com.delivery.model.exeption.SqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl extends AbstractDaoImpl<Bill> implements BillDao {
    private static final String FIND_BY_ID = "SELECT * FROM bill WHERE id=? LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE FROM bill WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM bill ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM bill LIMIT ?, ?";
    private static final String SAVE_ENTITY = "INSERT INTO bill (id, orders, payment, delivery, size, weight, material, total_price) VALUES (?, ?, ?, ?, ?, ?, ? , ? )";
    private static final String UPDATE_ALL = "UPDATE bill SET  orders = ? , payment = ? , delivery = ? , size = ? , weight = ? , material = ?, total_price = ? WHERE id = ?";
    private static final String FIND_ALL_FOR_USER = "SELECT * from bill where ";
    private static final String BILLS_FOR_USER_LIMIT = "SELECT bill.id as id , bill.size as size, bill.material as material , bill.weight as weight , bill.orders as orders , bill.payment as payment , bill.delivery as delivery , bill.total_price as total_price  from bill inner join  orders o on bill.orders = o.id join user u on o.user = u.id where u.email = ? LIMIT ?, ?";
    private static final String BILLS_FOR_USER = "SELECT bill.id as id , bill.size as size, bill.material as material , bill.weight as weight , bill.orders as orders , bill.payment as payment , bill.delivery as delivery , bill.total_price as total_price  from bill inner join  orders o on bill.orders = o.id join user u on o.user = u.id where u.email = ? ";
    private static final String BILL_PAID = "UPDATE bill SET payment = true WHERE id = ?";
    public BillDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }

    @Override
    protected Bill mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Bill.builder()
                .withId(resultSet.getInt("id"))
                .withOrder(new OrderDaoImpl(this.connector).findById(resultSet.getInt("orders")).get())
                .withPayment(resultSet.getBoolean("payment"))
                .withDeliveryType(DeliveryType.valueOf(resultSet.getString("delivery")))
                .withSize(Size.valueOf(resultSet.getString("size")))
                .withWeight(Weight.valueOf(resultSet.getString("weight")))
                .withShipmentMaterial(Material.valueOf(resultSet.getString("material")))
                .withTotalValue(resultSet.getFloat("total_price"))
                .build();
    }


    @Override
    protected void paramSet(Bill entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getOrder().getId());
        preparedStatement.setBoolean(3, entity.isPayment());
        preparedStatement.setString(4, entity.getDeliveryType().toString());
        preparedStatement.setString(5, entity.getSize().toString());
        preparedStatement.setString(6, entity.getWeight().toString());
        preparedStatement.setString(7, entity.getMaterial().toString());
        preparedStatement.setFloat(8, entity.getTotalValue());
    }

    @Override
    protected void updateStatementSet(Bill entity, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setInt(1, entity.getOrder().getId());
        preparedStatement.setBoolean(2, entity.isPayment());
        preparedStatement.setString(3, entity.getDeliveryType().toString());
        preparedStatement.setString(4, entity.getSize().toString());
        preparedStatement.setString(5, entity.getWeight().toString());
        preparedStatement.setString(6, entity.getMaterial().toString());
        preparedStatement.setFloat(7, entity.getTotalValue());
        preparedStatement.setInt(8, entity.getId());
    }

    @Override
    public int countUserBill(String email){
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(BILLS_FOR_USER)) {
            preparedStatement.setString(1, email);

            return this.resultSet(preparedStatement).size();

        } catch (SQLException e) {
            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }

    @Override
    public void paymentChange(int id){
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(BILL_PAID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception in paymentChange method", e);
            throw new SqlRuntimeException("Can't update payment ", e);
        }

    }

    @Override
    public List<Bill> findAllBillsForUser(int page, int itemsPerPage, String email) {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(BILLS_FOR_USER_LIMIT)) {
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, (page - 1) * itemsPerPage);
            preparedStatement.setInt(3, itemsPerPage);

            return this.resultSet(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }

}
