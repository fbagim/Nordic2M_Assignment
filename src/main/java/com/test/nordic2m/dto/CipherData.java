package com.test.nordic2m.dto;

import java.io.Serializable;

public class CipherData implements Serializable {
    private String requestData;
    private String responseData;

    private Integer noOfRotations;

    public Integer getNoOfRotations() {
        return noOfRotations;
    }

    public void setNoOfRotations(Integer noOfRotations) {
        this.noOfRotations = noOfRotations;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
