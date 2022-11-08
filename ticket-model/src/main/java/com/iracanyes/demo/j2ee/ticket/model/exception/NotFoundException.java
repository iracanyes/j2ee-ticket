package com.iracanyes.demo.j2ee.ticket.model.exception;

public class NotFoundException extends Exception {

  /**
   * Constructeur par défaut.
   */
  public NotFoundException() {
  }


  /**
   * Constructor.
   *
   * @param pMessage -
   */
  public NotFoundException(String pMessage) {
    super(pMessage);
  }


  /**
   * Constructor.
   *
   * @param pMessage -
   * @param pCause -
   */
  public NotFoundException(String pMessage, Throwable pCause) {
    super(pMessage, pCause);
  }
}
