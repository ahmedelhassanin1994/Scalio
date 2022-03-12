package com.example.scal_app.ui.main;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.scal_app.R;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public MutableLiveData<String> search = new MutableLiveData<>();
    Context context;



    public MainViewModel(Context context) {
    this.context=context;
    }


    public  void  go_search(){

        if (TextUtils.isEmpty(search.getValue())) {
            Toast.makeText(context, "Please type your search word", Toast.LENGTH_SHORT).show();
            return;
        }else {

            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
            MainFragmentDirections.ActionMainFragmentToUsersFragment action = MainFragmentDirections.actionMainFragmentToUsersFragment();
            action.setSearch(search.getValue());
            navController.navigate(action);
        }
    }
}