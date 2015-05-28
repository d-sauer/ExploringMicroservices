package ms.commons.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by davor on 27/05/15.
 */
public class PackageUtils {

    public static String[] getPackageNames(Class<?>... clazz) {
        Set<String> packages = new HashSet<String>();
        for (Class<?> type : clazz) {
            packages.add(getPackageName(type));
        }
        return packages.toArray(new String[0]);
    }

    public static String getPackageName(Class<?> clazz) {
        final String clazzName = clazz.getName();
        int lastDotIndex = clazzName.lastIndexOf(".");
        return (lastDotIndex != -1 ? clazzName.substring(0, lastDotIndex) : "");
    }
}
