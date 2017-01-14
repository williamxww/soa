package com.bow.common;

import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bow.service.EmsCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bow.service.Calculator;

/**
 * @author vv
 * @since 2016/12/12.
 */
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger", "slf4j");
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "com/bow/common/consumer.xml" });
        while (true) {
            EmsCalculator calculator1 = context.getBean(EmsCalculator.class);
            Calculator calculator2 = context.getBean(Calculator.class);
            System.out.println("calculator1>>>" + calculator1.calculate(10000, 1, 1));
            System.out.println("calculator2>>>" + calculator2.calculate(2, 2));
            System.out.println("wu la la");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
