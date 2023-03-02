package controller;


import com.yelan.domain.result.ResponseResult;
import com.yelan.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {
        try {
            return uploadService.upload(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传上传失败");
        }
    }
}