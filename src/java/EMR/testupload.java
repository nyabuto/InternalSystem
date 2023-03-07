/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class testupload {
//https://goldstarkenya.sharepoint.com/:f:/s/USAIDTujengeJamii/ElxgKSohtwNHqj_M6n1jqcsBNYa2kduMsMNtm-HlC71oJg?e=m3He2f
    private static final String UPLOAD_FILE_URL = "https://goldstarkenya.sharepoint.com/_api/web/lists/getbytitle('<ListName>')/rootfolder/files/add(url='<FileName>.<FileExtension>',overwrite=true)";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(UPLOAD_FILE_URL);
        postRequest.setHeader("Accept", "application/json;odata=verbose");
        postRequest.setHeader("Authorization", "Bearer <AccessToken>");
        postRequest.setHeader("Content-Type", "application/json;odata=verbose");

        byte[] fileContent = Files.readAllBytes(Paths.get("<FilePath>"));
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody("file", fileContent, ContentType.APPLICATION_OCTET_STREAM, "<FileName>.<FileExtension>")
                .build();

        postRequest.setEntity(entity);
        HttpResponse response = httpClient.execute(postRequest);
        HttpEntity responseEntity = response.getEntity();

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        System.out.println("Response Body : " + EntityUtils.toString(responseEntity));

        httpClient.close();
    }
}
