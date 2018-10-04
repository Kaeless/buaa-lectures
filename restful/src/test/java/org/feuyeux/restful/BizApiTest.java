package org.feuyeux.restful;

import lombok.extern.slf4j.Slf4j;
import org.feuyeux.restful.BizApi;
import org.feuyeux.restful.BizApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BizApplication.class)
public class BizApiTest {
    @Autowired
    private BizApi udsOpsApi;

    @Test
    public void test() {
        String msg = udsOpsApi.hello("BUAA");
        Assert.assertEquals("hello BUAA",msg);
        log.info(msg);
    }
}
