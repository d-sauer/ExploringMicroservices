package ms.commons.util;

import java.lang.reflect.Method;

/**
 * Created by davor on 28/05/15.
 */
public class ObjectUtils {

    /**
     * Coppy values from one object to another mapping setters of destionation object with getters of source object
     * @param source
     * @param destination
     */
    public static void mapValues(Object source, Object destination) throws Exception {
        Method [] sourceMethods = source.getClass().getMethods();

        for(Method sourceMethod : sourceMethods) {
            Class<?> sourceReturnType = sourceMethod.getReturnType();
            final String sourceMethodName = sourceMethod.getName();

            boolean smnStartWithGet = sourceMethodName.startsWith("get");
            boolean smnStartWithIs = sourceMethodName.startsWith("is");
            if ((smnStartWithGet || smnStartWithIs)
                    && !sourceReturnType.equals(void.class))
            {
                final String destMethodName = smnStartWithGet ? sourceMethodName.replaceFirst("get", "set") : sourceMethodName.replaceFirst("is", "set");
                try {
                    Method destMethod = destination.getClass().getMethod(destMethodName, sourceReturnType);

                    Object sourceValue = sourceMethod.invoke(source, null);

                    destMethod.invoke(destination, sourceValue);
                } catch (NoSuchMethodException e) {
                    // Destionation class doesn't have SETTER method
                } catch (Exception e) {
                    throw new Exception("Can't map values from:" + source.getClass() + "." + sourceMethodName +", to:" + destination.getClass() + "." + destMethodName, e);
                }
            }
        }
    }


}
