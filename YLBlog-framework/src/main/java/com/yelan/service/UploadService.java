package com.yelan.service;


import com.yelan.domain.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    ResponseResult upload(MultipartFile multipartFile) throws IOException;
}
