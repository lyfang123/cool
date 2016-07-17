package com.uwaytech.cool.common.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by moyi on 2016-04-29.
 */

@Component
public class RedisHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ValueOperations<String, String> valueOps;
    public SetOperations<String, String> setOps;
    public ZSetOperations<String, String> zSetOps;
    public ListOperations<String, String> listOps;
    public HashOperations<String,Object,Object> hashOPs;

    private  StringRedisTemplate template;

    @PostConstruct
    private void afterInit() {
        this.valueOps = this.template.opsForValue();
        this.setOps   = this.template.opsForSet();
        this.zSetOps  = this.template.opsForZSet();
        this.listOps  = this.template.opsForList();
        this.hashOPs = this.template.opsForHash();
    }

    public void saveObjectByValueOps(String key,Object obj) {
        String value = JSON.toJSONString(obj);
        this.valueOps.set(key,value);
    }

    public void saveObjectByValueOps(String key,Object obj,long timeOut,TimeUnit unit) {
        String value = JSON.toJSONString(obj);
        //默认使用分钟单位
        this.valueOps.set(key,value,timeOut, unit);
    }

    public <T>T getObjectByValueOps(String key,Class<T> clz){
        String value = this.valueOps.get(key);
        return JSON.parseObject(value,clz);
    }

    public void saveStringByValueOps(String key,String value){
        this.valueOps.set(key,value);
    }

    public void saveStringByValueOps(String key,String value,long timeOut,TimeUnit unit){
        this.valueOps.set(key,value,timeOut,unit);
    }

    public String getStringByValueOps(String key){
        return this.valueOps.get(key);
    }

    public  StringRedisTemplate getTemplate() {
        return template;
    }

    //获得锁
    public boolean acquireLock(){

        return false;
    }


    @Autowired(required = false)
    @Qualifier("redisTemplate")
    public void setTemplate(StringRedisTemplate template) {
        this.template = template;
    }

}
