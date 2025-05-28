package id.smartpesantren.entity;

import javax.persistence.*;

@Entity
@Table(name = "tahfidz_surat")
public class TahfidzSurat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "text")
    private String namaSuratIndo;

    @Column(nullable = false, columnDefinition = "text")
    private String namaSuratArab;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaSuratIndo() {
        return namaSuratIndo;
    }

    public void setNamaSuratIndo(String namaSuratIndo) {
        this.namaSuratIndo = namaSuratIndo;
    }

    public String getNamaSuratArab() {
        return namaSuratArab;
    }

    public void setNamaSuratArab(String namaSuratArab) {
        this.namaSuratArab = namaSuratArab;
    }
}
