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
import com.example.fragment_working.models.Model;

import java.util.ArrayList;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

        private OnItemClickListener listener;



    private LayoutInflater inflater;
    private ArrayList<Model> models;

    public ChooseAdapter(Context context, ArrayList<Model> models) {
        this.models = models;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.choose_item, parent, false); //Привязываемся к разметке и получаем элементы, которые выводим в модель
        return new ViewHolder(view);//передаем разметку в класс, который выведет в нее данные модели
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAdapter.ViewHolder holder, int position) {  //отвечает за связку можели и виджетов
        Model model = models.get(position);
        holder.textView.setText(model.getTitle());
        holder.imageView.setImageResource(model.getImg());
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
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder { //класс для управления виджетами
        ImageView imageView;  //виджет
        TextView textView;   //виджет

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.choose_img);
            textView = itemView.findViewById(R.id.choose_txt); //привязка кода к разметке
        }
    }
}
