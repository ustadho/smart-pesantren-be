package id.smartpesantren.service.dto;

import java.util.Date;

public class FileUploadVM {
    private String fileName;
    private String originalName;
    private String description;
    private Integer size;
    private Date uploadedAt;

    public FileUploadVM() {
    }

    public FileUploadVM(String fileName, String originalName, String description, Integer size, Date uploadedAt) {
        this.fileName = fileName;
        this.originalName = originalName;
        this.description = description;
        this.size = size;
        this.uploadedAt = uploadedAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
