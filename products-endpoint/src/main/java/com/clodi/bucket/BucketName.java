package com.clodi.bucket;

public enum BucketName {

    PRODUCT_IMAGES("clodi-product-images");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

}