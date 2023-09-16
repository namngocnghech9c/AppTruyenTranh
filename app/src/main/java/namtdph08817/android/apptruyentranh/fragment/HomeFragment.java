package namtdph08817.android.apptruyentranh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import namtdph08817.android.apptruyentranh.APIclass;
import namtdph08817.android.apptruyentranh.ChiTietTruyenActivity;
import namtdph08817.android.apptruyentranh.NoiDungTruyenActivity;
import namtdph08817.android.apptruyentranh.R;
import namtdph08817.android.apptruyentranh.RegisterActivity;
import namtdph08817.android.apptruyentranh.TruyenInterface;
import namtdph08817.android.apptruyentranh.UserInterface;
import namtdph08817.android.apptruyentranh.adapter.truyenAdapter;
import namtdph08817.android.apptruyentranh.model.TruyenModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private truyenAdapter adapter;
    private ArrayList<TruyenModel> arrayList = new ArrayList<>();
    private Retrofit retrofit;
    private TruyenInterface truyenInterface;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_recyclerView_home);
        adapter = new truyenAdapter(getContext(), new TruyenInterface() {
            @Override
            public Call<List<TruyenModel>> getall() {
                return null;
            }

            @Override
            public void nextActivity() {
                // Get a reference to the activity
                Activity activity = getActivity();

                    // Create an Intent to start the new activity
                Intent intent = new Intent(activity, ChiTietTruyenActivity.class);

                // Start the new activity
                activity.startActivity(intent);
            }
        });

        retrofit = new Retrofit
                .Builder()
                .baseUrl(NoiDungTruyenActivity.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        truyenInterface = retrofit.create(TruyenInterface.class);
        Call<List<TruyenModel>> call = truyenInterface.getall();
        call.enqueue(new Callback<List<TruyenModel>>() {
            @Override
            public void onResponse(Call<List<TruyenModel>> call, Response<List<TruyenModel>> response) {
                List<TruyenModel> list = response.body();
                adapter.setData((ArrayList<TruyenModel>) list);
                LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TruyenModel>> call, Throwable t) {

            }
        });

    }
}