package com.example.obligation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obligation.R;

public class WordsListHolder extends RecyclerView.ViewHolder {

    CardView wordsList;
    TextView textView;
    public WordsListHolder(@NonNull View itemView) {
        super(itemView);
        wordsList = itemView.findViewById(R.id.words_list);
        textView = itemView.findViewById(R.id.tv_words);
    }
}
