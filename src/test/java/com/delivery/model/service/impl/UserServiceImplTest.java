package com.delivery.model.service.impl;

import com.delivery.model.dao.impl.UserDaoImpl;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import com.delivery.model.mapper.Mapper;
import com.delivery.model.service.PasswordEncryption;
import com.delivery.model.service.validator.UserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    Optional<User> USER = Optional.of(User.builder().withPassword("123").build());
    @Mock
    private UserDaoImpl userDao;
    @Mock
    private UserValidator userValidator;
    @Mock
    private Mapper<User, UserDomain> userMapper;
    @Mock
    private PasswordEncryption passwordEncryption;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testLogin() {
        when(userDao.findByEmail(any())).thenReturn(USER);
        when(userValidator.validate(any(), any())).thenReturn(true);
        when(passwordEncryption.encrypt(any())).thenReturn("123");
        userService.login("", "");
        verify(passwordEncryption).encrypt("");
        verify(userDao,times(1)).findByEmail("");
        verify(passwordEncryption).encrypt("");
    }

    @Test
    public void testRegister(){
        when(userDao.findByEmail(any())).thenReturn(Optional.empty());
        when(userMapper.mapToEntity(any(UserDomain.class))).thenReturn(USER.get());
        doNothing().when(userDao).save(USER.get());
       doNothing().when(userValidator).validate(any(User.class));
       userService.register(UserDomain.builder().withEmail("").build());
       verify(userValidator).validate(USER.get());
       verify(userDao).save(eq(User.builder().build()));

    }
}