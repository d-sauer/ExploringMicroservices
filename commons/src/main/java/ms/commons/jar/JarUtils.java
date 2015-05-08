package ms.commons.jar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by davor on 23/04/15.
 */
public class JarUtils {

    public static final char SLASH = '/';

    /**
     * Get location of loaded class file;
     * <br/>
     * e.g.: jar:file:/Users/davor/development/my_repos/ExploringMicroservices/api/build/libs/api-0.0.1.jar!/ms/api/service/Microservice.class
     *
     * @param clazz
     * @return
     */
    public static URL getClassLocation(Class<?> clazz) {
        String className = clazz.getName();

        URL location = clazz.getResource(SLASH + className.replace('.', '/') + ".class");
        return location;
    }


    public static String getJarPath(URL classLocation) {
        if (classLocation == null) {
            return null;
        }

        // detect JAR file name
        String url = classLocation.toString();
        int end = url.indexOf(".jar!");
        if (end != -1) {
            return url.substring("jar:file:".length(), end + ".jar".length());
        }

        return null;
    }

    /**
     * Detect in which JAR file name is loaded class located.
     * By detecting if classLocation contains '.jar!' substring.
     *
     * @return
     */
    public static String getJarName(URL classLocation) {
        if (classLocation == null) {
            return null;
        }

        // detect JAR file name
        String url = classLocation.toString();
        int end = url.indexOf(".jar!");
        int start = -1;
        if (end != -1) {
            for (int n = end; n >= 0; n--) {
                if (url.charAt(n) == SLASH) {
                    start = n + 1;
                    break;
                }
            }

            if (start != -1) {
                return url.substring(start, end);
            }
        }

        return null;
    }

    public static Properties getPropertyFile(JarDetail detail, String fileName) throws IOException {
        JarFile jar = new JarFile(detail.getPath());
        JarEntry file = jar.getJarEntry(fileName);

        Properties prop = new Properties();
        prop.load(jar.getInputStream(file));

        return prop;
    }

    public static InputStream getInputStream(JarDetail detail, String fileName) throws IOException {
        JarFile jar = new JarFile(detail.getPath());
        JarEntry file = jar.getJarEntry(fileName);
        return jar.getInputStream(file);
    }

    public static boolean exists(JarDetail detail, String fileName) {
        JarFile jar = null;
        try {
            jar = new JarFile(detail.getPath());
            JarEntry file = jar.getJarEntry(fileName);
            if (file != null)
                return true;
        } catch (IOException e) {
            return false;
        }

        return false;
    }

}
