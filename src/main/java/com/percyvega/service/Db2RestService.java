package com.percyvega.service;

import com.percyvega.model.CarrierInquiry;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface Db2RestService {

    Collection<CarrierInquiry> find(String statusName, String carrierName, int count) throws DataAccessException;

    Collection<CarrierInquiry> findAndUpdate(String oldStatusName, String newStatusName, String carrierName, int count) throws DataAccessException;

    void save(CarrierInquiry carrierInquiry) throws DataAccessException;

}
