package com.example.demo.testcase;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @Auther: song.huai
 * @Date: 2021/1/20 23:54
 * @Description:
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        String url = "http://localhost:8801";
        System.out.println(getRequest(url));
    }

    private static String getRequest(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
