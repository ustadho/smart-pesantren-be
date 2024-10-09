package id.smartpesantren.web;

import id.smartpesantren.service.FilesStorageService;
import id.smartpesantren.service.dto.FileUploadVM;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/files")
public class FileResouce {
    private final String SESSION_KEY_LAMPIRAN = "sessionKeyPathLampiran";

    @Autowired
    FilesStorageService storageService;

    private final Logger logger = LoggerFactory.getLogger(FileResouce.class);

    @PostMapping("/upload")
    public ResponseEntity<FileUploadVM> uploadFile(
            @RequestPart("data") FileUploadVM data,
            @RequestParam("file") MultipartFile file, HttpSession session) {
        String message = "";
        try {
            session.removeAttribute(SESSION_KEY_LAMPIRAN);
            FileUploadVM upload = storageService.save(data, file);

            message = "Uploaded the file successfully: " + upload.getFileName();
            session.setAttribute(SESSION_KEY_LAMPIRAN, upload.getFileName());
            return ResponseEntity.status(HttpStatus.OK).body(upload);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @Timed
    @RequestMapping(value = "/{img:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImageAsByteArray(@PathVariable("img") String img) {
        Resource file = storageService.load(img);
//        InputStream in = new BufferedInputStream(new FileInputStream(uploadFolder+File.separator+img));
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        IOUtils.copy(in, response.getOutputStream());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
