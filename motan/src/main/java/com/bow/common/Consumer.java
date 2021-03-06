package com.bow.common;

import java.util.concurrent.TimeUnit;

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
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"com/bow/common/consumer.xml"});
        while(true){
            Calculator calculator = context.getBean(Calculator.class);
            System.out.println("result>>>"+calculator.calculate(1,1));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
