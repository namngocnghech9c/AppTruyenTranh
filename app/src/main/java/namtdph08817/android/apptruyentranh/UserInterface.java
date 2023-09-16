package namtdph08817.android.apptruyentranh;

import java.util.List;

import namtdph08817.android.apptruyentranh.model.Users;
import namtdph08817.android.apptruyentranh.model.UsersModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterface {
//    @GET("/users")
//    Call<UsersModel> getAllUser();
    @POST("/users")
    Call<UsersModel> postData(@Body UsersModel data);
    @GET("/users/")
    Call<List<UsersModel>> getall();
    @GET("/users/Login")
    Call<UsersModel> login();
}
