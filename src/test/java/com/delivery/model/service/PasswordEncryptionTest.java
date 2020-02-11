package com.delivery.model.service;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PasswordEncryptionTest {


    @Test
    public void passwordEncryption(){
        assertEquals("Y�\u000E�\u001F\u0017����B[�jy0", new PasswordEncryption().encrypt("Qwe12345"));
    }

}