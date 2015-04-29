package ms.commons.jar;


import java.net.URL;

/**
 * Created by davor on 23/04/15.
 */
public class JarDetail {

    public static final String JAR_DELIMITER = "-";

    private String path;

    private String fileName;

    private String name;

    private String version;

    public JarDetail() {
    }

    public JarDetail(Class<?> clazz) {
        load(clazz);
    }

    private void load(Class<?> clazz) {
        URL location = JarUtils.getClassLocation(clazz);
        if (location != null) {
            String jarName = JarUtils.getJarName(location);
            if (jarName != null) {
                setFileName(jarName);
                String nameVersion[] = jarName.split(JAR_DELIMITER);
                setName(nameVersion[0]);
                setVersion(nameVersion[1]);

                String path = JarUtils.getJarPath(location);
                setPath(path);
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", version:" + getVersion();
    }
}
