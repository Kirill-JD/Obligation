package com.example.obligation.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.obligation.NewFileActivity;
import com.example.obligation.WordsClickListener;
import com.example.obligation.adapter.WordsListAdapter;
import com.example.obligation.dataBase.wordDAO.RoomDB;
import com.example.obligation.databinding.FragmentHomeBinding;
import com.example.obligation.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WordsListAdapter wordsListAdapter;
    private RoomDB database;
    private List<Word> words = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        database = RoomDB.getInstance(getActivity());
        words = database.mainDAO().getAll();

        updateRecycler(words);

        binding.buttonCreate.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), NewFileActivity.class);
            startActivityForResult(intent, 101);
        });
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            Word newWord = (Word) data.getSerializableExtra("word");
            database.mainDAO().insert(newWord);
            words.clear();
            words.addAll(database.mainDAO().getAll());
            wordsListAdapter.notifyDataSetChanged();
        }
    }

    private void updateRecycler(List<Word> words) {
        binding.rvHome.setHasFixedSize(true);
        binding.rvHome.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL));
        wordsListAdapter = new WordsListAdapter(getActivity(), words, wordsClickListener);
        binding.rvHome.setAdapter(wordsListAdapter);
    }

    private final WordsClickListener wordsClickListener = new WordsClickListener() {
        @Override
        public void onClick(Word word) {

        }

        @Override
        public void onLongClick(Word word, CardView cardView) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
