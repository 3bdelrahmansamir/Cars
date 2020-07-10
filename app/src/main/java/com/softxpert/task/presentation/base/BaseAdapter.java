package com.softxpert.task.presentation.base;

import java.util.List;


public interface BaseAdapter<T> {

    public void setType(int type);

    public void setData(List<T> data);

    public void addData(List<T> data);

}
