package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties props = new Properties();
    private static boolean loaded = false;

    private static void loadIfNeeded() {
        if (loaded) return;
        loaded = true;

        // Try to load from classpath: src/test/resources/config.properties
        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            // ignore - file can be missing in CI
        }
    }

    public static String get(String key) {
        loadIfNeeded();

        // 1) JVM system property: -Dui.users=...
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys.trim();

        // 2) ENV mapping: ui.users -> UI_USERS
        String envKey = toEnvKey(key);
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) return env.trim();

        // 3) properties file
        String val = props.getProperty(key);
        if (val != null && !val.isBlank()) return val.trim();

        return null;
    }

    public static String getRequired(String key) {
        String val = get(key);
        if (val == null) {
            throw new IllegalStateException("Missing required config key: " + key +
                    " (set env var " + toEnvKey(key) + " or system property -D" + key + " or config.properties)");
        }
        return val;
    }

    private static String toEnvKey(String key) {
        // ui.base.url -> UI_BASE_URL
        return key.toUpperCase()
                .replace('.', '_')
                .replace('-', '_');
    }
}
