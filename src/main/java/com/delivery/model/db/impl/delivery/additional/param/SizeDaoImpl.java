package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.core.AbstractUnchangeableCrudDaoImpl;
import com.delivery.model.entity.bill.Size;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SizeDaoImpl extends AbstractUnchangeableCrudDaoImpl<Size> {
    private static final String FIND_BY_ID = "SELECT * FROM size WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM size WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM size ";
    private static final String FIND_ALL = "SELECT * FROM size";
    private static final String FIND_BY_NAME = "SELECT * from size where name = ?";

    public SizeDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public Size mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Size.valueOf(resultset.getString("name"));
    }

    @Override
    public Optional<Size> findById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    public Size findByName(String name) {
        Optional<Size> size = findByParam(name, FIND_BY_NAME, STRING_PARAM_SETTER);
        return size.orElse(null);
    }
}
