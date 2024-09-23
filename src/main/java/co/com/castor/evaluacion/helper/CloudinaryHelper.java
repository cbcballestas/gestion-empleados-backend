package co.com.castor.evaluacion.helper;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CloudinaryHelper {

    @Value("${cloudinary.upload-folder}")
    private String uploadFolder;

    private final Cloudinary cloudinary;

    /**
     * Método que se encarga de guardar una imágen en cloudinary
     * @param multipartFile Archivo a guardar
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {

        File file = this.convertToFile(multipartFile);

        Map<String, Object> response = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "resource_type", "auto",
                "folder", "uploads"
        ));
        JSONObject json = new JSONObject(response);
        return json.getString("url");
    }

    /**
     * Método que se encarga de eliminar una imágen en cloudinary
     * @param imagePath Path de la imágen a eliminar
     * @throws IOException
     */
    public void deleteFile(String imagePath) throws IOException {

        String publicId = this.getPublicId(imagePath);
        String fullPath = uploadFolder.concat(publicId);

        log.info("Imágen a borrar: {}", fullPath);

        Map response = cloudinary.uploader().destroy(fullPath, ObjectUtils.emptyMap());
        log.info("Deleted image response: [{}]", response);
    }

    /**
     * Método que se encarga de convertir a File recibiendo un MultiPartFile
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        return file;
    }

    private String getPublicId(String imagePath) {
        String[] imagePathArr = imagePath.split("/");
        String publicIdWithExtension = imagePathArr[imagePathArr.length - 1];
        return publicIdWithExtension.substring(0, publicIdWithExtension.indexOf("."));
    }

}
