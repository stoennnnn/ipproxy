package com.zql.api;

import com.zql.entity.IPEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程校验ip可用性获得可用ip集合
 * Created by 张启磊 on 2019-3-5.
 */
public class GetIPApi {
    private  int count=0;
    List<IPEntity> ips = new ArrayList();
    /**
     * 添加有效ip，支持并发
     * @param ipEntity
     */
    public synchronized  void add(IPEntity ipEntity){
        ips.add(ipEntity);
    }

    /**
     * 每个线程校验ip完成后计数
     */
    public void increase(){
        count++;
    }



}
