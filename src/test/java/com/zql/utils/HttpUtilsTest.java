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
        IPEntity ipEntity = new IPEntity("78.28.114.8", 53901, 1);
        String url = "https://www.baidu.com/";
        String url2 = "https://weixin.sogou.com/weixin?type=1&s_from=input&query=javastack&ie=utf8&_sug_=n&_sug_type_=";
        String url3 = "https://mp.weixin.qq.com/profile?src=3&timestamp=1551776786&ver=1&signature=EsElXhmCBhsgzM5ityjUjqakfLJLpIjmDiywLjZag4mLcP-gTBtujaicZCyg27BKH2r-SE3T5L6XJLaY0lxunw==";
        String sb = HttpUtils.getResponseContent(url3, null, ipEntity);
        System.out.println(sb);
    }

}