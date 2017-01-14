package com.bow.service.impl;

import com.bow.entity.Data;
import com.bow.service.EmsNeService;

/**
 * @author vv
 * @since 2017/1/14.
 */
public class EmsNeServiceImpl implements EmsNeService {
    @Override
    public Data getNe(Integer emsId) {
        Data data = new Data();
        data.setField1("hello ems");
        return data;
    }
}
