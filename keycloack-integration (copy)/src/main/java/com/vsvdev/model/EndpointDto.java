package com.vsvdev.model;

public class EndpointDto {

  private int id;
  private String name;
  private String userName;

  public EndpointDto(int id, String name, String userName) {
    this.id = id;
    this.name = name;
    this.userName = userName;
  }

  public EndpointDto() {
  }

  public EndpointDto(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public EndpointDto(String name, String userName) {
    this.name = name;
    this.userName = userName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
