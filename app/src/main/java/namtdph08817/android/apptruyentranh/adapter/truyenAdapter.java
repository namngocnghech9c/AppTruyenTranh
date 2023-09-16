package namtdph08817.android.apptruyentranh.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import namtdph08817.android.apptruyentranh.NoiDungTruyenActivity;
import namtdph08817.android.apptruyentranh.R;
import namtdph08817.android.apptruyentranh.TruyenInterface;
import namtdph08817.android.apptruyentranh.model.TruyenModel;

public class truyenAdapter extends RecyclerView.Adapter<truyenAdapter.truyenViewHolder> {
    private Context context;
    private ArrayList<TruyenModel> arrayList;
    private TruyenInterface truyenInterface;

    public truyenAdapter(Context context, TruyenInterface truyenInterface) {
        this.context = context;
        this.truyenInterface = truyenInterface;
    }

    public void setData(ArrayList<TruyenModel> arrayList){
        this.arrayList= arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public truyenAdapter.truyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_truyen,parent,false);
        return new truyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull truyenAdapter.truyenViewHolder holder, int position) {
        TruyenModel model = arrayList.get(position);
        holder.tv_tenTruyen.setText(model.getNameTruyen());
        String url = NoiDungTruyenActivity.url +"uploads/"+model.getAnhBia();

        Glide.with(context).load(url).placeholder(R.drawable.ic_android_black_24dp).into(holder.img_anhbia);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("itemlist", model.getNameTruyen());
                truyenInterface.nextActivity();
                SharedPreferences preferences = context.getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("_id",model.get_id());
                editor.putString("nameTruyen",model.getNameTruyen());
                editor.putString("nameTacgia",model.getNameTacgia());
                editor.putInt("namXuatban",model.getNamXuatban());
                editor.putString("anhNoidung",model.getAnhNoidung());
                editor.putString("anhBia",url);
                editor.putString("mota",model.getMota());
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList.size() !=0){
            return arrayList.size();
        }
        return 0;
    }

    public class truyenViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenTruyen;
        private ImageView img_anhbia;
        public truyenViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenTruyen = itemView.findViewById(R.id.tv_tenTruyen);
            img_anhbia = itemView.findViewById(R.id.img_anhbia);
        }
    }
}
