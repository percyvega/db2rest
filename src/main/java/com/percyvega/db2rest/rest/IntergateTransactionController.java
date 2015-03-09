package com.percyvega.db2rest.rest;

import com.percyvega.db2rest.service.Db2RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("intergateTransaction")
public class IntergateTransactionController {

    @Autowired
    private Db2RestService db2RestService;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public Collection find(
            @RequestParam(value = "status") String statusName,
            @RequestParam(value = "carrier") String carrierName,
            @RequestParam(value = "count", defaultValue = "1") int count) {
        return db2RestService.find(statusName, carrierName, count);
    }

    @RequestMapping(value = "findAndUpdate", method = RequestMethod.GET)
    public Collection findAndUpdate(
            @RequestParam(value = "oldStatus") String oldStatusName,
            @RequestParam(value = "newStatus") String newStatusName,
            @RequestParam(value = "carrier") String carrierName,
            @RequestParam(value = "count", defaultValue = "1") int count) {
        return db2RestService.findAndUpdate(oldStatusName, newStatusName, carrierName, count);
    }

}
