package pet.jwt;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisUtil {
    INSTANCE;

    private final JedisPool pool;

    RedisUtil() {
        pool = new JedisPool("localhost:", 6379);
    }

    public void sadd(String key, String value) {
        Jedis jedis = null;
        try{
            jedis =  new Jedis("localhost", 6379, 500);// pool.getResource();
            jedis.sadd(key, value);
            Set<String> a=jedis.smembers("a");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void srem(String key, String value) {
        Jedis jedis = null;
        try{
            jedis =new Jedis("localhost", 6379, 500);// pool.getResource();
            jedis.srem(key, value);
           
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = new Jedis("localhost", 6379, 500);//pool.getResource();
          
            return jedis.sismember(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}