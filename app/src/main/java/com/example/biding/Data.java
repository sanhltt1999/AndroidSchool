package com.example.biding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Data extends BaseObservable {
    private String name;

    public Data(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
