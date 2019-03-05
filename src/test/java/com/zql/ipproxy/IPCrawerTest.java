package com.zql.ipproxy;

import com.zql.entity.IPEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 张启磊 on 2019-3-5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPCrawerTest {
    @Autowired
    private IPCrawer crawer;
    @Test
    public void getHTTPS() throws Exception {
    }

    @Test
    public void getHTTP() throws Exception {

    }

    @Test
    public void crawl() throws Exception {
        List<IPEntity> ips = crawer.crawl("https://www.xicidaili.com/wt/", 1);
        System.out.println(ips);
    }

}