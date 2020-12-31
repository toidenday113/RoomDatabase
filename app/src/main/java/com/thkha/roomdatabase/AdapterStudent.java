package com.thkha.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterStudent extends PagedListAdapter<Student, AdapterStudent.Holder> {

    private OnClickItemView onClickItemView;

    public AdapterStudent(OnClickItemView onClickItemView) {
        super(DIFFUTIL_STUDENT);
          this.onClickItemView = onClickItemView;
    }

    private static final  DiffUtil.ItemCallback<Student> DIFFUTIL_STUDENT = new DiffUtil.ItemCallback<Student>(){


        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return  oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getUsername().equals(newItem.getUsername()) &&
                    oldItem.getAge() == newItem.getAge();
        }
    };



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Student student = getItem(position);
        holder.textView_Name.setText(student.getName());
        holder.textView_Username.setText(student.getUsername());
        holder.textView_Age.setText(String.valueOf(student.getAge()));

        holder.itemView.setOnClickListener(v ->{
            onClickItemView.onClickAdapter(getItem(position));
        });
     }

    static class Holder extends RecyclerView.ViewHolder {

        TextView textView_Name, textView_Username, textView_Age;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.textView_Name);
            textView_Username = itemView.findViewById(R.id.textView_Username);
            textView_Age = itemView.findViewById(R.id.textView_Age);
        }
    }

    public interface OnClickItemView {
        void onClickAdapter(Student student);
    }
}
