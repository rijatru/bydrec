package com.test.data.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRequest {

    @SerializedName("url")
    @Expose
    private String url;

    public DataRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
