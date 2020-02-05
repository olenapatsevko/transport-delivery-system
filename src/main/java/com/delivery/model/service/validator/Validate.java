package com.delivery.model.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Validate<T> {


    Logger LOGGER = LogManager.getLogger(Validate.class);

    void validate(T entity) ;

    boolean validate(String value, String key) ;



    // regular expression for login and password
    //cover with tests
}
