package com.example.demouploadfiles.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private boolean status;
    private List<String> messsages = new ArrayList<>();
    private Object payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMesssages() {
        return messsages;
    }

    public void setMesssages(List<String> messsages) {
        this.messsages = messsages;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

}
