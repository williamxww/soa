package com.bow.common;

import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.registry.RegistryService;
import com.bow.extension.EmsState;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author vv
 * @since 2016/12/12.
 */
public class Provider {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"com/bow/common/provider.xml"});
        EmsState state = context.getBean("emsState",EmsState.class);
        state.register();
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
