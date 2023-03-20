/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi.sahlservices.resources;

/**
 *
 * @author MOI
 */
public class InitiatePaymentResponse {

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the userCivilId
     */
    public String getUserCivilId() {
        return userCivilId;
    }

    /**
     * @param userCivilId the userCivilId to set
     */
    public void setUserCivilId(String userCivilId) {
        this.userCivilId = userCivilId;
    }

    /**
     * @return the payForCivilId
     */
    public String getPayForCivilId() {
        return payForCivilId;
    }

    /**
     * @param payForCivilId the payForCivilId to set
     */
    public void setPayForCivilId(String payForCivilId) {
        this.payForCivilId = payForCivilId;
    }

    /**
     * @return the serviceTypeId
     */
    public String getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * @param serviceTypeId the serviceTypeId to set
     */
    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * @return the serviceNameEn
     */
    public String getServiceNameEn() {
        return serviceNameEn;
    }

    /**
     * @param serviceNameEn the serviceNameEn to set
     */
    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }

    /**
     * @return the serviceNameAr
     */
    public String getServiceNameAr() {
        return serviceNameAr;
    }

    /**
     * @param serviceNameAr the serviceNameAr to set
     */
    public void setServiceNameAr(String serviceNameAr) {
        this.serviceNameAr = serviceNameAr;
    }

    /**
     * @return the trackId
     */
    public String getTrackId() {
        return trackId;
    }

    /**
     * @param trackId the trackId to set
     */
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    /**
     * @return the paymentUrl
     */
    public String getPaymentUrl() {
        return paymentUrl;
    }

    /**
     * @param paymentUrl the paymentUrl to set
     */
    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
    private String statusCode = "";
    private String statusMessage = "";
    private String applicationId = "";
    private String applicationName = "";
    private String userCivilId = "";
    private String payForCivilId = "";
    private String serviceTypeId = "";
    private String serviceNameEn = "";
    private String serviceNameAr = "";
    private String trackId = "";
    private String paymentUrl = "";
            
  
    
}
 