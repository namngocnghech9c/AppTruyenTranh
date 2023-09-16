package namtdph08817.android.apptruyentranh.model;

public class BinhLuanModel {
    private String _id, idUser,fullname, idTruyen, nameTruyen, date,noidung;

    public BinhLuanModel() {
    }

    public BinhLuanModel(String _id, String idUser, String fullname, String idTruyen, String nameTruyen, String date, String noidung) {
        this._id = _id;
        this.idUser = idUser;
        this.fullname = fullname;
        this.idTruyen = idTruyen;
        this.nameTruyen = nameTruyen;
        this.date = date;
        this.noidung = noidung;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getNameTruyen() {
        return nameTruyen;
    }

    public void setNameTruyen(String nameTruyen) {
        this.nameTruyen = nameTruyen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
