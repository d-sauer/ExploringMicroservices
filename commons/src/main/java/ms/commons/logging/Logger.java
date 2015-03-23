package ms.commons.logging;

//import java.util.WeakHashMap;
//
//import org.slf4j.LoggerFactory;
//import org.slf4j.Marker;

//public interface Logger extends org.slf4j.Logger {
public interface Logger  {

//    public static final WeakHashMap<Class<?>, org.slf4j.Logger> loggers = new WeakHashMap<>();
//    
//    default public org.slf4j.Logger log() {
//        Class<?> clazz = getClass();
//        org.slf4j.Logger log = loggers.get(clazz);
//        if (log == null) {
//            log = log(clazz);
//            loggers.put(clazz, log);
//        }
//
//        return log;
//    }
//
//    public static org.slf4j.Logger log(Class <?> clazz) {
//        return LoggerFactory.getLogger(clazz);
//    }
//    
//    /**
//     * {@inheritDoc}
//     */
//    default public String getName() {
//        return log().getName();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isTraceEnabled() {
//        return log().isTraceEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(String msg) {
//        log().trace(msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(String format, Object arg) {
//        log().trace(format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(String format, Object arg1, Object arg2) {
//        log().trace(format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(String format, Object... arguments) {
//        log().trace(format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(String msg, Throwable t) {
//        log().trace(msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isTraceEnabled(Marker marker) {
//        return log().isTraceEnabled(marker);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(Marker marker, String msg) {
//        log().trace(marker, msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(Marker marker, String format, Object arg) {
//        log().trace(marker, format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(Marker marker, String format, Object arg1, Object arg2) {
//        log().trace(marker, format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(Marker marker, String format, Object... argArray) {
//        log().trace(marker, format, argArray);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void trace(Marker marker, String msg, Throwable t) {
//        log().trace(marker, msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isDebugEnabled() {
//        return log().isDebugEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(String msg) {
//        log().debug(msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(String format, Object arg) {
//        log().debug(format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(String format, Object arg1, Object arg2) {
//        log().debug(format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(String format, Object... arguments) {
//        log().debug(format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(String msg, Throwable t) {
//        log().debug(msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isDebugEnabled(Marker marker) {
//        return log().isDebugEnabled(marker);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(Marker marker, String msg) {
//        log().debug(marker, msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(Marker marker, String format, Object arg) {
//        log().debug(marker, format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(Marker marker, String format, Object arg1, Object arg2) {
//        log().debug(marker, format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(Marker marker, String format, Object... arguments) {
//        log().debug(marker, format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void debug(Marker marker, String msg, Throwable t) {
//        log().debug(marker, msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isInfoEnabled() {
//        return log().isInfoEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(String msg) {
//        log().info(msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(String format, Object arg) {
//        log().info(format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(String format, Object arg1, Object arg2) {
//        log().info(format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(String format, Object... arguments) {
//        log().info(format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(String msg, Throwable t) {
//        log().info(msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isInfoEnabled(Marker marker) {
//        return log().isInfoEnabled(marker);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(Marker marker, String msg) {
//        log().info(marker, msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(Marker marker, String format, Object arg) {
//        log().info(marker, format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(Marker marker, String format, Object arg1, Object arg2) {
//        log().info(marker, format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(Marker marker, String format, Object... arguments) {
//        log().info(marker, format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void info(Marker marker, String msg, Throwable t) {
//        log().info(marker, msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isWarnEnabled() {
//        return log().isWarnEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(String msg) {
//        log().warn(msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(String format, Object arg) {
//        log().warn(format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(String format, Object... arguments) {
//        log().warn(format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(String format, Object arg1, Object arg2) {
//        log().warn(format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(String msg, Throwable t) {
//        log().warn(msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isWarnEnabled(Marker marker) {
//        return log().isWarnEnabled(marker);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(Marker marker, String msg) {
//        log().warn(marker, msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(Marker marker, String format, Object arg) {
//        log().warn(marker, format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(Marker marker, String format, Object arg1, Object arg2) {
//        log().warn(marker, format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(Marker marker, String format, Object... arguments) {
//        log().warn(marker, format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void warn(Marker marker, String msg, Throwable t) {
//        log().warn(marker, msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isErrorEnabled() {
//        return log().isErrorEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(String msg) {
//        log().error(msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(String format, Object arg) {
//        log().error(format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(String format, Object arg1, Object arg2) {
//        log().error(format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(String format, Object... arguments) {
//        log().error(format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(String msg, Throwable t) {
//        log().error(msg, t);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public boolean isErrorEnabled(Marker marker) {
//        return log().isErrorEnabled();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(Marker marker, String msg) {
//        log().error(marker, msg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(Marker marker, String format, Object arg) {
//        log().error(marker, format, arg);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(Marker marker, String format, Object arg1, Object arg2) {
//        log().error(marker, format, arg1, arg2);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(Marker marker, String format, Object... arguments) {
//        log().error(marker, format, arguments);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    default public void error(Marker marker, String msg, Throwable t) {
//        log().error(marker, msg, t);
//    }

}
