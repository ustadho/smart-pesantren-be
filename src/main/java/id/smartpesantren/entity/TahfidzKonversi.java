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

    @Column(nullable = false)
    private Integer awalNoSurat;

    @Column(columnDefinition = "text")
    private String awalNamaSurat;

    @Column(nullable = false)
    private Integer awalAyatSurat;

    @Column(nullable = false)
    private Integer akhirNoSurat;

    @Column(columnDefinition = "text")
    private String akhirNamaSurat;

    @Column(nullable = false)
    private Integer akhirAyatSurat;

    private Integer juz;

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

    public String getAwalNamaSurat() {
        return awalNamaSurat;
    }

    public void setAwalNamaSurat(String awalNamaSurat) {
        this.awalNamaSurat = awalNamaSurat;
    }

    public Integer getAwalNoSurat() {
        return awalNoSurat;
    }

    public void setAwalNoSurat(Integer awalNoSurat) {
        this.awalNoSurat = awalNoSurat;
    }

    public String getAkhirNamaSurat() {
        return akhirNamaSurat;
    }

    public void setAkhirNamaSurat(String akhirNamaSurat) {
        this.akhirNamaSurat = akhirNamaSurat;
    }

    public Integer getAkhirAyatSurat() {
        return akhirAyatSurat;
    }

    public void setAkhirAyatSurat(Integer akhirAyatSurat) {
        this.akhirAyatSurat = akhirAyatSurat;
    }

    public Integer getAwalAyatSurat() {
        return awalAyatSurat;
    }

    public void setAwalAyatSurat(Integer awalAyatSurat) {
        this.awalAyatSurat = awalAyatSurat;
    }

    public Integer getAkhirNoSurat() {
        return akhirNoSurat;
    }

    public void setAkhirNoSurat(Integer akhirNoSurat) {
        this.akhirNoSurat = akhirNoSurat;
    }

    public static String getDescription(TahfidzKonversi tk) {
        return "Hal: "+tk.getId() +" / Target: " + tk.getJmlHalaman() + " Hal. ("+ tk.getKonvJuz() +" Juz "+tk.getKonvHalaman() +" Hal.)";
    }

    public Integer getJuz() {
        return juz;
    }

    public void setJuz(Integer juz) {
        this.juz = juz;
    }
}
