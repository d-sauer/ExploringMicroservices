package ms.commons.logging;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public interface Logger extends org.slf4j.Logger {

    default public org.slf4j.Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }
    
    /**
     * {@inheritDoc}
     */
    default public String getName() {
        return logger().getName();
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isTraceEnabled() {
        return logger().isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(String msg) {
        logger().trace(msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(String format, Object arg) {
        logger().trace(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(String format, Object arg1, Object arg2) {
        logger().trace(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(String format, Object... arguments) {
        logger().trace(format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(String msg, Throwable t) {
        logger().trace(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isTraceEnabled(Marker marker) {
        return logger().isTraceEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(Marker marker, String msg) {
        logger().trace(marker, msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(Marker marker, String format, Object arg) {
        logger().trace(marker, format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger().trace(marker, format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(Marker marker, String format, Object... argArray) {
        logger().trace(marker, format, argArray);
    }

    /**
     * {@inheritDoc}
     */
    default public void trace(Marker marker, String msg, Throwable t) {
        logger().trace(marker, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isDebugEnabled() {
        return logger().isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(String msg) {
        logger().debug(msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(String format, Object arg) {
        logger().debug(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(String format, Object arg1, Object arg2) {
        logger().debug(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(String format, Object... arguments) {
        logger().debug(format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(String msg, Throwable t) {
        logger().debug(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isDebugEnabled(Marker marker) {
        return logger().isDebugEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(Marker marker, String msg) {
        logger().debug(marker, msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(Marker marker, String format, Object arg) {
        logger().debug(marker, format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger().debug(marker, format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(Marker marker, String format, Object... arguments) {
        logger().debug(marker, format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void debug(Marker marker, String msg, Throwable t) {
        logger().debug(marker, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isInfoEnabled() {
        return logger().isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void info(String msg) {
        logger().info(msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(String format, Object arg) {
        logger().info(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(String format, Object arg1, Object arg2) {
        logger().info(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(String format, Object... arguments) {
        logger().info(format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(String msg, Throwable t) {
        logger().info(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isInfoEnabled(Marker marker) {
        return logger().isInfoEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(Marker marker, String msg) {
        logger().info(marker, msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(Marker marker, String format, Object arg) {
        logger().info(marker, format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger().info(marker, format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(Marker marker, String format, Object... arguments) {
        logger().info(marker, format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void info(Marker marker, String msg, Throwable t) {
        logger().info(marker, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isWarnEnabled() {
        return logger().isWarnEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(String msg) {
        logger().warn(msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(String format, Object arg) {
        logger().warn(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(String format, Object... arguments) {
        logger().warn(format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(String format, Object arg1, Object arg2) {
        logger().warn(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(String msg, Throwable t) {
        logger().warn(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isWarnEnabled(Marker marker) {
        return logger().isWarnEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(Marker marker, String msg) {
        logger().warn(marker, msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(Marker marker, String format, Object arg) {
        logger().warn(marker, format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger().warn(marker, format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(Marker marker, String format, Object... arguments) {
        logger().warn(marker, format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void warn(Marker marker, String msg, Throwable t) {
        logger().warn(marker, msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isErrorEnabled() {
        return logger().isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void error(String msg) {
        logger().error(msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(String format, Object arg) {
        logger().error(format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(String format, Object arg1, Object arg2) {
        logger().error(format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(String format, Object... arguments) {
        logger().error(format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(String msg, Throwable t) {
        logger().error(msg, t);
    }

    /**
     * {@inheritDoc}
     */
    default public boolean isErrorEnabled(Marker marker) {
        return logger().isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    default public void error(Marker marker, String msg) {
        logger().error(marker, msg);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(Marker marker, String format, Object arg) {
        logger().error(marker, format, arg);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger().error(marker, format, arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(Marker marker, String format, Object... arguments) {
        logger().error(marker, format, arguments);
    }

    /**
     * {@inheritDoc}
     */
    default public void error(Marker marker, String msg, Throwable t) {
        logger().error(marker, msg, t);
    }

}
