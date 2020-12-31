package com.thkha.roomdatabase;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements AdapterStudent.OnClickItemView {

    private EditText et_Name, et_Username, et_Age;
    private Button btn_Save, btn_Update;
    private RecyclerView rv_ListStudent;
    private AdapterStudent adapterStudent;
    private StudentViewModel studentViewModel;
    private int id = -1;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private SearchView searchView;
    private  AdapterStudentSearch adapterStudentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitControl();
        SetupAdapter();
        EventControl();

        studentViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(StudentViewModel.class);
        studentViewModel.getGetAllStudent().observe(this, student -> {
            adapterStudent.submitList(student);
            rv_ListStudent.scrollToPosition(0);
            if (id == -1) {
                btn_Update.setEnabled(false);
                btn_Save.setEnabled(true);
            } else {
                btn_Update.setEnabled(true);
                btn_Save.setEnabled(false);
            }
        });


    }

    private void SetupAdapter() {
        rv_ListStudent.setLayoutManager(new LinearLayoutManager(this));
        rv_ListStudent.setItemAnimator(new DefaultItemAnimator());
        adapterStudent = new AdapterStudent(this);
        rv_ListStudent.setAdapter(adapterStudent);
        adapterStudentSearch = new AdapterStudentSearch();
        adapterStudentSearch.SetOnClickItemView(student -> {
            id = student.getId();
            et_Name.setText(student.getName());
            et_Username.setText(student.getUsername());
            et_Age.setText(String.valueOf(student.getAge()));
        });
    }


    private void InitControl() {
        et_Name = findViewById(R.id.editTextText_Name);
        et_Username = findViewById(R.id.editTextText_Username);
        et_Age = findViewById(R.id.editTextNumber_Age);
        rv_ListStudent = findViewById(R.id.recyclerView_ListStudent);
        btn_Save = findViewById(R.id.button_Save);
        btn_Update = findViewById(R.id.button_Update);
    }

    private void EventControl() {

        btn_Save.setOnClickListener(v -> {
            Student student = new Student();
            student.setName(et_Name.getText().toString());
            student.setUsername(et_Username.getText().toString());
            student.setAge(Integer.parseInt(et_Age.getText().toString()));
            studentViewModel.InsertStudent(student);
        });

        btn_Update.setOnClickListener(v -> {
            if (id != -1) {
                Student student = new Student();
                student.setName(et_Name.getText().toString());
                student.setUsername(et_Username.getText().toString());
                student.setAge(Integer.parseInt(et_Age.getText().toString()));
                student.setId(id);
                studentViewModel.UpdateStudent(student);
                id =-1;
            }
        });

    }

    @Override
    public void onClickAdapter(Student student) {
        id = student.getId();
        et_Name.setText(student.getName());
        et_Username.setText(student.getUsername());
        et_Age.setText(String.valueOf(student.getAge()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mDisposable.add(studentViewModel.getOneStudent("%"+newText+"%")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s ->{
                            rv_ListStudent.setAdapter(adapterStudentSearch);
                            adapterStudentSearch.submitList(s);
                        }, error ->{

                        })
                );
                //studentViewModel.getOneStudent("%"+newText+"%").observe(MainActivity.this, adapterStudent::submitList);
                return false;
            }
        });

        return true;

    }


}