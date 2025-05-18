package id.smartpesantren.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "att_log")
@IdClass(AttLog.AttLogId.class)
public class AttLog {

    @Id
    @Column(length = 30, nullable = false)
    private String sn;

    @Id
    @Column(name = "scan_date", nullable = false)
    private LocalDateTime scanDate;

    @Id
    @Column(length = 32, nullable = false)
    private String pin;

    @Column(name = "verifymode", nullable = false)
    private int verifyMode;

    @Column(name = "inoutmode", nullable = false)
    private int inOutMode = 0;

    @Column(name = "device_ip", length = 50)
    private String deviceIp;

    // Getters and Setters
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public LocalDateTime getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDateTime scanDate) {
        this.scanDate = scanDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getVerifyMode() {
        return verifyMode;
    }

    public void setVerifyMode(int verifyMode) {
        this.verifyMode = verifyMode;
    }

    public int getInOutMode() {
        return inOutMode;
    }

    public void setInOutMode(int inOutMode) {
        this.inOutMode = inOutMode;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    // ID Class for composite key
    public static class AttLogId implements Serializable {
        private String sn;
        private LocalDateTime scanDate;
        private String pin;

        public AttLogId() {}

        public AttLogId(String sn, LocalDateTime scanDate, String pin) {
            this.sn = sn;
            this.scanDate = scanDate;
            this.pin = pin;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AttLogId)) return false;
            AttLogId that = (AttLogId) o;
            return Objects.equals(sn, that.sn) &&
                    Objects.equals(scanDate, that.scanDate) &&
                    Objects.equals(pin, that.pin);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sn, scanDate, pin);
        }
    }
}
