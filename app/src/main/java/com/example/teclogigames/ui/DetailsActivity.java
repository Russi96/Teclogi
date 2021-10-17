package com.example.teclogigames.ui;

import static com.example.teclogigames.utils.Constants.KEY_DETAILS_GAMES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.teclogigames.R;
import com.example.teclogigames.adapters.DetailsAdapter;
import com.example.teclogigames.databinding.ActivityDetailsBinding;
import com.example.teclogigames.databinding.ActivityMainBinding;
import com.example.teclogigames.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

public class DetailsActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    DetailsAdapter detailsAdapter;
    ActivityDetailsBinding mBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Bundle extras = getIntent().getExtras();
        String gameId = extras.getString(KEY_DETAILS_GAMES);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        detailsAdapter = new DetailsAdapter();
        mBinding.recyclerviewDetailsActivity.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mBinding.recyclerviewDetailsActivity.setAdapter(detailsAdapter);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainViewModel.getDetailsResponse(gameId);
        mainViewModel.gamesResponse.observe(this, response -> {
            switch (response.responseData) {
                case SUCCESS:
                    hideShimmerEffect();
                    Glide.with(this)
                            .load(response.detailsGames.getInfo().getThumb())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(mBinding.ivGameImageDetailsActivity);
                    mBinding.tvGameNameDetailsActivity.setText(response.detailsGames.getInfo().getTitle());
                    mBinding.tvGamePriceDetailsActivity.setText("$" + response.detailsGames.getCheapestPriceEver().getPrice());
                    detailsAdapter.setData(response.detailsGames.getDeals());
                    break;

                case ERROR:
                    hideShimmerEffect();
                    Snackbar.make(mBinding.getRoot(), response.message, Snackbar.LENGTH_LONG).show();
                    break;

                case LOADING:
                    showShimmerEffect();
                    break;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showShimmerEffect() {
        mBinding.recyclerviewDetailsActivity.showShimmer();
    }

    private void hideShimmerEffect() {
        mBinding.recyclerviewDetailsActivity.hideShimmer();
    }
}