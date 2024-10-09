package id.smartpesantren.service;

import id.smartpesantren.service.dto.FileUploadVM;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface FilesStorageService {
    public void init();

    public FileUploadVM save(FileUploadVM vm, MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
