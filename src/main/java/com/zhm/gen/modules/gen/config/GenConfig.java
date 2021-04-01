package com.zhm.gen.modules.gen.config;

import org.apache.logging.log4j.util.PropertiesUtil;

/**
 * Describe: 读取代码相关配置
 * Author: zhm
 * CreateTime: 2019/10/23
 */
public class GenConfig {
    private static PropertiesUtil properties = new PropertiesUtil("application.properties");
    /**
     * 作者
     **/
    public static String author = properties.getStringProperty("gen.author");

    /**
     * 生成包路径
     **/
    public static String packageName = properties.getStringProperty("gen.packageName");

    /**
     * 自动去除表前缀，默认是false
     **/
    public static boolean autoRemovePre = properties.getBooleanProperty("gen.autoRemovePre");

    /**
     * 表前缀(类名不会包含表前缀)
     **/
    public static String tablePrefix = properties.getStringProperty("gen.tablePrefix");
}
