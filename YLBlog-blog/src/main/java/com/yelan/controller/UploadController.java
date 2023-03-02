package com.yelan.controller;



import com.yelan.domain.result.ResponseResult;
import com.yelan.service.UploadService;
import com.yelan.strategy.UploadStrategy;
import com.yelan.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import static com.yelan.enums.UploadModeEnum.getStrategy;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile multipartFile) throws IOException {
        return uploadService.upload(multipartFile);
    }
}
