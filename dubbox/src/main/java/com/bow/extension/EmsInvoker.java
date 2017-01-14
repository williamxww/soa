package com.bow.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Directory;

import java.util.List;

/**
 * @author vv
 * @since 2017/1/12.
 */
public class EmsInvoker<T> implements Invoker<T> {

    private Directory<T> directory;

    public EmsInvoker(Directory<T> directory) {
        System.out.println("init....");
        this.directory = directory;
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
        Object arg0 = invocation.getArguments()[0];
        if (arg0 == null || !(arg0 instanceof Integer)) {
            throw new IllegalStateException("first argument in ems interface must be emsId(Integer), not "+arg0);
        }
        int emsId = (int) arg0;
        List<Invoker<T>> invokers = directory.list(invocation);

        Invoker selectedInvoker = null;
        for (Invoker invoker : invokers) {
            String providerModuleStr = invoker.getUrl().getParameter("module");
            Integer providerModule = Integer.parseInt(providerModuleStr);
            if (emsId == providerModule) {
                selectedInvoker = invoker;
                break;
            }
        }

        if (selectedInvoker == null) {
            throw new IllegalStateException("no provider for ems, id " + emsId);
        }
        return selectedInvoker.invoke(invocation);
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
