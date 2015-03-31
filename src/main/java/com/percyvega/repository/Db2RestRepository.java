package com.percyvega.repository;

import com.percyvega.model.IntergateTransaction;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface Db2RestRepository extends PagingAndSortingRepository<IntergateTransaction, Long> {

    @Query(value = "select * from (select * FROM SA.INQ_RESULT where STATUS = :status and CARRIER_NAME = :carrierName order by CREATION_DATE desc) where rownum <= :count", nativeQuery = true)
    Collection<IntergateTransaction> find(@Param("status") String status, @Param("carrierName") String carrierName, @Param("count") int count) throws DataAccessException;

}