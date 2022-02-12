package com.example.dakirni.fatherObjects;

public class FatherResponse {
    private String _id;
    private String name;
    private String key;
    private int age;
    private String relation;

    public FatherResponse() {
    }

    public FatherResponse(String _id, String name, String key, int age, String relation) {
        this._id = _id;
        this.name = name;
        this.key = key;
        this.age = age;
        this.relation = relation;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
