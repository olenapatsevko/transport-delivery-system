package com.delivery.controller.injector;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.*;
import com.delivery.model.db.impl.delivery.additional.param.*;
import com.delivery.model.mapper.*;
import com.delivery.model.service.PasswordEncryption;
import com.delivery.model.service.UserService;
import com.delivery.model.service.impl.DeliveryCalculationImpl;
import com.delivery.model.service.impl.UserServiceImpl;
import com.delivery.model.service.validator.UserValidator;

public class ApplicationInjector {
    private static final DataBaseConnector DATA_BASE_CONNECTOR = new DataBaseConnector();
    private static final UserValidator USER_VALIDATOR = new UserValidator();
    public static final PasswordEncryption PASSWORD_ENCRYPTION = new PasswordEncryption();

    //dao
    private static final UserDaoImpl USER_DAO = new UserDaoImpl(DATA_BASE_CONNECTOR);
    private static final PlaceDaoImpl PLACE_DAO = new PlaceDaoImpl(DATA_BASE_CONNECTOR);
    public static final OrderDaoImpl ORDER_DAO = new OrderDaoImpl(DATA_BASE_CONNECTOR);
    public static final BillDaoImpl BILL_DAO = new BillDaoImpl(DATA_BASE_CONNECTOR);
    public static final ShipmentDaoImpl SHIPMENT_DAO = new ShipmentDaoImpl(DATA_BASE_CONNECTOR);
    public static final DeliveryTypeDaoImplImpl DELIVERY_TYPE_DAO = new DeliveryTypeDaoImplImpl(DATA_BASE_CONNECTOR);
    public static final MaterialDaoImpl MATERIAL_DAO = new MaterialDaoImpl(DATA_BASE_CONNECTOR);
    public static final OrderStatusDaoImpl STATUS_DAO = new OrderStatusDaoImpl(DATA_BASE_CONNECTOR);
    public static final SizeDaoImpl SIZE_DAO = new SizeDaoImpl(DATA_BASE_CONNECTOR);
    public static final WeightDaoImpl WEIGHT_DAO = new WeightDaoImpl(DATA_BASE_CONNECTOR);

   //mapper
    public static final UserMapper USER_MAPPER = new UserMapper(PASSWORD_ENCRYPTION);
    public static final PlaceMapper PLACE_MAPPER = new PlaceMapper();
    public static final OrderMapper ORDER_MAPPER = new OrderMapper();
    public static final ShipmentMapper SHIPMENT_MAPPER = new ShipmentMapper();
    public static final BillMapper BILL_MAPPER = new BillMapper();

    //services
    public static final DeliveryCalculationImpl DELIVERY_CALCULATION = new DeliveryCalculationImpl(PLACE_MAPPER,PLACE_DAO);
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER, PASSWORD_ENCRYPTION);

    private static final ApplicationInjector INSTANCE = new ApplicationInjector();

    private ApplicationInjector() {
    }

    public static ApplicationInjector getInstance() {
        return INSTANCE;
    }


    public static DataBaseConnector getDataBaseConnector() {
        return DATA_BASE_CONNECTOR;
    }

    public static UserValidator getUserValidator() {
        return USER_VALIDATOR;
    }

    public static PasswordEncryption getPasswordEncryption() {
        return PASSWORD_ENCRYPTION;
    }

    public static UserDaoImpl getUserDao() {
        return USER_DAO;
    }

    public static PlaceDaoImpl getPlaceDao() {
        return PLACE_DAO;
    }

    public static OrderDaoImpl getOrderDao() {
        return ORDER_DAO;
    }

    public static BillDaoImpl getBillDao() {
        return BILL_DAO;
    }

    public static ShipmentDaoImpl getShipmentDao() {
        return SHIPMENT_DAO;
    }

    public static DeliveryTypeDaoImplImpl getDeliveryTypeDao() {
        return DELIVERY_TYPE_DAO;
    }

    public static MaterialDaoImpl getMaterialDao() {
        return MATERIAL_DAO;
    }

    public static OrderStatusDaoImpl getStatusDao() {
        return STATUS_DAO;
    }

    public static SizeDaoImpl getSizeDao() {
        return SIZE_DAO;
    }

    public static WeightDaoImpl getWeightDao() {
        return WEIGHT_DAO;
    }

    public static UserMapper getUserMapper() {
        return USER_MAPPER;
    }

    public static PlaceMapper getPlaceMapper() {
        return PLACE_MAPPER;
    }

    public static OrderMapper getOrderMapper() {
        return ORDER_MAPPER;
    }

    public static ShipmentMapper getShipmentMapper() {
        return SHIPMENT_MAPPER;
    }

    public static BillMapper getBillMapper() {
        return BILL_MAPPER;
    }

    public static DeliveryCalculationImpl getDeliveryCalculation() {
        return DELIVERY_CALCULATION;
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }
}
