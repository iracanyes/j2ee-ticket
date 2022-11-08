/*
* Description:
*
 */
package com.iracanyes.demo.j2ee.ticket.dao;

import com.iracanyes.demo.j2ee.ticket.model.bean.intro.FullName;

import java.util.List;

public interface FullNameDao {
  void create(FullName fullname) throws DaoException;
  List<FullName> findAll() throws DaoException;
}
