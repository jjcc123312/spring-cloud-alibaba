package com.jjcc.zsl.cloud.auto.model;

public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}