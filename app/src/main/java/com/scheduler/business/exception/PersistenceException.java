package com.scheduler.business.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception{
    public PersistenceException(String message){
        super(message);
    }
}
