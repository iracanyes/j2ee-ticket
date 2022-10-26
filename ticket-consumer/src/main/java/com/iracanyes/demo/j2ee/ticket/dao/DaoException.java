/*
* Description:
* The DaoException class hide SQLException thrown from DAO implementation classes from
* the caller context (business or presentation layer)
* */
package com.iracanyes.demo.j2ee.ticket.dao;

public class DaoException extends Exception{
  public DaoException(String message){
    super(message);
  }
}
