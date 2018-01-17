package com.polo.common;

import com.intellij.util.ReflectionUtil;

import java.net.URL;

/**
 * @author bqy
 * @create 2018-01-17 13:05
 **/
public class FileFileMapper {

    public static final String MYBATIS = "/images/mybatis.png";
    public static final String MYBATIS_MAPPER = "/images/mybatis_mapper.png";

    public static URL pathUrl(String path) {
        return ReflectionUtil.getGrandCallerClass().getResource(path);
    }
}
