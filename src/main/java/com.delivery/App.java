package com.delivery;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.impl.entity.UserDaoImpl;
import com.delivery.entity.enums.shipment.Material;
import com.delivery.entity.enums.shipment.Size;
import com.delivery.service.PasswordEncryption;
import com.delivery.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {

    static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        DataBaseConnector connector = new DataBaseConnector("delivery_schema_properties");
        UserDaoImpl userCrudDao = new UserDaoImpl(connector);
        userCrudDao.findByEmail("om");
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(Material.GLASS);
        System.out.println(Size.ENORMOUS);
        System.out.println(userService.login("olena@gmail.com", "1").toString());

        PasswordEncryption passwordEncryption = new PasswordEncryption();


    }
}
