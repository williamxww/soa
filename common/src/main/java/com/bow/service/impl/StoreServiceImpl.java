package com.bow.service.impl;

import java.util.concurrent.TimeUnit;

import com.bow.entity.Data;
import com.bow.service.StoreService;

/**
 * @author vv
 * @since 2016/12/26.
 */
public class StoreServiceImpl implements StoreService{

    /**
     * 模拟耗时
     * @return true
     */
    @Override
    public boolean save(Data data) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("data stored ");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
