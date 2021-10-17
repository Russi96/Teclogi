package com.example.teclogigames.adapters;

import static com.example.teclogigames.utils.Constants.KEY_DETAILS_GAMES;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.teclogigames.R;
import com.example.teclogigames.databinding.GamesRowLayoutBinding;
import com.example.teclogigames.entities.GamesItem;
import com.example.teclogigames.ui.DetailsActivity;
import com.example.teclogigames.utils.GamesDiffUtil;

import java.util.Collections;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    List<GamesItem> gamesList = Collections.emptyList();
    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.games_row_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GamesItem games = gamesList.get(position);
        Glide.with(mContext)
                .load(games.getThumb())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.binding.ivGameImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra(KEY_DETAILS_GAMES, games.getGameID());
            v.getContext().startActivity(intent);
        });

        if (games.getTitle() != null) {
            holder.binding.tvGameName.setText(games.getTitle());
        } else {
            holder.binding.tvGameName.setText(games.getExternal());
        }

        if (games.getNormalPrice() != null) {
            holder.binding.tvGamePrice.setText("$" + games.getNormalPrice());
        } else {
            holder.binding.tvGamePrice.setText("$" + games.getCheapest());
        }


    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        GamesRowLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = GamesRowLayoutBinding.bind(itemView);

        }
    }

    public void setData(List<GamesItem> newData) {
        gamesList = newData;
        GamesDiffUtil<GamesItem> gamesDiffUtil = new GamesDiffUtil<>(gamesList, newData);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(gamesDiffUtil);
        diffUtilResult.dispatchUpdatesTo(this);
    }

}
