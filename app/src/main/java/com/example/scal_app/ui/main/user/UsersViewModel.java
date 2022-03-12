package com.example.scal_app.ui.main.user;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.scal_app.data_layer.DataSource.ItemDataSource;
import com.example.scal_app.data_layer.DataSource.ItemDataSourceFactory;
import com.example.scal_app.data_layer.model.Item;
import com.example.scal_app.data_layer.repository.Repository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class UsersViewModel extends ViewModel {

    Context context;
    MutableLiveData<String> search=new MutableLiveData<>();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<PagedList<Item>> itemPagedList;
    public MutableLiveData<ItemDataSource> liveDataSource;
    Repository repository;







    public UsersViewModel(Context context) {
        this.context = context;

    }

    // TODO: Implement the ViewModel



    public void getAllUser(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(search.getValue());
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}