package com.bow.common;

import java.util.concurrent.TimeUnit;

import com.bow.entity.Data;
import com.bow.service.EmsCalculator;
import com.bow.service.EmsNeService;
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
            System.out.println("calculator ems1>>>" + calculator1.calculate(10000, 1, 1));
            System.out.println("calculator ems2>>>" + calculator1.calculate(20000, 1, 1));
            System.out.println("calculator>>>" + calculator2.calculate(2, 2));

            EmsNeService neService = context.getBean(EmsNeService.class);
            Data data = neService.getNe(20000);
            System.out.println("neService.getNe>>>" + data);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
