package com.dambroz.dionis.main.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VKAnswer implements Serializable {
    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
