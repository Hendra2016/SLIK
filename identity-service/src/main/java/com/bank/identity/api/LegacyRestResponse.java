package com.bank.identity.api;

public class LegacyRestResponse {

    private int status;
    private String message;
    private long totalRecords;
    private Object contents;

    public LegacyRestResponse() {
    }

    public LegacyRestResponse(int status, String message, Object contents, long totalRecords) {
        this.status = status;
        this.message = message;
        this.contents = contents;
        this.totalRecords = totalRecords;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }
}

