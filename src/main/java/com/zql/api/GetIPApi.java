package com.zql.api;

import com.zql.entity.IPEntity;
import com.zql.ipproxy.IPCrawler;
import com.zql.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程校验ip可用性获得可用ip集合
 * Created by 张启磊 on 2019-3-5.
 */
@Component
public class GetIPApi {
    @Autowired
    private IPCrawler crawler;
    private  int count=0;
    List<IPEntity> ipList = new ArrayList();
    /**
     * 添加有效ip，支持并发
     * @param ipEntity
     */
    public synchronized  void add(IPEntity ipEntity){
        ipList.add(ipEntity);
    }
    /**
     * 每个线程校验ip完成后计数
     */
    public void increase(){
        count++;
    }


    /**
     * 多线程验证ip
     */
    public void  multExecute(){
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        List<IPEntity> ips = crawler.getHTTPS(1);
        for (IPEntity ipEntity : ips) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String result = IPUtils.isValid(ipEntity);
                    if ("true".equals(result)){
                        add(ipEntity);
                    }
                    increase();
                }
            });
        }
        //当count和集合一样长代表执行结束
        while (true){
            if (count==ipList.size()){
                System.out.println("有效ip数为"+count+"ip集合为"+ipList.toString());
                break;
            }
        }

    }




}
