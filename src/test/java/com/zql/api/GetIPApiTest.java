package com.zql.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 张启磊 on 2019-3-6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetIPApiTest {
    @Autowired
    private  GetIPApi getIPApi;
    @Test
    public void multExecute() throws Exception {
        getIPApi.multExecute();
    }

}