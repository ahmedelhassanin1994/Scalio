package com.example.scal_app.data_layer.network;




import com.example.scal_app.data_layer.model.Model_User;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface Api_Interface {


    @GET("search/users")
    Observable<Model_User> get_users(@Query("q") String search, @Query("page") int page, @Query("per_page") int per_page);

}


