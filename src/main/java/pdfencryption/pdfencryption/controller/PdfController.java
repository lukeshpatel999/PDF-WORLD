package pdfencryption.pdfencryption.controller;

import org.springframework.http.HttpStatusCode;
import pdfencryption.pdfencryption.service.PdfProcessingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfProcessingService pdfProcessingService;

    public PdfController(PdfProcessingService pdfProcessingService) {
        this.pdfProcessingService = pdfProcessingService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500") // Allow frontend calls
    @PostMapping("/encrypt")
    public ResponseEntity<byte[]> encryptPdf(@RequestParam("file") MultipartFile file,
                                             @RequestParam("password") String password) {
        try {
            byte[] encryptedPdf = pdfProcessingService.encryptPdf(file.getInputStream(), password);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=encrypted.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(encryptedPdf);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/encrypt1")
    public ResponseEntity<String> encryptPdf() {

            return new ResponseEntity<String>(HttpStatusCode.valueOf(200));
    }
}
