package com.midterm.letruongsanh;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewMoldel extends ViewModel {

    private MutableLiveData<List<Data>> mDatas = new MutableLiveData<List<Data>>();
    private MutableLiveData<Data> mData = new MutableLiveData<Data>();

    public LiveData<List<Data>> getDatas() {
        return mDatas;
    }

    public LiveData<Data> getData() {
        return mData;
    }

    public void submit(List<Data> datas, Data data) {
        mDatas =  new MutableLiveData<List<Data>>();
        mData.setValue(data);
        mDatas.setValue(datas);

    }

}
