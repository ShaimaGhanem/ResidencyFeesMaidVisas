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
public class envParam {

    /**
     * @return the Key
     */
    public String getKey() {
        return Key;
    }

    /**
     * @param Key the Key to set
     */
    public void setKey(String Key) {
        this.Key = Key;
    }

    /**
     * @return the Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(String Value) {
        this.Value = Value;
    }

 
    private String Key="";
    private String Value="";
    
}
