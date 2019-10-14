package org.og.fmall.fmallstock;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:ougen
 * @date:2019/10/221:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FmallStocksTest {
    @Before
    public void init(){
        System.out.println("开始测试");
    }

    @After
    public void after(){
        System.out.println("测试结束");
    }
}
