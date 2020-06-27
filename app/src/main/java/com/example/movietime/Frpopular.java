package com.example.movietime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Frpopular extends Fragment {
    ProgressBar pgpopular;
    RecyclerView rcpopular;
    private static final String TAG = Frpopular.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_popular, container, false);

        pgpopular=root.findViewById(R.id.progressBar);


        return root;
    }
}
