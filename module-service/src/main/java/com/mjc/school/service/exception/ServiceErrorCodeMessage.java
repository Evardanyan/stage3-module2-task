package com.mjc.school.service.exception;

public enum ServiceErrorCodeMessage {
    NEWS_ID_DOES_NOT_EXIST("001", "News with id %d does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST("002","Author Id does not exist. Author Id is: %s"),

    TAG_ID_DOES_NOT_EXIST("010","Tag Id does not exist. Tag Id is: %s"),

    VALIDATE_NEGATIVE_OR_NULL_NUMBER("003", "%s can not be null or less than 1. %s is: %s"),
    VALIDATE_NULL_STRING("004", "%s can not be null. %s is null"),
    VALIDATE_STRING_LENGTH("005", "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALIDATE_INT_VALUE("006", "%s should be number"),

    AUTHOR_ID_ALREADY_EXIST("007","Author Id already exist. Author Id is: %s"),

    NEWS_ID_ALREADY_EXIST("008","News Id already exist. Author Id is: %s"),

    TAG_ID_ALREADY_EXIST("009","Tag Id already exist. Tag Id is: %s");



    public final String code;
    public final String msg;


    private ServiceErrorCodeMessage(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

     public String getCodeMsg() {
        return "ERROR_CODE: " + code + ", " + "ERROR_MESSAGE: " + msg;
    }

}
