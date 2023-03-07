package com.giao.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){

        try {
            //把value的参数传到bean对象里面
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
    /**
     *将字符串转换成int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
