package com.example.scal_app.ui.main.user;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scal_app.Adapter.Adapter_AllOrderClient;
import com.example.scal_app.R;
import com.example.scal_app.data_layer.model.Item;
import com.example.scal_app.databinding.UsersFragmentBinding;
import com.example.scal_app.ui.main.MainViewModel;
import com.example.scal_app.ui.main.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment implements Adapter_AllOrderClient.OnItemClickListener{

    private UsersViewModel mViewModel;
    UsersFragmentBinding binding;
    private static final String TAG = "UsersFragment";
    List<Item> list;
    Adapter_AllOrderClient adapter_allOrderClient;




    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.users_fragment, null, false);
        mViewModel =
                ViewModelProviders.of(this, new UserViewModelFactory(getContext())).get(UsersViewModel.class);


        binding.setUsersViewModel(mViewModel);

        if (getArguments() !=null){
            UsersFragmentArgs args=UsersFragmentArgs.fromBundle(getArguments());
            mViewModel.search.setValue(args.getSearch());
            Log.d(TAG, "onCreateView: "+args.getSearch());
        }
        mViewModel.getAllUser();
        recy();
        getallusers();

        return binding.getRoot();

    }

    public void recy() {
        list = new ArrayList<>();
        adapter_allOrderClient = new Adapter_AllOrderClient(getContext());
        binding.recy.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.recy.setAdapter(adapter_allOrderClient);
        adapter_allOrderClient.setOnItemClickListener(this);

    }

    public void getallusers(){


        mViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> model_resultTransactions) {

                adapter_allOrderClient.submitList(model_resultTransactions);
                binding.shimmer.setVisibility(View.GONE);

            }

        });
    }


    @Override
    public void onItemClick(Item model_resultTransaction) {

    }
}