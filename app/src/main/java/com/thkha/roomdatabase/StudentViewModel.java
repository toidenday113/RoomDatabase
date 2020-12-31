package com.thkha.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

import io.reactivex.Flowable;


public class StudentViewModel extends AndroidViewModel {

    private final StudentRepository studentRepository;
    private final LiveData<PagedList<Student>> AllStudents;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        PagedList.Config myPagingConfig = new PagedList.Config.Builder()
                .setPageSize(15)
                .setPrefetchDistance(50)
                .setEnablePlaceholders(true)
                .build();

        studentRepository = new StudentRepository(application);
        AllStudents = new LivePagedListBuilder<>(studentRepository.getAllStudent(),myPagingConfig).build();
        //  AllStudents = studentRepository.getAllStudent();
    }

    public Flowable<List<Student>> getOneStudent(String username){
        return studentRepository.getOneStudent(username);
    }

    public LiveData<PagedList<Student>> getGetAllStudent(){
        return  AllStudents;
    }

    public void InsertStudent(Student student){
        studentRepository.InsertStudent(student);
    }

    public void UpdateStudent(Student student){
        studentRepository.UpdateStudent(student);
    }

    public void DeleteStudent(Student student){
        studentRepository.DeleteStudent(student);
    }




}
