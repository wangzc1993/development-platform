package org.wangzc.development.platform.util;

public class ExceptionUtils {

    public static String toString(Throwable t) {
        StringBuilder sb = new StringBuilder();
        Throwable c = t;
        do {
            if (c != t) {
                sb.append("Cause by: ");
            }
            sb.append(t.getClass().getName()).append(": ").append(t.getMessage()).append("\n");
            for (StackTraceElement element : t.getStackTrace()) {
                sb.append("    at ").append(element.getClassName()).append(".").append(element.getMethodName());
                sb.append("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(")\n");
            }
        } while (null != (c = c.getCause()));
        return sb.toString();
    }

}
