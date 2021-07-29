package com.example.segsw;

import com.example.segsw.controller.HelloController;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SegswApplicationTests {

    @Autowired
    private HelloController helloController;

    @Test
    public void contextLoads() throws Exception {
        MatcherAssert.assertThat(helloController, CoreMatchers.notNullValue());
    }

}
