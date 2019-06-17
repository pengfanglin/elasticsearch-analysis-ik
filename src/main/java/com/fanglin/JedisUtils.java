package com.fanglin;

import org.apache.logging.log4j.Logger;
import org.wltea.analyzer.help.ESPluginLoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * @author 彭方林
 * @version 1.0
 * @date 2019/6/17 16:21
 **/
public class JedisUtils {
    private static final Logger logger = ESPluginLoggerFactory.getLogger(JedisUtils.class.getName());

    public static JedisPoolConfig jedisPoolConfig;
    public static JedisPool jedisPool = null;
    public static boolean isInit = false;

    static {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(2);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setJmxEnabled(false);
    }

    public static void init(Properties prop) {
        try {
            String host = prop.getProperty("host");
            int port = Integer.valueOf(prop.getProperty("port"));
            String password = prop.getProperty("password");
            int database = Integer.valueOf(prop.getProperty("database"));
            int timeout = Integer.valueOf(prop.getProperty("timeout"));
            logger.info("redis信息:{},{},{},{},{}",host,port,password,database,timeout);
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
            isInit=true;
            logger.warn("jedis初始化成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("jedis初始化失败:{}", e.getMessage());
        }
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
