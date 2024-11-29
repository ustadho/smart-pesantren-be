package id.smartpesantren.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.stream.Stream;

import id.smartpesantren.repository.FileUploadRepository;
import id.smartpesantren.entity.FileUpload;
import id.smartpesantren.service.dto.FileUploadVM;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
//    String uploadFolder = System.getProperty("user.home")+ File.separator+"uploads";
    String uploadFolder = System.getProperty("user.dir")+ File.separator+"uploads";
    private final Logger logger = LoggerFactory.getLogger(FilesStorageServiceImpl.class);
    private final Path root = Paths.get(uploadFolder);

    @Autowired
    FileUploadRepository fileUploadRepository;

    @Override
    public void init() {
        try {
//            Files.createDirectory(root);
            new File(uploadFolder).mkdirs();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public FileUploadVM save(FileUploadVM vm, MultipartFile file) {
        String extension = tokenizer(file.getOriginalFilename(), ".");
        try {
            File dir = new File(uploadFolder);
            System.out.println("rootPath: " + uploadFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filename = UUID.randomUUID().toString() + "." + extension;
            byte[] bytes = file.getBytes();
//            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + filename);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            FileUpload fu = new FileUpload();
            fu.setFileName(filename);
            fu.setOriginalFileName(file.getOriginalFilename());
            fu.setSize(vm.getSize());
            fu.setDescription(vm.getDescription()==null || vm.getDescription().equalsIgnoreCase("")? file.getOriginalFilename(): vm.getDescription() );
            fileUploadRepository.save(fu);

            logger.info("Server File Location=" + serverFile.getAbsolutePath());
            return new FileUploadVM(filename, fu.getOriginalFileName(), fu.getDescription(), vm.getSize(), new Date());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//            return null;
        }
//        return null;
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = Paths.get(uploadFolder).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(Path.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    private String tokenizer(String filename, String token) {
        StringTokenizer st = new StringTokenizer(filename, token);
        String result = "";
        while (st.hasMoreTokens()) {
            result = st.nextToken();
        }
        return result;
    }
}