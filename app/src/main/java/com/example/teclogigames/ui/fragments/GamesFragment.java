package com.example.teclogigames.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.teclogigames.R;
import com.example.teclogigames.adapters.GamesAdapter;
import com.example.teclogigames.databinding.FragmentGamesBinding;
import com.example.teclogigames.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

public class GamesFragment extends Fragment implements SearchView.OnQueryTextListener {

    FragmentGamesBinding mBinding;
    MainViewModel mainViewModel;
    GamesAdapter gamesAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentGamesBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gamesAdapter = new GamesAdapter();
        mBinding.recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mBinding.recyclerview.setAdapter(gamesAdapter);

        setHasOptionsMenu(true);

        requestApiData(view);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.games_menu, menu);
        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.setMaxWidth(Integer.MAX_VALUE);
    }


    private void requestApiData(View view) {
        mainViewModel.getDealsResponse();
        mainViewModel.gamesResponse.observe(getViewLifecycleOwner(), response -> {

            switch (response.responseData) {
                case SUCCESS:
                    gamesAdapter.setData(response.data);
                    hideShimmerEffect();
                    hideViews();
                    break;

                case ERROR:
                    hideShimmerEffect();
                    showViews();
                    mBinding.errorTextView.setText(response.message);
                    Snackbar.make(view, response.message, Snackbar.LENGTH_LONG).show();
                    break;

                case EMPTY:
                    hideShimmerEffect();
                    showViews();
                    mBinding.errorTextView.setText(response.message);
                    break;

                case LOADING:
                    showShimmerEffect();
                    hideViews();
                    break;
            }
        });
    }


    private void showShimmerEffect() {
        mBinding.recyclerview.showShimmer();
    }

    private void hideShimmerEffect() {
        mBinding.recyclerview.hideShimmer();
    }

    private void hideViews() {
        mBinding.recyclerview.setVisibility(View.VISIBLE);
        mBinding.errorTextView.setVisibility(View.GONE);
        mBinding.lvAnimation.setVisibility(View.GONE);
    }

    private void showViews() {
        mBinding.recyclerview.setVisibility(View.GONE);
        mBinding.errorTextView.setVisibility(View.VISIBLE);
        mBinding.lvAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query != null) {
            searchGames(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    private void searchGames(String title) {
        mainViewModel.getGamesResponseSearch(title);
        mainViewModel.gamesResponse.observe(getViewLifecycleOwner(), response -> {

            switch (response.responseData) {
                case SUCCESS:
                    gamesAdapter.setData(response.data);
                    hideShimmerEffect();
                    hideViews();
                    mBinding.errorTextView.setText(response.message);
                    break;

                case ERROR:
                    hideShimmerEffect();
                    showViews();
                    mBinding.errorTextView.setText(response.message);
                    Toast.makeText(getContext(), response.message, Toast.LENGTH_LONG).show();
                    break;

                case EMPTY:
                    hideShimmerEffect();
                    showViews();
                    mBinding.errorTextView.setText(response.message);
                    break;

                case LOADING:
                    showShimmerEffect();
                    hideViews();
                    break;
            }
        });
    }
}