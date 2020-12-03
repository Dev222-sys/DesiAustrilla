package com.app.desiaustralia.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.desiaustralia.R;
import com.app.desiaustralia.adapter.desiTv.DesiTvLatestAdapter;
import com.app.desiaustralia.adapter.desiTv.DesiTvTrendingAdapter;
import com.app.desiaustralia.model.desitv.DesiTVModel;
import com.app.desiaustralia.model.desitv.LatestData;
import com.app.desiaustralia.model.desitv.TrendingData;
import com.app.desiaustralia.retrofit.RetrofitHelper;
import com.app.desiaustralia.utility.NetworkUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DesiTVFragment extends Fragment {

    private RecyclerView latestRecycleView;
    private RecyclerView trendingRecylerView;
    private ProgressBar progressBar;



    public DesiTVFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desi_t_v, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        latestRecycleView = view.findViewById(R.id.latestRecycleView);
        latestRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        trendingRecylerView = view.findViewById(R.id.trendingRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        trendingRecylerView.setLayoutManager(layoutManager);
        trendingRecylerView.setNestedScrollingEnabled(true);
        progressBar = view.findViewById(R.id.progressBar);

        if (NetworkUtil.checkNetworkStatus(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().getDesiTvData(callback);
        } else {
            Toast.makeText(getActivity(), "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }

    }

    Callback<DesiTVModel> callback = new Callback<DesiTVModel>() {
        @Override
        public void onResponse(Call<DesiTVModel> call, Response<DesiTVModel> response) {
            try {
                progressBar.setVisibility(View.GONE);
                if (response.body().getStatus() == 1){

                    ArrayList<LatestData> latestDataArrayList  = response.body().getDesitvArrayList().get(0).getLatest();
                    DesiTvLatestAdapter latestAdapter = new DesiTvLatestAdapter(getActivity(), latestDataArrayList);
                    latestRecycleView.setAdapter(latestAdapter);

                    ArrayList<TrendingData> trendingDataArrayList = response.body().getDesitvArrayList().get(1).getTrending();
                    DesiTvTrendingAdapter trendingAdapter = new DesiTvTrendingAdapter(getActivity(), trendingDataArrayList);
                    trendingRecylerView.setAdapter(trendingAdapter);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<DesiTVModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };

    }