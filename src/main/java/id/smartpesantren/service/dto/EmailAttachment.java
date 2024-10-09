package id.smartpesantren.service.dto;

import java.io.File;

public class EmailAttachment {
    private String nama;
    private File file;

    public EmailAttachment(String nama, File file) {
        this.nama = nama;
        this.file = file;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String judul) {
        this.nama = judul;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
