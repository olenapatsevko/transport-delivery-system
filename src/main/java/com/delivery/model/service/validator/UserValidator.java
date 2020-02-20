package com.delivery.model.service.validator;

import com.delivery.model.entity.User;
import com.delivery.model.exeption.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("squid:S1171")
public class UserValidator {
    private  StringReg stringReg = new StringReg();

    public void validate(User user) {
        if (!(stringReg.validate(user, User::getEmail, "email") && stringReg.validate(user, User::getPassword, "password"))) {
            throw new ValidationException("User Validation failed: "+user.toString() );
        }

    }

    public boolean validate(String value, String key){
        return stringReg.validate(value, key);
    }

    private class StringReg {
         Map<String, String> map = new HashMap<>();
         Pattern pattern;
         Matcher matcher;
         {
            map.put("email", "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
            map.put("password", "(?=.*[a-z])(?=.*[A-Z]).{6,30}");
            map.put("address", ".{6,200}");
         }

         boolean validate(User user, Function<User, String> function, String key) {
            pattern = Pattern.compile(map.get(key));
            matcher = pattern.matcher(function.apply(user));
            return matcher.matches();
        }

         boolean validate(String value, String key) {
            pattern = Pattern.compile(map.get(key));
            matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }
}
