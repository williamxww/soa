package com.bow.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.integration.RegistryDirectory;

/**
 * @author vv
 * @since 2017/1/12.
 */
public class EmsDirectory<T> extends RegistryDirectory<T> {

    public EmsDirectory(Class<T> serviceType, URL url) {
        super(serviceType, url);
    }
}
