package com.fanglin;

import org.apache.logging.log4j.Logger;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.help.ESPluginLoggerFactory;

/**
 * @author 彭方林
 * @version 1.0
 * @date 2019/6/17 13:07
 **/
public class RedisDictReloadThread implements Runnable  {

    private static final Logger logger = ESPluginLoggerFactory.getLogger(RedisDictReloadThread.class.getName());

    @Override
    public void run() {
        logger.info("开始重载词典");
        Dictionary.getSingleton().reLoadRedisDict();
        logger.info("结束重载词典");
    }
}
