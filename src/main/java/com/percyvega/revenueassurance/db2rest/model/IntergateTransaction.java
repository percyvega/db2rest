package com.percyvega.revenueassurance.db2rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "INQ_RESULT", schema = "SA")
@XmlRootElement(name = "IntergateTransaction")
public class IntergateTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long objid;

    private String mdn;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "ORDER_TYPE")
    private String orderType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "TRY_COUNT")
    private Long tryCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    private String response;

    public IntergateTransaction() {
    }

    public IntergateTransaction(String mdn) {
        this.mdn = mdn;
        this.orderType = "I";
        this.tryCount = 0L;
        this.status = Status.QUEUED;
        this.creationDate = new Date(System.currentTimeMillis());
    }

    public Long getObjid() {
        return objid;
    }

    public void setObjid(Long objid) {
        this.objid = objid;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.objid == null);
    }

    public String getMdn() {
        return this.mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getTryCount() {
        return this.tryCount;
    }

    public void setTryCount(Long tryCount) {
        this.tryCount = tryCount;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
        setUpdateDate();
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public void setUpdateDate(/*Date updateDate*/) {
//        this.updateDate = updateDate;
        this.updateDate = new Date();
    }

    @Override
    public String toString() {
        return "ReconRecord [objid=" + objid + ", carrierName=" + carrierName + ", mdn=" + mdn +
                ", creationDate=" + creationDate + ", updateDate=" + updateDate +
                ", status=" + status + ", orderType=" + orderType +
                ", tryCount=" + tryCount + ", response=" + response + "]";
    }

}
