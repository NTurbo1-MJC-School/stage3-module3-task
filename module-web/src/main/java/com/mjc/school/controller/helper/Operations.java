package com.mjc.school.controller.helper;

public enum Operations {
  GET_ALL_NEWS(1, "Get all news."),
  GET_ALL_AUTHORS(2, "Get all authors"),
  GET_ALL_TAGS(3, "Get all tags"),
  GET_NEWS_BY_ID(4, "Get news by id."),
  GET_AUTHOR_BY_ID(5, "Get author by id."),
  GET_TAG_BY_ID(6, "Get tag by id."),
  CREATE_NEWS(7, "Create news."),
  CREATE_AUTHOR(8, "Create author."),
  CREATE_TAG(9, "Create tag."),
  UPDATE_NEWS(10, "Update news."),
  UPDATE_AUTHOR(11, "Update author."),
  UPDATE_TAG(12, "Update tag."),
  REMOVE_NEWS_BY_ID(13, "Remove news by id."),
  REMOVE_AUTHOR_BY_ID(14, "Remove author by id."),
  REMOVE_TAG_BY_ID(15, "Remove tag by id."),
  GET_AUTHOR_BY_NEWS_ID(16, "Get author by news id."),
  GET_TAG_BY_NEWS_ID(17, "Get tag by news id."),
  EXIT(0, "Exit.");

  private final Integer operationNumber;
  private final String operation;

  Operations(Integer operationNumber, String operation) {
    this.operationNumber = operationNumber;
    this.operation = operation;
  }

  public String getOperation() {
    return Constant.OPERATION + operation;
  }

  public String getOperationNumber() {
    return operationNumber.toString();
  }

  public String getOperationWithNumber() {
    return operationNumber + " - " + operation;
  }
}
