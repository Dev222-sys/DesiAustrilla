package com.app.desiaustralia.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.desiaustralia.storage.SharedPrefManager;
import com.bumptech.glide.Glide;
import com.app.desiaustralia.HomePageDetailsActivity;
import com.app.desiaustralia.R;
import com.app.desiaustralia.adapter.HomePageAdapter;
import com.app.desiaustralia.model.home.HomeDataSuccess;
import com.app.desiaustralia.model.home.TopNews;
import com.app.desiaustralia.retrofit.RetrofitHelper;
import com.app.desiaustralia.utility.NetworkUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView titleTv;
    private RecyclerView recylerView;
    private LinearLayout ll;
    String Devicetoken;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        imageView = view.findViewById(R.id.imageView);
        titleTv = view.findViewById(R.id.titleTv);
        recylerView = view.findViewById(R.id.recylerView);
        ll = view.findViewById(R.id.ll);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setNestedScrollingEnabled(true);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {

                            Devicetoken = task.getResult().getToken();
                            SharedPrefManager.getInstance(getActivity()).storeToken(Devicetoken);
                            Log.d("Devicetoken", Devicetoken);
                           // Toast.makeText(getActivity(), Devicetoken + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Devicetoken is not generated", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


        performLogin();

        if (NetworkUtil.checkNetworkStatus(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            RetrofitHelper.getInstance().getHomeData(callback, "Community News");
        } else {
            Toast.makeText(getActivity(), "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }
    }

    Callback<HomeDataSuccess> callback = new Callback<HomeDataSuccess>() {
        @Override
        public void onResponse(Call<HomeDataSuccess> call, Response<HomeDataSuccess> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1) {
                    ArrayList<TopNews> arrayList = response.body().getHomeArrayList().get(0).getArrayList();
                    Glide.with(getActivity()).load(arrayList.get(0).getUrl()
                    ).into(imageView);
                    String title = arrayList.get(0).getTitle();
                    String image = arrayList.get(0).getUrl();
                    String desc = arrayList.get(0).getDesc();
                    titleTv.setText(arrayList.get(0).getTitle());
                    imageView.setVisibility(View.VISIBLE);
                    titleTv.setVisibility(View.VISIBLE);
                    arrayList.remove(0);
                    HomePageAdapter adapter = new HomePageAdapter(getActivity(), arrayList);
                    recylerView.setAdapter(adapter);
                    ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), HomePageDetailsActivity.class);
                            intent.putExtra("title", title);
                            intent.putExtra("image", image);
                            intent.putExtra("desc", desc);
                            intent.putExtra("page", "home");
                            startActivity(intent);
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<HomeDataSuccess> call, Throwable t) {
            progressBar.setVisibility(View.GONE);

        }
    };

    public void performLogin() {


        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("android_id", android_id);
        //  Toast.makeText(getActivity(), android_id+"", Toast.LENGTH_1SHORT).show();

        String DeviceTokenn=SharedPrefManager.getInstance(getActivity()).getAccess_Token();
        //Toast.makeText(getActivity(), DeviceTokenn+"", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String Url = "https://www.desiaustralia.com/wp-json/user/devicetoken";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(getContext(), response.toString() + "", Toast.LENGTH_SHORT).show();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

             Toast.makeText(getActivity(), "Net", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("devicetoken_id",DeviceTokenn);
                return params;
            }
        };

        queue.add(stringRequest);
    }

}