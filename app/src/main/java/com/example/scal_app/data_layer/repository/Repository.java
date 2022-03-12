package com.example.scal_app.data_layer.repository;

import com.example.scal_app.data_layer.model.Model_User;
import com.example.scal_app.data_layer.network.Api_Interface;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class Repository {

    Api_Interface api_interface;

    public Repository(Api_Interface api_interface) {
        this.api_interface = api_interface;
    }

//    public Call<Model_User> get_users(String search, int page , int per_page){
//        return  api_interface.get_users(search,page,per_page);
//    }
}
