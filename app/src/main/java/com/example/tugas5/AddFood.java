package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas5.roomdatabase.FoodTable;
import com.example.tugas5.roomdatabase.MyRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddFood extends AppCompatActivity {

    private FloatingActionButton floatingBackButton;
    private EditText et_food, et_calorie, et_protein, et_fat, et_carbohydrate;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        floatingBackButton = findViewById(R.id.floatingBackButton);
        et_food = findViewById(R.id.et_food);
        et_calorie = findViewById(R.id.et_calorie);
        et_protein = findViewById(R.id.et_protein);
        et_fat = findViewById(R.id.et_fat);
        et_carbohydrate = findViewById(R.id.et_carbohydrate);
        btn_add = findViewById(R.id.btn_add);

        floatingBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFood.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    private void addFood(){
        String food = et_food.getText().toString().trim();
        String calorie_txt = et_calorie.getText().toString().trim();
        String protein_txt = et_protein.getText().toString().trim();
        String fat_txt = et_fat.getText().toString().trim();
        String carbohydrate_txt = et_carbohydrate.getText().toString().trim();

        double calorie = Double.parseDouble(calorie_txt);
        double protein = Double.parseDouble(protein_txt);
        double fat = Double.parseDouble(fat_txt);
        double carbohydrate = Double.parseDouble(carbohydrate_txt);
        FoodTable foodTable = new FoodTable();

        foodTable.setName(food);
        foodTable.setCalorie(calorie);
        foodTable.setProtein(protein);
        foodTable.setFat(fat);
        foodTable.setCarbohydrate(carbohydrate);

        MyRoomDatabase.getInstance(getApplicationContext()).foodDAO().insert(foodTable);

        et_food.setText("");
        et_calorie.setText("");
        et_protein.setText("");
        et_fat.setText("");
        et_carbohydrate.setText("");

        Toast.makeText(this, "Data telah ditambahkan", Toast.LENGTH_SHORT).show();

    }
}