package com.example.scal_app.data_layer.DataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.scal_app.data_layer.model.Item;
import com.example.scal_app.data_layer.model.Model_User;
import com.example.scal_app.data_layer.network.PostsClient;
import com.example.scal_app.data_layer.repository.Repository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    private static final int FIRST_PAGE = 1;
    private static final String TAG = "ItemDataSource";
    public static final int PAGE_SIZE = 9;
    String search ;
    private Repository repository;


    CompositeDisposable compositelist=new CompositeDisposable();



    public ItemDataSource(String search) {
    this.search=search;
    }

    @Override
    public void loadInitial(@NonNull @NotNull LoadInitialParams<Integer> params, @NonNull @NotNull LoadInitialCallback<Integer, Item> callback) {

        PostsClient.getINSTANCE().getApi().get_users(search,FIRST_PAGE,PAGE_SIZE)
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model_user -> callback.onResult(model_user.getItems(),null,FIRST_PAGE+1),
                        error -> Log.e("viwModel", error.getMessage())
                );





    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {

        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
        PostsClient.getINSTANCE().getApi().get_users(search,params.key,PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model_user -> callback.onResult(model_user.getItems(),adjacentKey),
                        error -> Log.e("viwModel", error.getMessage())

                );




    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        PostsClient.getINSTANCE().getApi().get_users(search,params.key,PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model_user -> callback.onResult(model_user.getItems(),params.key+1),
                        error -> Log.e("viwModel", error.getMessage())

                );




    }
}
