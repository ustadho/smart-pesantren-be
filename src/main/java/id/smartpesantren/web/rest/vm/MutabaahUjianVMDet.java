package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;

public class MutabaahUjianVMDet {
    private String id;

    @NotNull
    private Integer halaman;

    private Integer ayat1;
    private String catatan1;
    private Integer ayat2;
    private String catatan2;
    private Integer ayat3;
    private String catatan3;
    private Integer ayat4;
    private String catatan4;
    private String catatan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull Integer getHalaman() {
        return halaman;
    }

    public void setHalaman(@NotNull Integer halaman) {
        this.halaman = halaman;
    }

    public Integer getAyat1() {
        return ayat1;
    }

    public void setAyat1(Integer ayat1) {
        this.ayat1 = ayat1;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public Integer getAyat2() {
        return ayat2;
    }

    public void setAyat2(Integer ayat2) {
        this.ayat2 = ayat2;
    }

    public String getCatatan2() {
        return catatan2;
    }

    public void setCatatan2(String catatan2) {
        this.catatan2 = catatan2;
    }

    public Integer getAyat3() {
        return ayat3;
    }

    public void setAyat3(Integer ayat3) {
        this.ayat3 = ayat3;
    }

    public String getCatatan3() {
        return catatan3;
    }

    public void setCatatan3(String catatan3) {
        this.catatan3 = catatan3;
    }

    public Integer getAyat4() {
        return ayat4;
    }

    public void setAyat4(Integer ayat4) {
        this.ayat4 = ayat4;
    }

    public String getCatatan4() {
        return catatan4;
    }

    public void setCatatan4(String catatan4) {
        this.catatan4 = catatan4;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
