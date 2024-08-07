package com.clodi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class AmazonFileStoreService {

    private final AmazonS3 s3;

    @Autowired public AmazonFileStoreService(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path, String filename, Optional<Map<String, String>> optionalMetadata, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            s3.putObject(path, filename, inputStream, objectMetadata);
        }
        catch (AmazonServiceException e) {
            throw new IllegalStateException("failed to store file to s3", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        }
        catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("cant download file", e);
        }
    }

}
