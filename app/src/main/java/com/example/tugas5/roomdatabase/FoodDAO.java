package com.example.tugas5.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FoodTable foodTable);

    @Query("UPDATE food_table SET name = :food , calorie = :calorie, protein = :protein, fat = :fat, carbohydrate = :carbohydrate WHERE id = :id")
    void update(int id, String food, double calorie, double protein, double fat, double carbohydrate);

    @Delete()
    void delete(FoodTable foodTable);

    @Query("SELECT * FROM food_table")
    List<FoodTable> getAllData();
}
