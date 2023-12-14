package com.example.sarvikademo;


public class ResponsePacket<T> {

    private int errorCode;
    private String message;
    private T responsePacket;


    public ResponsePacket(int errorCode, String message, T responsePacket) {
        this.errorCode = errorCode;
        this.message = message;
        this.responsePacket = responsePacket;

    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponsePacket() {
        return responsePacket;
    }

    public void setResponsePacket(T responsePacket) {
        this.responsePacket = responsePacket;
    }

}
