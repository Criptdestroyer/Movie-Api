package com.criptdestroyer.moviecatalogueapi.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.criptdestroyer.moviecatalogueapi.R;
import com.criptdestroyer.moviecatalogueapi.model.MovieItems;
import com.criptdestroyer.moviecatalogueapi.viewmodel.DetailViewModel;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_FILM = "extra_film";
    private TextView tvTitle;
    private TextView tvDate;
    private ImageView imgPhoto;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        TextView tvDescription = findViewById(R.id.tv_detail_movie_description);
        TextView tvTrTitle = findViewById(R.id.tr_tv_movie_title);
        TextView tvTrDate = findViewById(R.id.tr_tv_movie_date);
        progressBar = findViewById(R.id.progressBarMovieDetail);
        tvTitle = findViewById(R.id.tv_detail_movie_title);
        tvDate = findViewById(R.id.tv_detail_movie_year);
        imgPhoto = findViewById(R.id.img_detail_movie_photo);

        MovieItems dataItem = getIntent().getParcelableExtra(EXTRA_FILM);

        if (dataItem != null) {
            if(dataItem.getDescription() != null){
                tvDescription.setText(dataItem.getDescription());
            }
            tvTrTitle.setText(dataItem.getTitle());
            tvTrDate.setText(dataItem.getDate());

            DetailViewModel detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
            detailViewModel.getDataMovie().observe(this, getMovie);
            detailViewModel.setMovie(dataItem.getId(), MainActivity.locale_language);
        }

        showLoading(true);
    }

    private Observer<MovieItems> getMovie = new Observer<MovieItems>() {
        @Override
        public void onChanged(MovieItems movieItems) {
            if(movieItems != null){
                tvTitle.setText(movieItems.getTitle());
                tvDate.setText(movieItems.getDate());
                Glide.with(DetailMovieActivity.this).load("https://image.tmdb.org/t/p/w500"+movieItems.getPhoto()).placeholder(R.drawable.notfound).error(R.drawable.notfound).into(imgPhoto);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
