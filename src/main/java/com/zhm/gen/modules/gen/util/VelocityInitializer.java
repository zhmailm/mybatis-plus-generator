package com.zhm.gen.modules.gen.util;

import com.zhm.gen.common.config.constant.SystemConstant;
import com.zhm.gen.modules.gen.config.GenConfig;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * Describe: 模板引擎工厂
 * Author: zhm
 * CreateTime: 2019/10/23
 * */
public class VelocityInitializer {
    private static PropertiesUtil properties= new PropertiesUtil("application.properties");
    /**
     * 初 始 化 模 板 引 擎
     * */
    public static void initVelocity() {
        Properties p = new Properties();
        System.out.println( GenConfig.packageName);
        try
        {
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("runtime.log", properties.getStringProperty("log.path")+"/velocity.log");
            p.setProperty(Velocity.ENCODING_DEFAULT, SystemConstant.UTF8);
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
