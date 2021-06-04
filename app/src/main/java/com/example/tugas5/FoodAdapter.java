package com.example.tugas5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas5.roomdatabase.FoodTable;
import com.example.tugas5.roomdatabase.MyRoomDatabase;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    //    Activity activity;
    Context context;
    List<FoodTable> list;
    MyRoomDatabase myRoomDatabase;

    public FoodAdapter(Context context, List<FoodTable> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        FoodTable data = list.get(position);
        myRoomDatabase = MyRoomDatabase.getInstance(context);


        holder.food.setText(list.get(position).getName());
        holder.calorie.setText(String.valueOf(list.get(position).getCalorie()));
        holder.protein.setText(String.valueOf(list.get(position).getProtein()));
        holder.fat.setText(String.valueOf(list.get(position).getFat()));
        holder.carbohydrate.setText(String.valueOf(list.get(position).getCarbohydrate()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTable data = list.get(holder.getAdapterPosition());
                int id = data.getId();
                String food = data.getName();
                double calorie = data.getCalorie();
                double protein = data.getProtein();
                double fat = data.getFat();
                double carbohydrate = data.getCarbohydrate();

                Intent intent = new Intent(v.getContext(), UpdateFood.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", id);
                intent.putExtra("food", food);
                intent.putExtra("cal", calorie);
                intent.putExtra("protein", protein);
                intent.putExtra("fat", fat);
                intent.putExtra("carbo", carbohydrate);
                context.startActivity(intent);
                notifyDataSetChanged();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTable data = list.get(holder.getAdapterPosition());
                myRoomDatabase.foodDAO().delete(data);
                list = myRoomDatabase.foodDAO().getAllData();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView food, calorie, protein, fat, carbohydrate;
        LinearLayout linearLayout;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            food = itemView.findViewById(R.id.tv_food);
            calorie = itemView.findViewById(R.id.tv_calorie);
            protein = itemView.findViewById(R.id.tv_protein);
            fat = itemView.findViewById(R.id.tv_fat);
            carbohydrate = itemView.findViewById(R.id.tv_carbohydrate);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
