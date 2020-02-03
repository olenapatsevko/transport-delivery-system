package com.delivery.service.validator;


import com.delivery.entity.User;
import com.delivery.exeption.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("squid:S1171")
public class UserValidator implements Validate<User> {

    @Override
    public void validate(User user) {
        if (!(StringReg.validate(user, User::getEmail, "email") && StringReg.validate(user, User::getPassword, "password"))) {
            throw new ValidationException("User Validation failed: "+user.toString() );
        }
    }

//    public void validate(String value, String key) {
//        if (!(StringReg.validate(value, key))) {
//            LOGGER.warn("Validation failed: " + value + " " + key);
//            throw new ValidationException("Validation Failed");
//        }
//    }

    public boolean validate(String value , String key){
        return StringReg.validate(value, key);
    }


    private static class StringReg {
        static Map<String, String> map = new HashMap<>();
        static Pattern pattern;
        static Matcher matcher;

        static {
            map.put("email", "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
            map.put("password", "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
        }

        static boolean validate(User user, Function<User, String> function, String key) {
            pattern = Pattern.compile(map.get(key));
            matcher = pattern.matcher(function.apply(user));
            return matcher.matches();
        }

        static boolean validate(String value, String key) {
            pattern = Pattern.compile(map.get(key));
            matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }


}
