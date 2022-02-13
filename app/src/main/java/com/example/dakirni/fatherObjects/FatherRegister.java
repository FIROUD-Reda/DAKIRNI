package com.example.dakirni.fatherObjects;

public class FatherRegister {
    private String name;
    private String key;
    private int age;
    private String relation;
    private String photo;


    public FatherRegister(String name, String key, int age, String relation, String photo) {
        this.name = name;
        this.key = key;
        this.age = age;
        this.relation = relation;
        this.photo = photo;
    }

    public FatherRegister() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRelation() {
        return relation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
