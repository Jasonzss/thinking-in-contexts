package com.jason.tics.file.service.impl;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class MinioOssService {
    @Autowired
    private MinioClient minioClient;

}
