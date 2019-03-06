package com.zql.utils;

import com.zql.entity.IPEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by 26725 on 2019/3/6.
 */
public class IPUtils {
    /**
     * 筛选能用的ip：能够请求搜狗微信平台的ip
     */
    public static String isValid(IPEntity ipEntity) {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet("https://weixin.sogou.com/");
        //设置代理
        HttpHost proxy = new HttpHost(ipEntity.getIp(), ipEntity.getPort());
        //设置请求超时和读取超时时间，如果返回200则视为有效ip
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到返回码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (200 == statusCode) {
            return "true";
        } else
            return "false";
    }

    /**
     *  把ip存放到队列
     *  需要支持多线程并发操作
     */
    public static void saveIP(){

    }
}
