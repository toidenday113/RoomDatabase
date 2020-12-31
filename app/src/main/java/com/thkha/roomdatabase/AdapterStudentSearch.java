package com.thkha.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterStudentSearch extends ListAdapter<Student, AdapterStudentSearch.Holder> {

    private OnClickItemView onClickItemView;

    public AdapterStudentSearch() {
        super(DIFFUTIL_STUDENT);
          //this.onClickItemView = onClickItemView;
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
        return new AdapterStudentSearch.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Student student = getItem(position);
        holder.textView_Name.setText(student.getName());
        holder.textView_Username.setText(student.getUsername());
        holder.textView_Age.setText(String.valueOf(student.getAge()));

//        holder.itemView.setOnClickListener(v ->{
//            onClickItemView.onClickAdapter(getItem(position));
//        });
     }



    class Holder extends RecyclerView.ViewHolder {

        TextView textView_Name, textView_Username, textView_Age;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.textView_Name);
            textView_Username = itemView.findViewById(R.id.textView_Username);
            textView_Age = itemView.findViewById(R.id.textView_Age);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onClickItemView != null && position != RecyclerView.NO_POSITION) {
                        onClickItemView.onClickAdapter(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnClickItemView {
        void onClickAdapter(Student student);
    }

    public void SetOnClickItemView(OnClickItemView listener){
        this.onClickItemView = listener;
    }
}
