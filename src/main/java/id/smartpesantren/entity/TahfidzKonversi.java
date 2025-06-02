package id.smartpesantren.entity;

import javax.persistence.*;

@Entity
@Table(name = "tahfidz_konversi")
public class TahfidzKonversi {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer jmlHalaman;

    @Column(nullable = false)
    private Integer konvJuz;

    @Column(nullable = false)
    private Integer konvHalaman;

    @Column(columnDefinition = "text")
    private String namaSurat;

    @Column(columnDefinition = "text")
    private String namaSuratArab;

    @Column(nullable = false)
    private Integer jmlAyat;

    @Column(nullable = false)
    private Integer noSurat;

    public TahfidzKonversi() {
    }

    public TahfidzKonversi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJmlHalaman() {
        return jmlHalaman;
    }

    public void setJmlHalaman(Integer jmlHalaman) {
        this.jmlHalaman = jmlHalaman;
    }

    public Integer getKonvJuz() {
        return konvJuz;
    }

    public void setKonvJuz(Integer konvJuz) {
        this.konvJuz = konvJuz;
    }

    public Integer getKonvHalaman() {
        return konvHalaman;
    }

    public void setKonvHalaman(Integer konvHalaman) {
        this.konvHalaman = konvHalaman;
    }

    public String getNamaSurat() {
        return namaSurat;
    }

    public void setNamaSurat(String namaSurat) {
        this.namaSurat = namaSurat;
    }

    public String getNamaSuratArab() {
        return namaSuratArab;
    }

    public void setNamaSuratArab(String namaSuratArab) {
        this.namaSuratArab = namaSuratArab;
    }

    public Integer getJmlAyat() {
        return jmlAyat;
    }

    public void setJmlAyat(Integer jmlAyat) {
        this.jmlAyat = jmlAyat;
    }

    public Integer getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(Integer noSurat) {
        this.noSurat = noSurat;
    }

    public static String getDescription(TahfidzKonversi tk) {
        return "Hal: "+tk.getId() +" / Target: " + tk.getJmlHalaman() + " Hal. ("+ tk.getKonvJuz() +" Juz "+tk.getKonvHalaman() +" Hal.)";
    }
}
