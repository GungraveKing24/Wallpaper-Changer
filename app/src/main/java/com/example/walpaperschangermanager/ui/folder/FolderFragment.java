package com.example.walpaperschangermanager.ui.folder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walpaperschangermanager.Database.DatabaseMethods;
import com.example.walpaperschangermanager.adapters.FolderAdapter;
import com.example.walpaperschangermanager.databinding.FragmentFolderBinding;
import com.example.walpaperschangermanager.models.Folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class FolderFragment extends Fragment {
    private FragmentFolderBinding binding;

    // Controles
    private RecyclerView recyclerView;
    private FolderAdapter adapter;
    private DatabaseMethods dbmethods;
    private ArrayList<Folder> folderList;
    private FloatingActionButton fabAdd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FolderViewModel fragmentViewModel = new ViewModelProvider(this).get(FolderViewModel.class);

        binding = FragmentFolderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar base de datos
        dbmethods = new DatabaseMethods(getContext());

        // Inicializar RecyclerView
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Cargar carpetas desde la base de datos
        loadFolders();

        // Inicializar botón flotante
        fabAdd = binding.fabAdd;
        fabAdd.setOnClickListener(v -> showAddFolderDialog());

        return root;
    }

    private void loadFolders() {
        folderList = dbmethods.getAllFolders(); // Obtener carpetas de la base de datos
        adapter = new FolderAdapter(folderList, getContext()); // Pasar la lista correctamente
        recyclerView.setAdapter(adapter);
    }

    private void showAddFolderDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        builder.setTitle("Nueva Carpeta");

        // Crear un EditText para ingresar el nombre de la carpeta
        final EditText input = new EditText(getContext());
        input.setHint("Nombre de la carpeta");

        builder.setView(input);
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String folderName = input.getText().toString().trim();
            if (!folderName.isEmpty()) {
                dbmethods.CreateFolder(folderName, ""); // Insertar sin imagen por ahora
                loadFolders(); // Recargar la lista de carpetas
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
