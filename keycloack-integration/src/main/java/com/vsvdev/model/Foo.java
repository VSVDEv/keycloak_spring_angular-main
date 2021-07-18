package com.vsvdev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="foo")
public class Foo {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private String name;
  @ElementCollection(targetClass = String.class)
  private List<String> userName = new ArrayList<>();

  public Foo() {
  }

  public Foo(String name, List<String>  userName) {
    this.name = name;
    this.userName = userName;
  }

  public Foo(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Foo(int id, String name, List<String>  userName) {
    this.id = id;
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

  public List<String> getUserName() {
    return userName;
  }

  public void setUserName(List<String> userName) {
    this.userName = userName;
  }
}
