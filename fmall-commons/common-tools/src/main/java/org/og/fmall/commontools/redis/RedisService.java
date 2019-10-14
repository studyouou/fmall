package org.og.fmall.commontools.redis;

import lombok.extern.slf4j.Slf4j;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ougen
 * @date:2019/8/2817:47
 */
@Service
@Slf4j
@ConditionalOnClass(JedisPool.class)
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    public <T> boolean set(String key,T value){
        return set(key,value,0);
    }

    public <T> boolean set(String key,T value,int ex){
        Jedis jedis = null;
        String result = null;

        try {
            jedis = jedisPool.getResource();
            String str = JSONUtil.beanToString(value);
            if (str==null||str.length()<=0){
                return false;
            }
            if (ex==0){
                jedis.set(key,str);
            }else {
                jedis.setex(key,ex,str);
            }
        }catch (Exception e){
            log.error("expire key:{} error",key,e);
            return  false;
        }finally {
            returnToPool(jedis);
        }
        return true;
    }

    /**
     * 设置失效时间
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key ,String value){
        Jedis jedis =null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setnx(key,value);
        }catch (Exception e){
            log.error("expire key:{} error",key,e);
            return  result;
        }finally {
            returnToPool(jedis);
        }

        return  result;

    }
    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public Long expire(String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }


    public  String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }


    public  String getset(String key,String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.getSet(key,value);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }

    public  Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error",key,e);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }

    public Long incr(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incr(key);
        }catch (Exception e){
            log.error("incr key:{} error",key);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }
    public Long decr(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.decr(key);
        }catch (Exception e){
            log.error("decr key:{} error",key);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }
    public Long decrBy(String key,int num){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.decrBy(key,num);
        }catch (Exception e){
            log.error("decr key:{} error",key);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }
    public List<String> scanKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<String>();
            String cursor = "0";
            ScanParams sp = new ScanParams();
            sp.match("*"+key+"*");
            sp.count(100);
            do{
                ScanResult<String> ret = jedis.scan(cursor, sp);
                List<String> result = ret.getResult();
                if(result!=null && result.size() > 0){
                    keys.addAll(result);
                }
                //再处理cursor
                cursor = ret.getStringCursor();
            }while(!cursor.equals("0"));
            return keys;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean exist(String key){
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = jedis.exists(key);
        }catch (Exception e){
            log.error("exiest key:{} error",key);
            return result;
        }finally {
            returnToPool(jedis);
        }
        return result;
    }
    public Long getExpireTime(String key){
        Jedis jedis = null;
        long expireTime;
        try {
            jedis = jedisPool.getResource();
            expireTime = jedis.ttl(key);
        }catch (Exception e){
            log.error("exiest key:{} error",key);
            return null;
        }finally {
            returnToPool(jedis);
        }
        return expireTime;
    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}
