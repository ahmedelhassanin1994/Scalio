package com.example.scal_app.ui.main.user;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.scal_app.data_layer.repository.Repository;
import com.example.scal_app.ui.main.MainViewModel;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

   Application application;
   Context context;
   Repository repository;


    public UserViewModelFactory(Context context)
    {
        this.context=context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new UsersViewModel(context);
    }
}
