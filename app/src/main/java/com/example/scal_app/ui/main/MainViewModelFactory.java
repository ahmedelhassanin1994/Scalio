package com.example.scal_app.ui.main;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

   Application application;
   Context context;


    public MainViewModelFactory(Context context)
    {
        this.context=context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new MainViewModel(context);
    }
}
