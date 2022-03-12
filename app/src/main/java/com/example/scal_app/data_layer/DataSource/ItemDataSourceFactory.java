package com.example.scal_app.data_layer.DataSource;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.scal_app.data_layer.model.Item;
import com.example.scal_app.data_layer.repository.Repository;


public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<ItemDataSource> itemLiveDataSource = new MutableLiveData<ItemDataSource>();
    String  search;




    public ItemDataSourceFactory(String  search) {
        this.search = search;

    }



    @Override
    public DataSource<Integer, Item> create() {
        ItemDataSource itemDataSource = new ItemDataSource(search);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<ItemDataSource> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
