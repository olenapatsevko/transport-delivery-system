package com.delivery.model.service.validator;

import com.delivery.model.entity.User;
import com.delivery.model.exeption.ValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserValidatorTest {

    private UserValidator userValidator = new UserValidator();


    @Test
    public void validationEmailTrue(){
        assertTrue(userValidator.validate("email@gmai.com", "email"));

    }

    @Test(expected = ValidationException.class)
    public void  testUserWithException(){
        userValidator.validate(User.builder().withEmail("olena@com").withPassword("Qdfdklgj456").build());
        userValidator.validate("email", "lol");
    }

}