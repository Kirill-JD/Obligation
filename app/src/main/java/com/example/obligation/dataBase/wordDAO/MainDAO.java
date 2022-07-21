package com.example.obligation.dataBase.wordDAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.obligation.domain.Word;

import java.util.List;

@Dao
public interface MainDAO {

    @Insert (onConflict = REPLACE)
    void insert (Word word);

    @Query ("SELECT * FROM words")
    List<Word> getAll();

    @Delete
    void delete (Word words);
}
