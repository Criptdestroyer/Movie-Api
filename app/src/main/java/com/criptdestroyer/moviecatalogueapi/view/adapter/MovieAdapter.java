package com.criptdestroyer.moviecatalogueapi.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.criptdestroyer.moviecatalogueapi.R;
import com.criptdestroyer.moviecatalogueapi.model.MovieItems;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.DataViewHolder> {
    private ArrayList<MovieItems> mData = new ArrayList<>();


    public void setData(ArrayList<MovieItems> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new DataViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtDescription;
        private ImageView imgPhoto;
        private View view;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            txtTitle = itemView.findViewById(R.id.tv_item_title);
            txtDate = itemView.findViewById(R.id.tv_item_date);
            txtDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

        void bind(MovieItems dataItems){
            txtTitle.setText(dataItems.getTitle());
            txtDate.setText(dataItems.getDate());
            txtDescription.setText(dataItems.getDescription());
            Glide.with(view).load("https://image.tmdb.org/t/p/w500"+dataItems.getPhoto()).placeholder(R.drawable.notfound).into(imgPhoto);

        }
    }
}
