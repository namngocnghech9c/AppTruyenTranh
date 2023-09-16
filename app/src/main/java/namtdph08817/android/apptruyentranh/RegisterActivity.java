package namtdph08817.android.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import namtdph08817.android.apptruyentranh.model.UsersModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;
    private EditText ed_username,ed_fullname, ed_email, ed_pass, ed_Repass;
    private static Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_REGEX =   "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private Retrofit retrofit;
    private UserInterface mInterface;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_fullname = findViewById(R.id.ed_fullname_register);
        ed_username = findViewById(R.id.ed_username_register);
        ed_email = findViewById(R.id.ed_email_register);
        ed_pass = findViewById(R.id.ed_passwd_register);
        ed_Repass = findViewById(R.id.ed_Re_passwd_register);
        btn_register = findViewById(R.id.btn_register);
        pattern = Pattern.compile(EMAIL_REGEX);

        //retrofit
        retrofit = new Retrofit
                .Builder()
                .baseUrl(APIclass.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mInterface = retrofit.create(UserInterface.class);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;
                if (ed_username.getText().length() < 5){
                    ed_username.setError("Username cần nhập ít nhất 5 ký tự");
                    check = false;
                }
                if (ed_fullname.getText().length() < 10){
                    ed_fullname.setError("Fullname cần nhập ít nhất 10 ký tự");
                    check = false;
                }
                if (!validate(ed_email.getText().toString())){
                    ed_email.setError("Email không đúng định dạng");
                    check = false;
                }
                if (ed_pass.getText().length()<1){
                    ed_pass.setError("Password không dược để trống");
                    check = false;
                }
                if (!ed_pass.getText().toString().equals(ed_Repass.getText().toString())){
                    ed_Repass.setError("Mật khẩu không trùng khớp !");
                    check = false;
                }
                if (check){
                    UsersModel user = new UsersModel();
                    user.setFullname(ed_fullname.getText().toString());
                    user.setUsername(ed_username.getText().toString());
                    user.setPasswd(ed_pass.getText().toString());
                    user.setEmail(ed_email.getText().toString());

                    Call<UsersModel> call = mInterface.postData(user);
                    call.enqueue(new Callback<UsersModel>() {
                        @Override
                        public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                            Toast.makeText(RegisterActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            Log.i("okkay","thanh cong r : "+response.body());
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }

                        @Override
                        public void onFailure(Call<UsersModel> call, Throwable t) {
                            Log.e("loi ne : ",t.toString());
                        }
                    });
                }
            }
        });
    }

    private boolean validate(String regex) {
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}