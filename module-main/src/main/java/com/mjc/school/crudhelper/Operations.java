package com.mjc.school.crudhelper;

public enum Operations {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    CREATE_NEWS(3, "Create news."),
    UPDATE_NEWS(4, "Update news."),
    REMOVE_NEWS_BY_ID(5, "Remove news by id."),

    GET_ALL_AUTHOR(6, "Get all authors."),
    GET_AUTHOR_BY_ID(7, "Get authors by id."),
    CREATE_AUTHOR(8, "Create authors."),
    UPDATE_AUTHOR(9, "Update authors."),
    REMOVE_AUTHOR_BY_ID(10, "Remove authors by id."),
    EXIT(0, "Exit.");

    public final Integer operationNum;
    public final String operation;


    private Operations(Integer operationNum, String operation) {
        this.operationNum = operationNum;
        this.operation = operation;
    }

    public Integer getOperationNum() {
        return operationNum;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperationWithNumber () {
        return this.operationNum + " - " + this.operation;
    }
}
