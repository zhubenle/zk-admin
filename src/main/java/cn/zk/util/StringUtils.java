package cn.zk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <br/>
 * Created on 2018/4/21 12:39.
 *
 * @author zhubenle
 */
public class StringUtils {

    public static boolean isTrimEmpty(String value) {
        return Objects.isNull(value) || "".equals(value.trim());
    }

    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || "".equals(value);
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static String toString(Object o) {
        return toString(o, null);
    }

    public static String toString(Object o, String defaultValue) {
        return Objects.isNull(o) ? defaultValue : o.toString();
    }

    public static List<String> splitToList(String value, String split) {
        List<String> list = new ArrayList<>();
        if (isEmpty(value) || isEmpty(split)) {
            return list;
        }
        String[] array =value.split(split);
        for (String item : array) {
            if (isNotEmpty(item)) {
                list.add(item);
            }
        }
        return list;
    }

    public static <T> String join(List<T> list, String join) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        return list.stream().map(Object::toString).reduce((s, s2) -> s + join + s2).get();
    }
}
