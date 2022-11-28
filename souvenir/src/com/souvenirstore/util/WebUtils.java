package com.souvenirstore.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    /**
     * transfer the data in the Map to the corresponding JavaBean
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            // System.out.println("before: " + bean);
            BeanUtils.populate(bean, value);
            // System.out.println("after: " + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * convert the string type into Integer
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return defaultValue;
    }
}
