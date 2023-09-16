package namtdph08817.android.apptruyentranh;

import java.util.List;

import namtdph08817.android.apptruyentranh.model.BinhLuanModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BinhLuanInterface {
    @POST("/binhluan")
    Call<BinhLuanModel> postData(@Body BinhLuanModel data);
    @GET("/binhluan/{idTruyen}")
    Call<List<BinhLuanModel>> getDataByIdTruyen(@Path("idTruyen") String idTruyen);
    @PUT("/binhluan/{id}")
    Call<BinhLuanModel> putData(@Path("id") String id,@Body BinhLuanModel data);
    @DELETE("/binhluan/{id}")
    Call<BinhLuanModel> deleteData(@Path("id") String id);
}
