package com.thkha.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.paging.DataSource;

import java.util.List;

import io.reactivex.Flowable;


public class StudentRepository {
    private MyApplicationDao myApplicationDao;
    private DataSource.Factory<Integer, Student> AllStudent;

    public StudentRepository(Application application){

        MyApplicationDatabase myApplicationDatabase = MyApplicationDatabase
                .getInstance(application.getApplicationContext());

        myApplicationDao = myApplicationDatabase.getMyApplicationDao();
        AllStudent = myApplicationDao.getAllStudent();
    }

    public Flowable<List<Student>> getOneStudent(String username){
        return myApplicationDao.getOneStudent(username);
    }

    public void InsertStudent(Student student){
        new InsertStudentAsyncTask(myApplicationDao).execute(student);
    }

    public void UpdateStudent(Student student){
            new UpdateStudentAsyncTask(myApplicationDao).execute(student);
    }

    public void DeleteStudent(Student student){
        new DeleteAsyncTask(myApplicationDao).execute(student);
    }

    public void DeleteAllStudent(){

    }

    public DataSource.Factory<Integer, Student> getAllStudent(){
        return  AllStudent;
    }

    private static class InsertStudentAsyncTask  extends AsyncTask<Student, Void, Void> {
       private final MyApplicationDao myApplicationDao;

       public  InsertStudentAsyncTask( MyApplicationDao myApplicationDao){
           this.myApplicationDao = myApplicationDao;
       }

        @Override
        protected Void doInBackground(Student... students) {
           myApplicationDao.InsertStudent(students[0]);
            return null;
        }
    }

    private static class UpdateStudentAsyncTask  extends AsyncTask<Student, Void, Void> {
        private final MyApplicationDao myApplicationDao;

        public  UpdateStudentAsyncTask( MyApplicationDao myApplicationDao){
            this.myApplicationDao = myApplicationDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            myApplicationDao.UpdateStudent(students[0]);
            return null;
        }
    }

    private static final class DeleteAsyncTask extends AsyncTask<Student, Void, Void>{
        private final MyApplicationDao myApplicationDao;

        public DeleteAsyncTask( MyApplicationDao myApplicationDao ){
            this.myApplicationDao = myApplicationDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            myApplicationDao.DeleteStudent(students[0]);
            return null;
        }
    }
}
