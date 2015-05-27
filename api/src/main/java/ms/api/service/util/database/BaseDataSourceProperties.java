package ms.api.service.util.database;

import java.util.StringJoiner;

/**
 * Created by davor on 27/05/15.
 */
public class BaseDataSourceProperties {

    private String username;

    private String password;

    private String driverClassName;

    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add("url='" + (url != null ? url : "") + "'");
        joiner.add("driverClassName='" + (driverClassName != null ? driverClassName : "") + "'");
        joiner.add("username='" + (username != null ? username : "") + "'");
        joiner.add("password='" + (password != null ? "***" : "") + "'");

        return joiner.toString();
    }

}
