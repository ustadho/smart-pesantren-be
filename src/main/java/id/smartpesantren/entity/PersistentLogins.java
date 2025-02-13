package id.smartpesantren.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins") // Nama tabel harus sesuai dengan yang Anda buat di database
public class PersistentLogins {

    @Id
    private String series;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUsed;

    // конструктор, геттеры и сеттеры
    public PersistentLogins() {}

    public PersistentLogins(String series, String username, String token, Date lastUsed) {
        this.series = series;
        this.username = username;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    // ... getter and setter
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
