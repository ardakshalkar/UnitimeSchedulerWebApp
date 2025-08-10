package com.example.unitimescheduler.feign;

import com.example.unitimescheduler.config.FeignMultipartSupportConfig;
import com.example.unitimescheduler.models.FileDto;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Encoder;
import java.security.spec.EncodedKeySpec;

@FeignClient(
        name = "file-service",
        url = "localhost:8081/file",
        configuration = FeignMultipartSupportConfig.class
)
public interface FileServiceClient {
    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);
}


