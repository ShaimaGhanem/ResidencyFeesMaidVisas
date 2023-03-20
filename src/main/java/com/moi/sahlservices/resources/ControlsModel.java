/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi.sahlservices.resources;

import java.util.List;

/**
 *
 * @author MOI
 */
public class ControlsModel {

    /**
     * @return the isDisabled
     */
    public boolean isIsDisabled() {
        return isDisabled;
    }

    /**
     * @param isDisabled the isDisabled to set
     */
    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
   
    private String id="";
    private String type = "";
    private String labelAr = "";
    private String labelEn = "";
    private boolean isRequired = true;
     private boolean isDisabled = false;
    private List<OptionControlModel> optionControlModels = null;
    private String defaultValue = "";

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the labelAr
     */
    public String getLabelAr() {
        return labelAr;
    }

    /**
     * @param labelAr the labelAr to set
     */
    public void setLabelAr(String labelAr) {
        this.labelAr = labelAr;
    }

    /**
     * @return the labelEn
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * @param labelEn the labelEn to set
     */
    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * @return the isRequired
     */
    public boolean getIsRequired() {
        return isRequired;
    }

    /**
     * @param isRequired the isRequired to set
     */
    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * @return the optionControlModels
     */
   
    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the optionControlModels
     */
    public List<OptionControlModel> getOptionControlModels() {
        return optionControlModels;
    }

    /**
     * @param optionControlModels the optionControlModels to set
     */
    public void setOptionControlModels(List<OptionControlModel> optionControlModels) {
        this.optionControlModels = optionControlModels;
    }
    
}
