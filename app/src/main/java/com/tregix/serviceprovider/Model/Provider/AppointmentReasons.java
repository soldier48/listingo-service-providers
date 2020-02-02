package com.tregix.serviceprovider.Model.Provider;;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppointmentReasons implements Serializable {

    @SerializedName("urgenttask")
    @Expose
    private String urgenttask;
    @SerializedName("generalvisit")
    @Expose
    private String generalvisit;
    @SerializedName("quotation")
    @Expose
    private String quotation;
    @SerializedName("consultation")
    @Expose
    private String consultation;
    @SerializedName("others")
    @Expose
    private String others;

    public String getUrgenttask() {
        return urgenttask;
    }

    public void setUrgenttask(String urgenttask) {
        this.urgenttask = urgenttask;
    }

    public String getGeneralvisit() {
        return generalvisit;
    }

    public void setGeneralvisit(String generalvisit) {
        this.generalvisit = generalvisit;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

}
