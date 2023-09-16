package namtdph08817.android.apptruyentranh.model;

public class Users {
    private String msg;
    private int status;
    private UsersModel data;

    public Users() {
    }

    public Users(String msg, int status, UsersModel data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UsersModel getData() {
        return data;
    }

    public void setData(UsersModel data) {
        this.data = data;
    }
}
