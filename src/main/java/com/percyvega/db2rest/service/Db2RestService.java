package com.percyvega.db2rest.service;

import com.percyvega.db2rest.model.IntergateTransaction;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface Db2RestService {

    Collection<? extends IntergateTransaction> find(String statusName, String carrierName, int count) throws DataAccessException;

    Collection<? extends IntergateTransaction> findAndUpdate(String oldStatusName, String newStatusName, String carrierName, int count) throws DataAccessException;

    void save(IntergateTransaction intergateTransaction) throws DataAccessException;

}
