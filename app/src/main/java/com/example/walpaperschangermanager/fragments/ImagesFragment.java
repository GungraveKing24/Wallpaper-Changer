package com.example.walpaperschangermanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walpaperschangermanager.R;
import com.example.walpaperschangermanager.adapters.ImageAdapter;

import java.util.Arrays;
import java.util.List;

public class ImagesFragment extends Fragment {
    private static final String ARG_FOLDER_NAME = "folder_name";
    private String folderName;

    public static ImagesFragment newInstance(String folderName) {
        ImagesFragment fragment = new ImagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOLDER_NAME, folderName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);

        if (getArguments() != null) {
            folderName = getArguments().getString(ARG_FOLDER_NAME);
        }

        TextView textView = view.findViewById(R.id.folderName);
        textView.setText("Imágenes en: " + folderName);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewImages);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // Simulación de imágenes
        List<String> images = Arrays.asList("Imagen 1", "Imagen 2", "Imagen 3");
        recyclerView.setAdapter(new ImageAdapter(images));

        if (savedInstanceState != null) {
            folderName = savedInstanceState.getString("folderName");
        }

        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("folderName", folderName);
    }
}
