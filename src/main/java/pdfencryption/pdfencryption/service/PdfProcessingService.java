package pdfencryption.pdfencryption.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfProcessingService {

    public byte[] encryptPdf(InputStream inputStream, String password) throws IOException {
        PDDocument document = PDDocument.load(inputStream);
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, null);
        spp.setEncryptionKeyLength(128);
        spp.setPermissions(new org.apache.pdfbox.pdmodel.encryption.AccessPermission());
        document.protect(spp);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream.toByteArray();
    }
}
