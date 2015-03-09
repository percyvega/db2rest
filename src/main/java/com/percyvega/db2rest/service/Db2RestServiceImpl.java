package com.percyvega.db2rest.service;

import com.percyvega.db2rest.model.Carrier;
import com.percyvega.db2rest.model.Status;
import com.percyvega.db2rest.repository.Db2RestRepository;
import com.percyvega.db2rest.model.IntergateTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class Db2RestServiceImpl implements Db2RestService {

    private Db2RestRepository db2RestRepository;

    @Autowired
    public Db2RestServiceImpl(Db2RestRepository db2RestRepository) {
        this.db2RestRepository = db2RestRepository;
    }

    @Override
    @Transactional
    public Collection<? extends IntergateTransaction> find(String statusName, String carrierName, int count) throws DataAccessException {
        Status status = Status.getByName(statusName);
        Carrier carrier = Carrier.getByName(carrierName);

        if (status == null)
            throw new RuntimeException(statusName + " is not a valid Status");
        if (carrier == null)
            throw new RuntimeException(carrierName + " is not a valid Carrier");
        if (count < 1 || count > 150)
            throw new RuntimeException(count + " is not a valid count value (1-150)");

        Collection<? extends IntergateTransaction> intergateTransactions = db2RestRepository.find(status.getName(), carrier.getName(), count);
        return intergateTransactions;
    }

    @Override
    @Transactional
    public Collection<? extends IntergateTransaction> findAndUpdate(String oldStatusName, String newStatusName, String carrierName, int count) throws DataAccessException {
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

        Collection<? extends IntergateTransaction> intergateTransactions = db2RestRepository.find(oldStatus.getName(), carrier.getName(), count);
        for(IntergateTransaction intergateTransaction : intergateTransactions) {
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
