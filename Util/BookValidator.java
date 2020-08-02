package com.example.libraryManagementSpring.Util;

import com.example.libraryManagementSpring.DataAccessLayer.Book;

public class BookValidator {
    public static boolean isValid(Book book){

        if(book.getTitle()=="" || book.getTitle()==null)
            return false;
        return true;
    }

}
