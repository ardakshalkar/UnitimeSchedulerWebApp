package com.example.unitimescheduler.converter;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PathToFile {
    public static MultipartFile fileToMultipart(String path) throws IOException{
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);

        return new MockMultipartFile(
                file.getName(),
                file.getName(),
                "application/octet_stream",
                input
        );


    }
}
