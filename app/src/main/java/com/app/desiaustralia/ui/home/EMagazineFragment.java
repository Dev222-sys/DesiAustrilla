package com.app.desiaustralia.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.desiaustralia.R;
import com.app.desiaustralia.adapter.NewsLetterAdapter;
import com.app.desiaustralia.model.newsletter.DataItems;
import com.app.desiaustralia.model.newsletter.NewsLetterModel;
import com.app.desiaustralia.retrofit.RetrofitHelper;
import com.app.desiaustralia.utility.NetworkUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EMagazineFragment extends Fragment {
    private RecyclerView recylerView;
    private ProgressBar progressBar;


    public EMagazineFragment() {
        // Required empty public constructor
    }


    public static EMagazineFragment newInstance(String param1, String param2) {
        EMagazineFragment fragment = new EMagazineFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_e_magazine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = view.findViewById(R.id.recylerView);
        progressBar = view.findViewById(R.id.progressBar);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setNestedScrollingEnabled(true);

        if (NetworkUtil.checkNetworkStatus(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().getNewsLetter(callback, "monthly-e-magazine");
        } else {
            Toast.makeText(getActivity(), "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }
    }

    Callback<NewsLetterModel> callback = new Callback<NewsLetterModel>() {
        @Override
        public void onResponse(Call<NewsLetterModel> call, Response<NewsLetterModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){

                    ArrayList<DataItems> arrayList = response.body().getData().get(0).getArrayList();
                    NewsLetterAdapter adapter = new NewsLetterAdapter(getActivity(), arrayList, "magazine");
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<NewsLetterModel> call, Throwable t) {

            progressBar.setVisibility(View.GONE);
        }
    };


}