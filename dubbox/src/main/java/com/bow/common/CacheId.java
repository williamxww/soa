package com.bow.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author vv
 * @since 2017/1/8.
 */
public class CacheId {

    private static final Integer ROOT_ID = 0;

    private static final Integer ROOT_TYPE = 0;

    private static final Integer EMS_TYPE = 1;

    private static final Integer NE_TYPE = 2;

    private static final Integer BOARD_TYPE = 3;

    private static final Integer ONU_TYPE = 4;

    private static final Integer PORT_TYPE = 5;

    private Integer id;

    private Integer type;

    private Map<Integer, CacheId> children;

    public CacheId() {
        // root
        this.id = ROOT_ID;
        this.type = ROOT_TYPE;
    }

    public CacheId(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public void put(Integer emsId) {
        this.put(emsId, null, null, null);
    }

    public void put(Integer emsId, Integer neId) {
        this.put(emsId, neId, null, null);
    }

    public void put(Integer emsId, Integer neId, Integer boardId) {
        this.put(emsId, neId, boardId, null);
    }

    public void put(Integer emsId, Integer neId, Integer boardId, Integer portNo) {
        // add ems to root
        CacheId ems = appendIfAbsent(this, emsId, EMS_TYPE);
        CacheId ne = appendIfAbsent(ems, neId, NE_TYPE);
        CacheId board = appendIfAbsent(ne, boardId, BOARD_TYPE);
        appendIfAbsent(board, portNo, PORT_TYPE);

    }

    public Set<Integer> getEmsIds() {
        return this.children.keySet();
    }

    public Set<Integer> getNeIds(Integer emsId) {
        CacheId ems = this.children.get(emsId);
        return ems.children.keySet();
    }

    public Set<Integer> getBoardIds(Integer emsId) {
        Set<Integer> result = new HashSet();
        CacheId ems = this.children.get(emsId);
        for (CacheId ne : ems.children.values()) {
            result.addAll(ne.children.keySet());
        }
        return result;
    }

    private CacheId appendIfAbsent(CacheId cache, Integer id, Integer type) {
        if (cache == null || id == null || type == null) {
            return null;
        }
        if (cache.children == null) {
            cache.children = new HashMap();
        }
        CacheId child = cache.children.get(id);
        if (child == null) {
            child = new CacheId(id, type);
            cache.children.put(id, child);
        }
        return child;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Map<Integer, CacheId> getChildren() {
        return children;
    }

    public void setChildren(Map<Integer, CacheId> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        CacheId cache = new CacheId();
        cache.put(1000, 100, 10, 1);
        cache.put(1000, 100, 11, 2);
        cache.put(1000, 100, 12, 2);
        cache.put(1000, 101, 13, 2);
        cache.put(2000, 101, 13, 2);
        System.out.println(cache.getNeIds(1000));
        System.out.println(cache.getBoardIds(1000));
    }
}
