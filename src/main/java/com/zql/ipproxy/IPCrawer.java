package com.zql.ipproxy;

import com.zql.entity.IPEntity;
import com.zql.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据url和page获取指定页面的ip列表,每页100个ip
 * Created by  on 2019-3-5.
 */
@Component
public class IPCrawer {
    private static String HTTP_URL = "https://www.xicidaili.com/wt/";
    private static String HTTPS_URL = "https://www.xicidaili.com/wn/";

    /**
     * 获取HTTPS类型的ip
     * @return
     */
    public List<IPEntity> getHTTPS(int page){
          return  crawl(HTTPS_URL, page);
    }

    /**
     * 获取http类型的ip
     * @param page
     * @return
     */
    public List<IPEntity> getHTTP(int page){
        return  crawl(HTTP_URL, page);
    }
    //获取ip列表
    public List<IPEntity> crawl(String url, Integer page) {
        String responseContent = HttpUtils.getResponseContent(url);
        Document doc = Jsoup.parse(responseContent);
        //获取本页的ip数据
        Elements elements = doc.getElementById("ip_list").select("tr");
        //取出数据存入list
        List<IPEntity> ips = new ArrayList<>();
        Integer i = 0 ;
        for (Element element : elements) {
            if(i==0){
                i++;
                continue;
            }
            String ip = element.children().get(1).text();
            Integer port = Integer.parseInt(element.children().get(2).text().trim());
            String typeStr = element.children().get(5).text().trim();
            int type;
            if (typeStr.equals("HTTP"))
                type = IPEntity.TYPE_HTTP;
            else
                type = IPEntity.TYPE_HTTPS;
            IPEntity ipEntity = new IPEntity();
            ipEntity.setIp(ip);
            ipEntity.setPort(port);
            ipEntity.setType(type);
            ips.add(ipEntity);
        }
        return ips;
    }
}
