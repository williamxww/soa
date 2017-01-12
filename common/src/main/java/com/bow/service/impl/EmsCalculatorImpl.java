package com.bow.service.impl;

import com.bow.service.EmsCalculator;

/**
 * @author vv
 * @since 2017/1/12.
 */
public class EmsCalculatorImpl implements EmsCalculator {
    @Override
    public int calculate(String emsId, int a, int b) {
        return (a + b) * 2;
    }
}
