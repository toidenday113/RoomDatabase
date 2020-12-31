package com.thkha.roomdatabase;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

class MyDiffUtilCallback extends DiffUtil.Callback {

    private List<Student> ollList;
    private List<Student> newList;

    public MyDiffUtilCallback(List<Student> ollList, List<Student> newList) {
        this.ollList = ollList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return ollList !=null ? ollList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList !=null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == newItemPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return ollList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
