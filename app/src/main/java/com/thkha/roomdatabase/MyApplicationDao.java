package com.thkha.roomdatabase;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface MyApplicationDao {

    @Insert
    void InsertStudent(Student student);

    @Update
    void UpdateStudent(Student student);

    @Delete
    void DeleteStudent(Student student);

    @Query("DELETE FROM table_student")
    void DeleteAllStudent();


    @Query("SELECT * FROM table_student WHERE  username LIKE :username")
    Flowable<List<Student>> getOneStudent(String username);

    @Query("SELECT  * FROM table_student ORDER BY age DESC")
    DataSource.Factory<Integer, Student> getAllStudent();
    //LiveData<List<Student>> getAllStudent();

}
