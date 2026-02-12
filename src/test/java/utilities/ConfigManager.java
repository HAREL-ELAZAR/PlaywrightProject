package utilities;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            // אם הקובץ לא קיים (כי אנחנו ב-CI) זה בסדר
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigManager() {}

    /** מחזיר ערך לפי: ENV -> properties -> error */
    public static String getRequired(String key) {
        String value = getOptional(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required config key: " + key
                    + " (set env var or config.properties)");
        }
        return value;
    }

    /** מחזיר ערך לפי: ENV -> properties -> null */
    public static String getOptional(String key) {
        String envKey = toEnvKey(key);

        String envVal = System.getenv(envKey);
        if (envVal != null && !envVal.isBlank()) {
            return envVal;
        }

        String propVal = props.getProperty(key);
        if (propVal != null && !propVal.isBlank()) {
            return propVal;
        }

        return null;
    }

    /** base.uri -> BASE_URI */
    private static String toEnvKey(String key) {
        return key.toUpperCase().replace('.', '_');
    }
}
