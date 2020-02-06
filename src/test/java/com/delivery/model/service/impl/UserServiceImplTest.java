package com.delivery.model.service.impl;

import com.delivery.model.db.impl.UserDaoImpl;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import com.delivery.model.service.validator.UserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserDaoImpl userDao;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin(){
        when(userDao.loginUser(anyString(), anyString())).thenReturn(Optional.of(User.builder().build()));
        when(userValidator.validate(any(), any())).thenReturn(true);
        Assert.assertEquals(User.builder().build().getId(), userService.login("", "").getId());

    }

    @Test
    public void testRegister(){
        doNothing().when(userDao).save(any(User.class));
       doNothing().when(userValidator).validate(any(User.class));
       userService.register(User.builder().build());
       verify(userValidator).validate(eq(User.builder().build()));
       verify(userDao).save(eq(User.builder().build()));

    }
}