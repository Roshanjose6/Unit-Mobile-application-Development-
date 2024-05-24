package com.example.lostandfoundapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder> {
    Context context;
    List<ItemModel> list;
    LayoutInflater layoutInflater;
    DataBaseHelper databasehelper;

    public ListItemAdapter(Context context, List<ItemModel> list) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.databasehelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_card, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.type.setText(list.get(position).getItemType());
        holder.name.setText(list.get(position).getItemname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel i = list.get(position);
                Intent intent = new Intent(context, ItemsDetailsPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("item_id", i.getItemid());
                intent.putExtra("item_name", i.getItemname());
                intent.putExtra("item_desc", i.getItemdescription());
                intent.putExtra("item_date", i.getItemdate());
                intent.putExtra("item_loc", i.getItemlocation());
                intent.putExtra("item_type", i.getItemType());
                intent.putExtra("phone", i.getItemphone());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.item_type);
            name = itemView.findViewById(R.id.item_name);
        }
    }
}
