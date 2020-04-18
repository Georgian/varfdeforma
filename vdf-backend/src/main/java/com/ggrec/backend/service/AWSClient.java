package com.ggrec.backend.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;

@Service
public class AWSClient {

    private AmazonS3 s3client;

    @Value("${s3.region}")
    private String region;
    @Value("${s3.bucketName}")
    private String bucketName;
    @Value("${s3.accessKey}")
    private String accessKey;
    @Value("${s3.secretKey}")
    private String secretKey;

    private String getDOServiceEndpoint() {
        return MessageFormat.format("https://vdf.{0}.digitaloceanspaces.com", region);
    }

    @PostConstruct
    private void initializeAmazon() {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                getDOServiceEndpoint(),
                region
        ));

        if (!accessKey.isEmpty() && !secretKey.isEmpty())
            clientBuilder.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)));
        else
            throw new IllegalArgumentException("No AWS S3 credentials provided in YML file");

        this.s3client = clientBuilder.build();
    }

    public void uploadFileFromURL(URL url, String fileName, String contentType) throws IOException {
        try ( InputStream fileStream = url.openStream();) {
            byte[] bytes = IOUtils.toByteArray(fileStream);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(bytes.length);

            s3client.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(bytes), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
    }

//    private String buildDOAuthorizationHeader() {
//        return MessageFormat.format("AWS4-HMAC-SHA256 " +
//                "Credential={0}/{1}/{2}/s3/aws4_request," +
//                "SignedHeaders=host;x-amz-acl;x-amz-content-sha256;x-amz-date," +
//                "", "");
//
//
//        // return "https://vdf.fra1.digitaloceanspaces.com";
//    }

}
