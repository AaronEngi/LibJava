package tyrael.jdkextend.collection;

import java.util.Map;

import javax.annotation.Nonnull;

public class MapStatic {
    @Nonnull
    public static <K, V> V getNonnull(Map<K, V> map, K key, @Nonnull V defaultValue) {
        V v = map.get(key);
        return v == null ? defaultValue : v;
    }
}
