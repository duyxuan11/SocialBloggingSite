package org.example.socialbloggingsite.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Slf4j
public class CloudUtils {
    Cloudinary cloudinary ;

    public CompletableFuture<String> uploadFileAsync(MultipartFile image) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                //log.info(uploadResult.toString());
                return uploadResult.get("secure_url").toString();
            } catch (IOException e) {
                throw new CustomRunTimeException(ErrorCode.FILE_NOT_FOUND);
            }
        });
    }

    public void deleteFile(String imgUrl) {
        int lastSlashIndex = imgUrl.lastIndexOf("/");
        int lastDocIndex = imgUrl.lastIndexOf(".");
        String publicID = imgUrl.substring(lastSlashIndex+1,lastDocIndex);
        try {
            var result = cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());
            result.get("result");
        } catch (IOException e) {
            throw  new CustomRunTimeException(ErrorCode.DELETE_FILE_NOT_SUCCESS);
        }
    }
    public void deleteFileAsync(String imgUrl) {
        int lastSlashIndex = imgUrl.lastIndexOf("/");
        int lastDocIndex = imgUrl.lastIndexOf(".");
        String publicID = imgUrl.substring(lastSlashIndex+1,lastDocIndex);
        CompletableFuture.supplyAsync( () -> {
            try {
                var result = cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());
                result.get("result");
            } catch (IOException e) {
                throw new CustomRunTimeException(ErrorCode.DELETE_FILE_NOT_SUCCESS);
            }
            return null;
        } );

    }
}
