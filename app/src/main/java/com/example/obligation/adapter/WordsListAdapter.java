package com.example.obligation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obligation.R;
import com.example.obligation.WordsClickListener;
import com.example.obligation.domain.Word;

import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter<WordsListHolder> {

    Context context;
    List<Word> list;

    public WordsListAdapter(Context context, List<Word> list, WordsClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    WordsClickListener listener;

    @NonNull
    @Override
    public WordsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordsListHolder(LayoutInflater.from(context).inflate(R.layout.words_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsListHolder holder, int position) {
        holder.textView.setText(list.get(position).getNameFile());

        holder.wordsList.setOnClickListener(view -> listener.onClick(list.get(holder.getAdapterPosition())));
        holder.wordsList.setOnLongClickListener(view -> {
            listener.onLongClick(list.get(holder.getAdapterPosition()), holder.wordsList);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
