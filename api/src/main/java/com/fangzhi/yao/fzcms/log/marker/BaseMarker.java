package com.fangzhi.yao.fzcms.log.marker;

import java.util.HashMap;
import java.util.Map;

import static com.fangzhi.yao.fzcms.log.marker.MarkerName.BASE_MARKER;


/**
 * Created by yao
 */
public class BaseMarker extends AbstractMarker {
    private final Map<String, String> extParamMap = new HashMap<>();

    @Override
    public String getName() {
        return BASE_MARKER;
    }

    public void addParam(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        }

        extParamMap.put(key, value);
    }

    public String getParam(String key) {
        return extParamMap.get(key);
    }
}
