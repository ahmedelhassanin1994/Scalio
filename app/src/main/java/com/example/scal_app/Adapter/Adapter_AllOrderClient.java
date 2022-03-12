package com.example.scal_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.scal_app.R;
import com.example.scal_app.data_layer.model.Item;

import org.jetbrains.annotations.NotNull;


public class Adapter_AllOrderClient extends PagedListAdapter<Item, Adapter_AllOrderClient.AllTransactionViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    Item client;



    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.getId()== newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public interface OnItemClickListener {
        void onItemClick(Item model_resultTransaction);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }




    public Adapter_AllOrderClient(Context mContext) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public AllTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.model_alluser, parent, false);
        return new Adapter_AllOrderClient.AllTransactionViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull AllTransactionViewHolder holder, int position) {
        Item  item = getItem(position);
        holder.login.setText(item.getLogin());
        holder.type.setText(item.getType());
        Glide.with(mContext).load(item.getAvatarUrl()).into(holder.img);


    }





    public class AllTransactionViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView login,type;
        ImageView img;

        public AllTransactionViewHolder(View view) {
            super(view);
            login=view.findViewById(R.id.login);
            type=view.findViewById(R.id.type);
            img=view.findViewById(R.id.img_profile);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            client=getItem(position);
            mListener.onItemClick(client);
        }

    }


}

