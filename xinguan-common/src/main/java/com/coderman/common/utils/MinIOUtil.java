package com.coderman.common.utils;

import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class MinIOUtil {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        try {
            minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
            makeBucket(bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *	MultipartFile类型的文件上传ַ
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        return uploadStream(file.getInputStream(), bucket, file.getOriginalFilename());
    }

    public String upfileImage(File imageFile) throws IOException{
        String fileName = imageFile.getName();
        return uploadStream(FileUtils.openInputStream(imageFile), bucket, fileName);
    }

    public void deleteFile(String fileKey) throws Exception  {
        if (StringUtils.isEmpty(fileKey)) {
            return;
        }
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileKey)
                    .build();
            minioClient.removeObject(removeObjectArgs);
        }catch (Exception e){
            log.error("[MINIO] 文件删除失败", e);
        }

    }


    public String uploadStream(InputStream stream, String bucket, String filename){
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(filename)
                    .stream(stream, stream.available(), PutObjectArgs.MAX_PART_SIZE)
                    .build();
           minioClient.putObject(putObjectArgs);

           return minioClient.getObjectUrl(bucket, filename);
        } catch (Exception e) {
            log.error("[MINIO] 文件上传失败", e);
            return null;
        }finally {
            IOUtils.closeQuietly(stream);
        }
    }


    private void makeBucket(String bucketName){
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
            if (!minioClient.bucketExists(bucketExistsArgs)){
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
                minioClient.makeBucket(makeBucketArgs);

                SetBucketPolicyArgs bucketPolicyArgs = SetBucketPolicyArgs.builder().bucket(bucket).config(policyJson(bucket)).build();
                minioClient.setBucketPolicy(bucketPolicyArgs);
                log.info("[MINIO] Bucket创建成功, {}", bucketName);
            }
        }catch (Exception e){
            log.error("[MINIO] Bucket Builder异常", e);
        }
    }

    private String policyJson(String bucketName){
        StringBuilder policyJsonBuilder = new StringBuilder();
        policyJsonBuilder.append("{\n");
        policyJsonBuilder.append("    \"Statement\": [\n");
        policyJsonBuilder.append("        {\n");
        policyJsonBuilder.append("            \"Action\": [\n");
        policyJsonBuilder.append("                \"s3:GetBucketLocation\",\n");
        policyJsonBuilder.append("                \"s3:ListBucket\"\n");
        policyJsonBuilder.append("            ],\n");
        policyJsonBuilder.append("            \"Effect\": \"Allow\",\n");
        policyJsonBuilder.append("            \"Principal\": \"*\",\n");
        policyJsonBuilder.append("            \"Resource\": \"arn:aws:s3:::"+bucketName+"\"\n");
        policyJsonBuilder.append("        },\n");
        policyJsonBuilder.append("        {\n");
        policyJsonBuilder.append("            \"Action\": \"s3:GetObject\",\n");
        policyJsonBuilder.append("            \"Effect\": \"Allow\",\n");
        policyJsonBuilder.append("            \"Principal\": \"*\",\n");
        policyJsonBuilder.append("            \"Resource\": \"arn:aws:s3:::"+bucketName+"/*\"\n");
        policyJsonBuilder.append("        }\n");
        policyJsonBuilder.append("    ],\n");
        policyJsonBuilder.append("    \"Version\": \"2012-10-17\"\n");
        policyJsonBuilder.append("}\n");

        return policyJsonBuilder.toString();
    }
}
