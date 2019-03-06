package com.zql.utils;

import com.zql.entity.IPEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Asche
 * @github: https://github.com/asche910
 * @date 2019年1月19日
 */
public class HttpUtils {

    public static  String getResponseContent(String url, IPEntity ipEntity) {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet(url);
        //设置代理
        HttpHost proxy;
        RequestConfig requestConfig;
        if (null != ipEntity) {
            proxy = new HttpHost(ipEntity.getIp(), ipEntity.getPort());
            //设置请求超时和读取超时时间，如果返回200则视为有效ip
            requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(5000)//设置连接超时时间
                    .setConnectionRequestTimeout(5000) // 设置请求超时时间
                    .setSocketTimeout(5000)
                    .build();
        }
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
//                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (!Optional.ofNullable(httpResponse).isPresent()) {
                if (200 == httpResponse.getStatusLine().getStatusCode()) {
                    String htmls = EntityUtils.toString(httpResponse.getEntity());
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
//
//    /**
//     * @param url
//     * @param headerMap 请求头部
//     * @param ipEntity
//     * @return
//     * @throws Exception
//     */
//    public static String getResponseContent(String url, Map<String, List<String>> headerMap, IPEntity ipEntity) throws Exception {
//        HttpsURLConnection connection = null;
//
//        // 设置代理
//        if (ipEntity != null) {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipEntity.getIp(), ipEntity.getPort()));
//
//            connection = (HttpsURLConnection) new URL(url).openConnection(proxy);
//
//            if (ipEntity.getType() == IPEntity.TYPE_HTTPS) {
//                SSLContext sslContext = SSLContext.getInstance("SSL");
//                sslContext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
//                connection.setSSLSocketFactory(sslContext.getSocketFactory());
//                connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
//            }
//        }
//
//        if (connection == null)
//            connection = (HttpsURLConnection) new URL(url).openConnection();
//
//        // 添加请求头部
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
//        if (headerMap != null) {
//            Iterator<Map.Entry<String, List<String>>> iterator = headerMap.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, List<String>> entry = iterator.next();
//                List<String> values = entry.getValue();
//                for (String value : values)
//                    connection.setRequestProperty(entry.getKey(), value);
//            }
//        }
//
//        InputStream inputStream = connection.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        reader.close();
//        inputStream.close();
//        return stringBuilder.toString();
//    }
//
//
//    private static class TrustAnyTrustManager implements X509TrustManager {
//
//        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[]{};
//        }
//    }
//
//    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
//        public boolean verify(String hostname, SSLSession session) {
//            return true;
//        }
//    }