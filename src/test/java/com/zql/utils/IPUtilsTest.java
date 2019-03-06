package com.zql.utils;

import com.zql.entity.IPEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 张启磊 on 2019-3-6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPUtilsTest {
    @Test
    public void isValid() throws Exception {
        IPEntity ipEntity = new IPEntity("125.123.143.13", 9999, 1);
        String valid = IPUtils.isValid(ipEntity);
    }

}