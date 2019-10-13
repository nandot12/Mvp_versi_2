package com.udacoding.pegawaiapp.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.home.data.UserItem;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    List<UserItem> userItems ;
    onItemUserClick itemUserClick ;

    public UserAdapter(List<UserItem> userItems, onItemUserClick itemUserClick) {
        this.userItems = userItems;
        this.itemUserClick = itemUserClick;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);


        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {


        holder.textname.setText(userItems.get(position).getStaffNama());
        holder.textemail.setText(userItems.get(position).getStaffEmail());

        //action klik item user
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemUserClick.onItem(userItems.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView textname ,textemail ;
        public MyHolder(@NonNull View itemView) {


            super(itemView);
            textname = itemView.findViewById(R.id.itemName);
            textemail = itemView.findViewById(R.id.itemEmail);
        }
    }

    public interface onItemUserClick{


        void onItem(UserItem nando);
    }
}
