package config;

public class OsConfig {

    public static void getOperatingSystems(Runnable doMacOS, Runnable doWindowsOS, Runnable doLinuxOS) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            doMacOS.run();
        } else if (os.contains("win")) {
            doWindowsOS.run();
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            doLinuxOS.run();
        }
    }
}