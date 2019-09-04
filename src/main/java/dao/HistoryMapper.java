package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by czq
 * time on 2019/9/4  20:41
 */
public interface HistoryMapper {


    Map<String, Map<String, String>> cache = new ConcurrentHashMap<>();


    default String selectOne(String user, String video) {
        Map<String, String> stringStringMap = cache.get(user);
        if (stringStringMap != null) {
            return stringStringMap.getOrDefault(video, "0");
        }
        String s = Optional.ofNullable(selectOneFromDb(user, video)).orElse("0");
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put(video, s);
        cache.put(user, map);
        return s;
    }


    String selectOneFromDb(String user, String video);


}
