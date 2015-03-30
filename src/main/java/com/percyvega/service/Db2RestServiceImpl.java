package com.percyvega.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.percyvega.model.Carrier;
import com.percyvega.model.IntergateTransaction;
import com.percyvega.model.Status;
import com.percyvega.repository.Db2RestRepository;
import com.percyvega.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class Db2RestServiceImpl implements Db2RestService {

    private static final Logger logger = LoggerFactory.getLogger(Db2RestServiceImpl.class);

    private Db2RestRepository db2RestRepository;

    @Autowired
    public Db2RestServiceImpl(Db2RestRepository db2RestRepository) {
        this.db2RestRepository = db2RestRepository;
    }

    @Override
    @Transactional
    public Collection<IntergateTransaction> find(String statusName, String carrierName, int count) throws DataAccessException {
        Status status = Status.getByName(statusName);
        Carrier carrier = Carrier.getByName(carrierName);

        if (status == null)
            throw new RuntimeException(statusName + " is not a valid Status");
        if (carrier == null)
            throw new RuntimeException(carrierName + " is not a valid Carrier");
        if (count < 1 || count > 150)
            throw new RuntimeException(count + " is not a valid count value (1-150)");

        Collection<IntergateTransaction> intergateTransactions = db2RestRepository.find(status.getName(), carrier.getName(), count);

        for(IntergateTransaction intergateTransaction : intergateTransactions) {
            try {
                logger.debug(JacksonUtil.fromTransactionToJson(intergateTransaction));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return intergateTransactions;
    }

    @Override
    @Transactional
    public Collection<IntergateTransaction> findAndUpdate(String oldStatusName, String newStatusName, String carrierName, int count) throws DataAccessException {
        Status oldStatus = Status.getByName(oldStatusName);
        Status newStatus = Status.getByName(newStatusName);
        Carrier carrier = Carrier.getByName(carrierName);

        if (oldStatus == null)
            throw new RuntimeException(oldStatusName + " is not a valid old Status");
        if (newStatus == null)
            throw new RuntimeException(newStatusName + " is not a valid new Status");
        if (carrier == null)
            throw new RuntimeException(carrierName + " is not a valid Carrier");
        if (count < 1 || count > 150)
            throw new RuntimeException(count + " is not a valid count value (1-150)");

        Collection<IntergateTransaction> intergateTransactions = db2RestRepository.find(oldStatus.getName(), carrier.getName(), count);

        for(IntergateTransaction intergateTransaction : intergateTransactions) {
            try {
                logger.debug(JacksonUtil.fromTransactionToJson(intergateTransaction));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            intergateTransaction.setStatus(newStatus);
            intergateTransaction.setTryCount(intergateTransaction.getTryCount() + 1);
            save(intergateTransaction);
        }
        return intergateTransactions;
    }

    @Override
    public void save(IntergateTransaction intergateTransaction) throws DataAccessException {
        db2RestRepository.save(intergateTransaction);
    }

}
