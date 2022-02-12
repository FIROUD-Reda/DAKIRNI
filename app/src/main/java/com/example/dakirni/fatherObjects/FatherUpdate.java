package com.example.dakirni.fatherObjects;

public class FatherUpdate {
    private String name;
    private int age;
    private String relation;
    private String photo;


    public FatherUpdate(String name, int age, String relation, String photo) {
        this.name = name;
        this.age = age;
        this.relation = relation;
        this.photo = photo;
    }

    public FatherUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
