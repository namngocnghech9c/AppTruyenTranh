package namtdph08817.android.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import namtdph08817.android.apptruyentranh.model.UsersModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView tv_register;
    private EditText ed_username, ed_pass;
    private CheckBox checkBox;
    private Retrofit retrofit;
    private UserInterface mInterface;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        ed_username = findViewById(R.id.ed_username);
        ed_pass = findViewById(R.id.ed_passwd);
        checkBox = findViewById(R.id.checkBox);

        SharedPreferences luutk = getSharedPreferences("LUUTK",Context.MODE_PRIVATE);
        Boolean checkluu = luutk.getBoolean("ischecked",true);
        if (checkluu){
            checkBox.setChecked(true);
            ed_username.setText(luutk.getString("user",""));
            ed_pass.setText(luutk.getString("pass",""));
        }
        ArrayList<UsersModel> arrayList = new ArrayList<>();


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objectRequest =new JsonObjectRequest(APIclass.URL+"users", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String username =object.getString("username");
                        String pass =object.getString("passwd");
                        String id =object.getString("_id");
                        String fullname =object.getString("fullname");
                        String email =object.getString("email");
                        Log.i("resVolley", id);
                        arrayList.add(new UsersModel(username,pass,email,fullname,id));
                    }


                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("error", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error volley", error.toString());
            }
        });
        requestQueue.add(objectRequest);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;

                if (ed_username.getText().length() == 0  || ed_pass.getText().length() ==0){
                    Toast.makeText(LoginActivity.this, "Không được để trống username và password", Toast.LENGTH_SHORT).show();
                    check = false;
                }
                for(int i=0; i<arrayList.size();i++){
                    UsersModel u = arrayList.get(i);
                    if (ed_username.getText().toString().equals(u.getUsername()) && ed_pass.getText().toString().equals(u.getPasswd())){Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        i = arrayList.size()+1;
                        SharedPreferences preferences = getSharedPreferences("TRUYEN.txt",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("idUser",u.get_id());
                        editor.putString("fullname",u.getFullname());
                        editor.apply();
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Username hoặc password không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
                if (checkBox.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("LUUTK",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("ischecked",checkBox.isChecked());
                    editor.putString("user",ed_username.getText().toString());
                    editor.putString("pass",ed_pass.getText().toString());
                    editor.apply();
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
}