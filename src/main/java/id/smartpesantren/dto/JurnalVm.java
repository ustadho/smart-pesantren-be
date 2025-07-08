package id.smartpesantren.dto;

public class JurnalVm {
    private String id;
    private String presenceId;
    private String materiPokok;
    private String kegiatan;
    private String penilaian;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(String presenceId) {
        this.presenceId = presenceId;
    }

    public String getMateriPokok() {
        return materiPokok;
    }

    public void setMateriPokok(String materiPokok) {
        this.materiPokok = materiPokok;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(String penilaian) {
        this.penilaian = penilaian;
    }
}
