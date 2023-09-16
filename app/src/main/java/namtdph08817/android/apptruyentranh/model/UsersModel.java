package namtdph08817.android.apptruyentranh.model;

public class UsersModel {
    private String username,passwd, email, fullname,_id;

    public UsersModel() {
    }

    public UsersModel(String username, String passwd, String email, String fullname, String _id) {
        this.username = username;
        this.passwd = passwd;
        this.email = email;
        this.fullname = fullname;
        this._id = _id;
    }

    public UsersModel(String username, String passwd, String email, String fullname) {
        this.username = username;
        this.passwd = passwd;
        this.email = email;
        this.fullname = fullname;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
