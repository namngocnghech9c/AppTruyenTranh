package namtdph08817.android.apptruyentranh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import namtdph08817.android.apptruyentranh.ChiTietTruyenActivity;
import namtdph08817.android.apptruyentranh.LoginActivity;
import namtdph08817.android.apptruyentranh.R;
import namtdph08817.android.apptruyentranh.ThemTruyenActivity;

public class AccountFragment extends Fragment {
    private TextView tv_themtruyen;
    private Button btn_logout;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_themtruyen = view.findViewById(R.id.tv_btn_themTruyen);
        btn_logout = view.findViewById(R.id.tv_btn_logout);

        tv_themtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the activity
                Activity activity = getActivity();

                // Create an Intent to start the new activity
                Intent intent = new Intent(activity, ThemTruyenActivity.class);

                // Start the new activity
                activity.startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the activity
                Activity activity = getActivity();

                // Create an Intent to start the new activity
                Intent intent = new Intent(activity, LoginActivity.class);

                // Start the new activity
                activity.startActivity(intent);
            }
        });
    }
}