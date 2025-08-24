package com.example.BankService.service;

public class RequestBodyGetterConfirm {
    private String operationId;
    private String code;

    public String getCode() {
        return code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
