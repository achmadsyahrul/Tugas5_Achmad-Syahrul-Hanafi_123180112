package com.example.tugas5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

                //dialog
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
                dialog.show();

                EditText editFood = dialog.findViewById(R.id.edit_food);
                EditText editCalorie = dialog.findViewById(R.id.edit_calorie);
                EditText editProtein = dialog.findViewById(R.id.edit_protein);
                EditText editFat = dialog.findViewById(R.id.edit_fat);
                EditText editCarbohydrate = dialog.findViewById(R.id.edit_carbohydrate);
                Button btnUpdate = dialog.findViewById(R.id.btn_update);

                editFood.setText(food);
                editCalorie.setText(String.valueOf(calorie));
                editProtein.setText(String.valueOf(protein));
                editFat.setText(String.valueOf(fat));
                editCarbohydrate.setText(String.valueOf(carbohydrate));

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String upFood = editFood.getText().toString().trim();
                        myRoomDatabase.foodDAO().update(id, food, calorie, protein, fat, carbohydrate);
                        list.clear();
                        list.addAll(myRoomDatabase.foodDAO().getAllData());
                        notifyDataSetChanged();
                    }
                });
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
