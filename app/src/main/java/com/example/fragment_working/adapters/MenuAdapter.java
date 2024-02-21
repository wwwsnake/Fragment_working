package com.example.fragment_working.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment_working.R;
import com.example.fragment_working.models.MenuItem;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public MenuAdapter(Context context, ArrayList<MenuItem> items) {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    private ArrayList<MenuItem> items;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;
    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemImg.setImageResource(item.getImg());
        holder.descriptionTxt.setText(item.getDescription());
        holder.titleTxt.setText(item.getTitle());
        holder.priceTxt.setText(String.valueOf(item.getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImg;
        public TextView titleTxt;
        public TextView descriptionTxt;
        public TextView priceTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.menu_img);
            titleTxt = itemView.findViewById(R.id.item_title);
            descriptionTxt = itemView.findViewById(R.id.item_description);
            priceTxt = itemView.findViewById(R.id.item_price);
        }
    }
}
