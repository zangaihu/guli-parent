package com.sh.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.sh.oss.service.FileService;
import com.sh.oss.util.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传服务
 *
 * @author sunhu
 * @date 2021/4/25 16:32
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {



    @Override
    public String upload(MultipartFile file) {

        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
//        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl=null;


        try {
            // 构建oss
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 不存在Bucket则创建
            if (!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream inputStream=file.getInputStream();

            String filePath=new DateTime().toString("yyyy/MM/dd");
            String original=file.getOriginalFilename();
            String fileName= UUID.randomUUID().toString();
            String fileType=original.substring(original.lastIndexOf("."));

            String newName=fileName+fileType;


            String fileUrl = filePath + "/" + newName;

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;

        }catch (Exception e){
            log.error("上传文件异常");
        }

        return uploadUrl;
    }
}
