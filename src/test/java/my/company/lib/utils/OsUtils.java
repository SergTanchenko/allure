package my.company.lib.utils;

/**
 * Created by Serg on 04.11.2014.
 */
public final class OsUtils {
    private static String OS = null;

    private OsUtils() {
    }

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix() {
        return getOsName().startsWith("Windows");
    }
}
