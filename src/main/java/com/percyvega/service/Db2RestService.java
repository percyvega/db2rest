package com.percyvega.service;

import com.percyvega.model.IntergateTransaction;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface Db2RestService {

    Collection<IntergateTransaction> find(String statusName, String carrierName, int count) throws DataAccessException;

    Collection<IntergateTransaction> findAndUpdate(String oldStatusName, String newStatusName, String carrierName, int count) throws DataAccessException;

    void save(IntergateTransaction intergateTransaction) throws DataAccessException;

}
