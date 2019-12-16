package org.og.fmall.fmallconsle.controller;

import org.og.fmall.commontools.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lyp
 * @description:
 * @date: 2019/11/1
 */
@RestController
public class CountController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/getCount")
    public String getCount(){
        String count = redisService.get("count:html");
        return count;
    }

}
