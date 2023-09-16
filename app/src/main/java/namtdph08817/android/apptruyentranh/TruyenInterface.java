package namtdph08817.android.apptruyentranh;

import java.util.List;

import namtdph08817.android.apptruyentranh.model.TruyenModel;
import namtdph08817.android.apptruyentranh.model.UsersModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TruyenInterface {
    @GET("/truyen")
    Call<List<TruyenModel>> getall();


    void nextActivity();
}
