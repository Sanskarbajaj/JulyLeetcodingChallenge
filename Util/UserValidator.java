package com.example.libraryManagementSpring.Util;

import com.example.libraryManagementSpring.DataAccessLayer.User;

public class UserValidator {
    public static boolean isValidUser(User user){
        if(user.getEmail()==null || user.getEmail()=="")
            return false;
        else return true;}
}
