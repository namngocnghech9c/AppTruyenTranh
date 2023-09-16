package namtdph08817.android.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import namtdph08817.android.apptruyentranh.adapter.BinhLuanAdapter;
import namtdph08817.android.apptruyentranh.model.BinhLuanModel;
import namtdph08817.android.apptruyentranh.model.TruyenModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietTruyenActivity extends AppCompatActivity {
    private ImageView img_anhbia, btn_send;
    private Button btn_doctruyen;
    private TextView tv_tenTruyen, tv_tacgia, tv_namXB, tv_mota;
    private EditText ed_cmt;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private BinhLuanInterface mInterface;
    private BinhLuanAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);
        //anh xa
        img_anhbia = findViewById(R.id.img_anhbia_ct);
        btn_send = findViewById(R.id.img_send_cmt);
        btn_doctruyen = findViewById(R.id.btn_doctruyen);
        tv_tenTruyen = findViewById(R.id.tv_tenTruyen_ct);
        tv_tacgia = findViewById(R.id.tv_tenTacGia_ct);
        tv_namXB = findViewById(R.id.tv_namxuatban_ct);
        tv_mota = findViewById(R.id.tv_mota_ct);
        ed_cmt = findViewById(R.id.ed_binhluan_ct);
        recyclerView = findViewById(R.id.id_recyclerView_binhluan);


        retrofit = new Retrofit
                .Builder()
                .baseUrl(APIclass.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mInterface = retrofit.create(BinhLuanInterface.class);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String time = sdf.format(new Date());

        SharedPreferences preferences = getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
        String tenTruyen = preferences.getString("nameTruyen", "");
        String tacGia = preferences.getString("nameTacgia", "");
        int namXB = preferences.getInt("namXuatban", 0);
        String mota = preferences.getString("mota", "");
        String idUser = preferences.getString("idUser", "");
        String urlimg = preferences.getString("anhBia", "");
        String _id = preferences.getString("_id", "");
        String fullname = preferences.getString("fullname", "");

        adapter = new BinhLuanAdapter(getApplicationContext(), new binhLuan() {
            @Override
            public void chuyenChucnang() {
                Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.dialog_sua_cmt);
                EditText ed_sua_cmt = dialog.findViewById(R.id.ed_sua_cmt);
                Button btn_ok = dialog.findViewById(R.id.btn_edit);
                Button btn_huy = dialog.findViewById(R.id.btn_huy_edit);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences preferences = getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
                        String idBinhluan = preferences.getString("_idBinhLuan", "");
                        BinhLuanModel model1 = new BinhLuanModel();
                        model1.setIdUser(idUser);
                        model1.setFullname(fullname);
                        model1.setIdTruyen(_id);
                        model1.setNameTruyen(tenTruyen);
                        model1.setDate(time);
                        model1.setNoidung(ed_sua_cmt.getText().toString());
                        Call<BinhLuanModel> call2 = mInterface.putData(idBinhluan,model1);
                        call2.enqueue(new Callback<BinhLuanModel>() {
                            @Override
                            public void onResponse(Call<BinhLuanModel> call, Response<BinhLuanModel> response) {
                                Toast.makeText(ChiTietTruyenActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                onLoadCmt(_id);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<BinhLuanModel> call, Throwable t) {

                            }
                        });
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        Glide.with(getApplicationContext()).load(urlimg).placeholder(R.drawable.ic_android_black_24dp).into(img_anhbia);
        tv_tenTruyen.setText(tenTruyen);
        tv_tacgia.setText("Tác giả : " + tacGia);
        tv_namXB.setText("Năm xuất bản : " + namXB);
        tv_mota.setText("Mô tả : " + mota);




        onLoadCmt(_id);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinhLuanModel model = new BinhLuanModel();
                model.setIdUser(idUser);
                model.setFullname(fullname);
                model.setIdTruyen(_id);
                model.setNameTruyen(tenTruyen);
                model.setDate(time);
                model.setNoidung(ed_cmt.getText().toString());
                Log.i("binhluanmodel",idUser +", " + _id+", " + tenTruyen +", "+ time+", "  + ed_cmt.getText().toString());
                Call<BinhLuanModel> call = mInterface.postData(model);
                call.enqueue(new Callback<BinhLuanModel>() {
                    @Override
                    public void onResponse(Call<BinhLuanModel> call, Response<BinhLuanModel> response) {
                        Toast.makeText(ChiTietTruyenActivity.this, "Thêm bình luận thành công", Toast.LENGTH_SHORT).show();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ed_cmt.getWindowToken(), 0);
                        onLoadCmt(_id);
                        ed_cmt.setText("");
                        onResume();
                    }

                    @Override
                    public void onFailure(Call<BinhLuanModel> call, Throwable t) {
                        Toast.makeText(ChiTietTruyenActivity.this, "lôi : " + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_doctruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietTruyenActivity.this, NoiDungTruyenActivity.class));
            }
        });


    }

    private void onLoadCmt(String id) {
        Call<List<BinhLuanModel>> call1 = mInterface.getDataByIdTruyen(id);
        call1.enqueue(new Callback<List<BinhLuanModel>>() {
            @Override
            public void onResponse(Call<List<BinhLuanModel>> call, Response<List<BinhLuanModel>> response) {
                List<BinhLuanModel> list = response.body();
                adapter.setData((ArrayList<BinhLuanModel>) list);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<BinhLuanModel>> call, Throwable t) {

            }
        });
    }


}