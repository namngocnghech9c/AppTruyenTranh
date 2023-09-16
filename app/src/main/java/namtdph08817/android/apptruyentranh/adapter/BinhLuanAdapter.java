package namtdph08817.android.apptruyentranh.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namtdph08817.android.apptruyentranh.R;
import namtdph08817.android.apptruyentranh.binhLuan;
import namtdph08817.android.apptruyentranh.model.BinhLuanModel;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder> {
    private Context context;
    private ArrayList<BinhLuanModel> arrayList;
    private binhLuan binhLuanInterface;

    public BinhLuanAdapter(Context context, binhLuan binhLuanInterface) {
        this.context = context;
        this.binhLuanInterface = binhLuanInterface;
    }

    public void setData(ArrayList<BinhLuanModel> arrayList){
        this.arrayList= arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BinhLuanAdapter.BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_binhluan,parent,false);
        return new BinhLuanAdapter.BinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.BinhLuanViewHolder holder, int position) {
        BinhLuanModel model = arrayList.get(position);

        holder.tv_fullname.setText(model.getFullname());
        holder.tv_time.setText(model.getDate());
        holder.tv_noidung.setText(model.getNoidung());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                SharedPreferences preferences1 = context.getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("_idBinhLuan", model.get_id());
                editor.apply();

                SharedPreferences preferences = context.getSharedPreferences("TRUYEN.txt", Context.MODE_PRIVATE);
                String idUser = preferences.getString("idUser", "");
                Toast.makeText(context, model.getIdUser(), Toast.LENGTH_SHORT).show();
                if (model.getIdUser().equals(idUser)){
                    binhLuanInterface.chuyenChucnang();
                }
                return false;
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

    public class BinhLuanViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time,tv_fullname,tv_noidung;
        public BinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_fullname = itemView.findViewById(R.id.tv_fullname_item);
            tv_time = itemView.findViewById(R.id.tv_time_item);
            tv_noidung = itemView.findViewById(R.id.tv_noidungcmt_item);
        }
    }
}
