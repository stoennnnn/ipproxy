package com.zql.utils;

import com.zql.entity.IPEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 张启磊 on 2019-3-5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilsTest {
//    @Autowired
//    private HttpUtils httpUtilsl;
    @Test
    public void getResponseContent() throws Exception {
        IPEntity ipEntity = new IPEntity("122.136.212.132", 53281, 1);
        String utl4="http://31f.cn/https-proxy/";
        String url3 = "http://weixin.sogou.com/weixin?type=1&ie=utf8&query=javatuanzhang";
        String url5="http://mp.weixin.qq.com/profile?src=3&timestamp=1552144040&ver=1&signature=Jh-ByW8AUU9Sqnp1-zzKRJ1dzZXoQTK4DpJt*GUAdbfRyRTzXNizMhY05Uapvew-B9fp8zDLjvvHwuO4XkQMqw==";
        String sb = HttpUtils.getResponseContent(url5, ipEntity);
        System.out.println(sb);
    }

}