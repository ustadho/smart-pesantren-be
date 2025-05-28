package id.smartpesantren.entity;

import org.checkerframework.common.reflection.qual.ClassBound;

import javax.persistence.*;

@Entity
@Table(name = "tahfidz_konversi")
public class TahfidzKonversi {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer noHalaman;

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

    public Integer getNoHalaman() {
        return noHalaman;
    }

    public void setNoHalaman(Integer noHalaman) {
        this.noHalaman = noHalaman;
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
}
