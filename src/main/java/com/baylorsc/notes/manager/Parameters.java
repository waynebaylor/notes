package com.baylorsc.notes.manager;

import java.util.HashMap;
import java.util.Map;

public class Parameters 
{
    private final Map<String, Object> map = new HashMap<String, Object>();
    
    private Parameters() {}
    
    public static Parameters create(Map<String, Object> m) {
        Parameters p = new Parameters();
        p.map.putAll(m);
        return p;
    }
    
    public static Parameters create() {
        return create(new HashMap<String, Object>());
    }
    
    public Parameters set(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
    
    public Map<String, Object> asMap() {
        return new HashMap<String, Object>(this.map);
    }
}
