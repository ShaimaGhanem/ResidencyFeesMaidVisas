/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi.sahlservices.resources;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author MOI
 */
public class FileParameters {

    /**
     * @return the deportationMoiIP
     */
    public String getDeportationMoiIP() {
        return deportationMoiIP;
    }

    /**
     * @param deportationMoiIP the deportationMoiIP to set
     */
    public void setDeportationMoiIP(String deportationMoiIP) {
        this.deportationMoiIP = deportationMoiIP;
    }

    /**
     * @return the deportationMoiPort
     */
    public String getDeportationMoiPort() {
        return deportationMoiPort;
    }

    /**
     * @param deportationMoiPort the deportationMoiPort to set
     */
    public void setDeportationMoiPort(String deportationMoiPort) {
        this.deportationMoiPort = deportationMoiPort;
    }

    /**
     * @return the personDisplayIp
     */
    public String getPersonDisplayIp() {
        return personDisplayIp;
    }

    /**
     * @param personDisplayIp the personDisplayIp to set
     */
    public void setPersonDisplayIp(String personDisplayIp) {
        this.personDisplayIp = personDisplayIp;
    }

    /**
     * @return the personDisplayPort
     */
    public String getPersonDisplayPort() {
        return personDisplayPort;
    }

    /**
     * @param personDisplayPort the personDisplayPort to set
     */
    public void setPersonDisplayPort(String personDisplayPort) {
        this.personDisplayPort = personDisplayPort;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the paymentIp
     */
    public String getPaymentIp() {
        return paymentIp;
    }

    /**
     * @param paymentIp the paymentIp to set
     */
    public void setPaymentIp(String paymentIp) {
        this.paymentIp = paymentIp;
    }

    /**
     * @return the initPaymentPort
     */
    public String getInitPaymentPort() {
        return initPaymentPort;
    }

    /**
     * @param initPaymentPort the initPaymentPort to set
     */
    public void setInitPaymentPort(String initPaymentPort) {
        this.initPaymentPort = initPaymentPort;
    }

    /**
     * @return the checkPaymentPort
     */
    public String getCheckPaymentPort() {
        return checkPaymentPort;
    }

    /**
     * @param checkPaymentPort the checkPaymentPort to set
     */
    public void setCheckPaymentPort(String checkPaymentPort) {
        this.checkPaymentPort = checkPaymentPort;
    }

    /**
     * @return the initpaymentToken
     */
    public String getInitpaymentToken() {
        return initpaymentToken;
    }

    /**
     * @param initpaymentToken the initpaymentToken to set
     */
    public void setInitpaymentToken(String initpaymentToken) {
        this.initpaymentToken = initpaymentToken;
    }

    /**
     * @return the initpaymentUsername
     */
    public String getInitpaymentUsername() {
        return initpaymentUsername;
    }

    /**
     * @param initpaymentUsername the initpaymentUsername to set
     */
    public void setInitpaymentUsername(String initpaymentUsername) {
        this.initpaymentUsername = initpaymentUsername;
    }

    /**
     * @return the initpaymentPassword
     */
    public String getInitpaymentPassword() {
        return initpaymentPassword;
    }

    /**
     * @param initpaymentPassword the initpaymentPassword to set
     */
    public void setInitpaymentPassword(String initpaymentPassword) {
        this.initpaymentPassword = initpaymentPassword;
    }

    /**
     * @return the initpaymentNonce
     */
    public String getInitpaymentNonce() {
        return initpaymentNonce;
    }

    /**
     * @param initpaymentNonce the initpaymentNonce to set
     */
    public void setInitpaymentNonce(String initpaymentNonce) {
        this.initpaymentNonce = initpaymentNonce;
    }

    /**
     * @return the initpaymentCreated
     */
    public String getInitpaymentCreated() {
        return initpaymentCreated;
    }

    /**
     * @param initpaymentCreated the initpaymentCreated to set
     */
    public void setInitpaymentCreated(String initpaymentCreated) {
        this.initpaymentCreated = initpaymentCreated;
    }

    /**
     * @return the checkpaymentToken
     */
    public String getCheckpaymentToken() {
        return checkpaymentToken;
    }

    /**
     * @param checkpaymentToken the checkpaymentToken to set
     */
    public void setCheckpaymentToken(String checkpaymentToken) {
        this.checkpaymentToken = checkpaymentToken;
    }

    /**
     * @return the checkpaymentUsername
     */
    public String getCheckpaymentUsername() {
        return checkpaymentUsername;
    }

    /**
     * @param checkpaymentUsername the checkpaymentUsername to set
     */
    public void setCheckpaymentUsername(String checkpaymentUsername) {
        this.checkpaymentUsername = checkpaymentUsername;
    }

    /**
     * @return the checkpaymentPassword
     */
    public String getCheckpaymentPassword() {
        return checkpaymentPassword;
    }

    /**
     * @param checkpaymentPassword the checkpaymentPassword to set
     */
    public void setCheckpaymentPassword(String checkpaymentPassword) {
        this.checkpaymentPassword = checkpaymentPassword;
    }

    /**
     * @return the checkpaymentNonce
     */
    public String getCheckpaymentNonce() {
        return checkpaymentNonce;
    }

    /**
     * @param checkpaymentNonce the checkpaymentNonce to set
     */
    public void setCheckpaymentNonce(String checkpaymentNonce) {
        this.checkpaymentNonce = checkpaymentNonce;
    }

    /**
     * @return the checkpaymentCreated
     */
    public String getCheckpaymentCreated() {
        return checkpaymentCreated;
    }

    /**
     * @param checkpaymentCreated the checkpaymentCreated to set
     */
    public void setCheckpaymentCreated(String checkpaymentCreated) {
        this.checkpaymentCreated = checkpaymentCreated;
    }

    private String ip = "";
    private String port = "";
    private String personDisplayIp = "";
    private String personDisplayPort = "";
    private String paymentIp = "";
    private String initPaymentPort = "";
    private String checkPaymentPort = "";
    private String initpaymentToken = "";
    private String initpaymentUsername = "";
    private String initpaymentPassword = "";
    private String initpaymentNonce = "";
    private String initpaymentCreated = "";
    private String checkpaymentToken = "";
    private String checkpaymentUsername = "";
    private String checkpaymentPassword = "";
    private String checkpaymentNonce = "";
    private String checkpaymentCreated = "";
    private String deportationMoiIP = "";
    private String deportationMoiPort = "";

    public void readParameters() throws IOException {

        //Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        //System.out.println("-----1------------" + path.toString());
        String filePath = "//shared//lzcollect//members//SharedResources//AppVariables//";
        // System.out.println("-----1----filePath--------" + filePath);

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(filePath + "DeportationFineService.json"));

        envParam[] envParamData = gson.fromJson(reader, envParam[].class);
        for (int i = 0; i < envParamData.length; i++) {

            switch (envParamData[i].getKey()) {

                case "ip":
                    setIp(envParamData[i].getValue());
                    break;
                case "port":
                    setPort(envParamData[i].getValue());
                    break;
                case "personDisplayIp":
                    setPersonDisplayIp(envParamData[i].getValue());
                    break;
                case "personDisplayPort":
                    setPersonDisplayPort(envParamData[i].getValue());
                    break;

                case "paymentIp":
                    setPaymentIp(envParamData[i].getValue());
                    break;
                case "initPaymentPort":
                    setInitPaymentPort(envParamData[i].getValue());
                    break;
                case "checkPaymentPort":
                    setCheckPaymentPort(envParamData[i].getValue());
                    break;
                case "initpaymentToken":
                    setInitpaymentToken(envParamData[i].getValue());
                    break;
                case "initpaymentUsername":
                    setInitpaymentUsername(envParamData[i].getValue());
                    break;
                case "initpaymentPassword":
                    setInitpaymentPassword(envParamData[i].getValue());
                    break;
                case "initpaymentNonce":
                    setInitpaymentNonce(envParamData[i].getValue());
                    break;
                case "initpaymentCreated":
                    setInitpaymentCreated(envParamData[i].getValue());
                    break;
                case "checkpaymentToken":
                    setCheckpaymentToken(envParamData[i].getValue());
                    break;
                case "checkpaymentUsername":
                    setCheckpaymentUsername(envParamData[i].getValue());
                    break;
                case "checkpaymentPassword":
                    setCheckpaymentPassword(envParamData[i].getValue());
                    break;
                case "checkpaymentNonce":
                    setCheckpaymentNonce(envParamData[i].getValue());
                    break;
                case "checkpaymentCreated":
                    setCheckpaymentCreated(envParamData[i].getValue());
                    break;
                case "deportationMoiIP":
                    setDeportationMoiIP(envParamData[i].getValue());
                    break;
                case "deportationMoiPort":
                    setDeportationMoiPort(envParamData[i].getValue());
                    break;
                default:
                    break;
            }
        }

        reader.close();

    }

}
