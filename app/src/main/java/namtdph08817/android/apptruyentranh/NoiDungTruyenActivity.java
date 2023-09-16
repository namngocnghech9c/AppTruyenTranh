package namtdph08817.android.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class NoiDungTruyenActivity extends AppCompatActivity {
    private ImageView img_noidung;
    public static final String url = APIclass.URL;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_truyen);
        img_noidung = findViewById(R.id.img_noidungTruyen);
        SharedPreferences preferences = getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
        String a = preferences.getString("anhNoidung","");
        String url = APIclass.URL+"uploads/"+a;
        Glide.with(getApplicationContext()).load(url).placeholder(R.drawable.ic_android_black_24dp).into(img_noidung);
        img_noidung.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}