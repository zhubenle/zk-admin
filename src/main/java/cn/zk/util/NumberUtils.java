package cn.zk.util;

import java.util.Objects;

/**
 * <br/>
 * Created on 2018/4/21 13:12.
 *
 * @author zhubenle
 */
public class NumberUtils {

    public static Integer toInteger(Number number) {
        return toInteger(number, null);
    }

    public static Integer toInteger(Number number, Integer defaultValue) {
        if (Objects.isNull(number)) {
            return defaultValue;
        }
        return number.intValue();
    }

    public static Long toLong(Number number) {
        return toLong(number, null);
    }

    public static Long toLong(Number number, Long defaultValue) {
        if (Objects.isNull(number)) {
            return defaultValue;
        }
        return number.longValue();
    }

    public static Float toFloat(Number number) {
        return toFloat(number, null);
    }

    public static Float toFloat(Number number, Float defaultValue) {
        if (Objects.isNull(number)) {
            return defaultValue;
        }
        return number.floatValue();
    }
}
