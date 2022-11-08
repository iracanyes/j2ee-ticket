package com.iracanyes.demo.j2ee.ticket.model.exception;

public class FunctionalException extends Exception{
  /**
   * Constructor
   * @param pMessage -
   *
   * */
  public FunctionalException(String pMessage){
    super(pMessage);
  }

  /**
   * Constructor
   * @param pMessage -
   * @param pCause -
   */
  public FunctionalException(String pMessage, Throwable pCause){
    super(pMessage, pCause);
  }
}
