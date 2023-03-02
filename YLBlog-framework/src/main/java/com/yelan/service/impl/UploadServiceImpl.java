package com.yelan.service.impl;

import com.yelan.domain.result.ResponseResult;
import com.yelan.enums.FilePathEnum;
import com.yelan.service.UploadService;
import com.yelan.strategy.UploadStrategy;
import com.yelan.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: UploadServiceImpl
 * @Description:
 * @Author: ChenKo
 * @Date: 2023/3/2
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public ResponseResult upload(MultipartFile multipartFile) throws IOException {
        return ResponseResult.okResult(uploadStrategyContext.executeUploadStrategy(multipartFile, FilePathEnum.PHOTO.getPath()));
    }
}
