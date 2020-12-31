package com.thkha.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String username;

    private int age;

    public Student(String name, String username, int age) {
        this.name = name;
        this.username = username;
        this.age = age;
    }

    public Student() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
