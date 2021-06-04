package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tugas5.roomdatabase.FoodTable;
import com.example.tugas5.roomdatabase.MyRoomDatabase;

import java.util.List;

public class UpdateFood extends AppCompatActivity {

    String food;
    int id;
    double calorie, protein, fat, carbohydrate;
    EditText editFood, editCalorie, editProtein, editFat, editCarbohydrate;
    Button btnUpdate;
    List<FoodTable> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MyRoomDatabase myRoomDatabase = MyRoomDatabase.getInstance(this);

        FoodTable foodTable = new FoodTable();

        editFood = findViewById(R.id.edit_food);
        editCalorie = findViewById(R.id.edit_calorie);
        editProtein = findViewById(R.id.edit_protein);
        editFat = findViewById(R.id.edit_fat);
        editCarbohydrate = findViewById(R.id.edit_carbohydrate);
        btnUpdate = findViewById(R.id.btn_update);

        getIncomingExtra();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = editFood.getText().toString().trim();
                String calorie_txt = editCalorie.getText().toString().trim();
                String protein_txt = editProtein.getText().toString().trim();
                String fat_txt = editFat.getText().toString().trim();
                String carbohydrate_txt = editCarbohydrate.getText().toString().trim();

                double calorie = Double.parseDouble(calorie_txt);
                double protein = Double.parseDouble(protein_txt);
                double fat = Double.parseDouble(fat_txt);
                double carbohydrate = Double.parseDouble(carbohydrate_txt);

                foodTable.setName(food);
                foodTable.setCalorie(calorie);
                foodTable.setProtein(protein);
                foodTable.setFat(fat);
                foodTable.setCarbohydrate(carbohydrate);

                myRoomDatabase.getInstance(getApplicationContext()).foodDAO().update(id, food, calorie, protein, fat, carbohydrate);
                list.clear();
                list.addAll(myRoomDatabase.getInstance(getApplicationContext()).foodDAO().getAllData());
            }
        });
    }

    private void getIncomingExtra(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("food") && getIntent().hasExtra("cal") && getIntent().hasExtra("protein" ) && getIntent().hasExtra("fat") && getIntent().hasExtra("carbo")){
            int id = getIntent().getIntExtra("id", 0);
            String food = getIntent().getStringExtra("food");
            double calorie = getIntent().getDoubleExtra("cal", 0);
            double protein = getIntent().getDoubleExtra("protein", 0);
            double fat = getIntent().getDoubleExtra("fat", 0);
            double carbohydrate = getIntent().getDoubleExtra("carbo", 0);

            setDataActivity(id, food, calorie, protein, fat, carbohydrate);
        }
    }

    private void setDataActivity(int id, String food, double calorie, double protein, double fat, double carbohydrate){
        this.id=id;
        this.food = food;
        this.calorie = calorie;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        editFood.setText(food);
        editCalorie.setText(String.valueOf(calorie));
        editProtein.setText(String.valueOf(protein));
        editFat.setText(String.valueOf(fat));
        editCarbohydrate.setText(String.valueOf(carbohydrate));
    }
}