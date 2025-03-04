package com.example.walpaperschangermanager.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walpaperschangermanager.R;
import com.example.walpaperschangermanager.models.Folder;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {
    private ArrayList<Folder> folderList;
    private Context context;

    public FolderAdapter(ArrayList<Folder> folderList, Context context) {
        this.folderList = folderList;
        this.context = context;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        Folder folder = folderList.get(position);
        holder.name.setText(folder.getName());

        if (!folder.getImagePath().isEmpty()) {
            holder.imageView.setImageURI(Uri.parse(folder.getImagePath()));
        } else {
            holder.imageView.setImageResource(R.drawable.default_folder);
        }
    }

    @Override
    public int getItemCount() { return folderList.size(); }

    static class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.folder_name);
            imageView = itemView.findViewById(R.id.folder_image);
        }
    }
}
