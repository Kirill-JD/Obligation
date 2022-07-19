package com.example.obligation;

import androidx.cardview.widget.CardView;

import com.example.obligation.domain.Word;

public interface WordsClickListener {
    void onClick (Word word);
    void onLongClick (Word word, CardView cardView);
}
