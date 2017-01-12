package com.bow.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.integration.RegistryDirectory;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author vv
 * @since 2017/1/12.
 */
public class EmsInvoker<T> implements Invoker<T> {

    private RegistryDirectory<T> directory;

    public EmsInvoker(Directory<T> directory) {
        System.out.println("init....");
        this.directory = (RegistryDirectory<T>)directory;
    }

    /**
     * get service interface.
     *
     * @return service interface.
     */
    @Override
    public Class<T> getInterface() {
        return directory.getInterface();
    }

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        String emsId = (String) invocation.getArguments()[0];
        System.out.println("client "+emsId);
        List<Invoker<T>> invokers = directory.list(invocation);
        for(Invoker invoker : invokers){
            URL url = invoker.getUrl();
            String server = url.getParameter("name");
            System.out.println("server "+server);
        }

        Invoker<T> invoker = invokers.get(0);
        return invoker.invoke(invocation);
    }

    /**
     * get url.
     *
     * @return url.
     */
    @Override
    public URL getUrl() {
        return directory.getUrl();
    }

    /**
     * is available.
     *
     * @return available.
     */
    @Override
    public boolean isAvailable() {
        return directory.isAvailable();
    }

    /**
     * destroy.
     */
    @Override
    public void destroy() {
        directory.destroy();
    }
}
