package com.example.test.exception;

public class PaymentTypeNotValidException extends RuntimeException{

private String code ;
    public PaymentTypeNotValidException(String resourceName, String value , String code) {
        super(generateMessage(resourceName, value));
        this.code = code;

    }


    private static String generateMessage(String resourceName, String value ) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Invoice Type ").append(value).append(" =  Not valid .");
        return messageBuilder.toString();
    }

    public String getCode() {
        return this.code;
    }


}
