package com.example.teclogigames.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teclogigames.R;
import com.example.teclogigames.databinding.DetailsRowLayoutBinding;
import com.example.teclogigames.databinding.GamesRowLayoutBinding;
import com.example.teclogigames.entities.Deal;
import com.example.teclogigames.entities.DetailsGames;
import com.example.teclogigames.entities.GamesItem;
import com.example.teclogigames.utils.GamesDiffUtil;

import java.util.Collections;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    List<Deal> deals = Collections.emptyList();
    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new DetailsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.details_row_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deal deal = deals.get(position);
        holder.binding.tvStoreId.setText("ID TIENDA " + deal.getStoreID());
        holder.binding.tvPriceStore.setText("Precio: $" + deal.getPrice());
        holder.binding.tvRetailPriceStore.setText("Precio al por menor: $" + deal.getRetailPrice());
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DetailsRowLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DetailsRowLayoutBinding.bind(itemView);

        }
    }


    public void setData(List<Deal> newData) {
        deals = newData;
        GamesDiffUtil<Deal> gamesDiffUtil = new GamesDiffUtil<>(deals, newData);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(gamesDiffUtil);
        diffUtilResult.dispatchUpdatesTo(this);
    }
}
