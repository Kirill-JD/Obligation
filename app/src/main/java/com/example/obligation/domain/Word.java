package com.example.obligation.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;
import java.io.Serializable;

@Entity (tableName = "words")
public class Word implements Serializable {

    @PrimaryKey(autoGenerate = true)
    long id = 0;

    @ColumnInfo (name = "file")
    byte[] file;

    @ColumnInfo (name = "name_file")
    String nameFile;

    public Word(String nameFile, byte[] file) {
        this.nameFile = nameFile;
        this.file = file;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
