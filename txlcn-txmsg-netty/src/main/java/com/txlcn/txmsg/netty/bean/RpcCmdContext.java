/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.txlcn.txmsg.netty.bean;

import com.txlcn.txmsg.RpcConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author ujued
 */
@Slf4j
public class RpcCmdContext {
    
    private int cacheSize = 1024;
    
    private long waitTime = 1;
    
    private static RpcCmdContext context = null;
    
    private Map<String, RpcContent> map;
    
    private List<RpcContent> cacheList;
    
    private final LinkedList<RpcContent> freeList;
    
    public static RpcCmdContext getInstance() {
        if (context == null) {
            synchronized (RpcCmdContext.class) {
                if (context == null) {
                    context = new RpcCmdContext();
                }
            }
        }
        return context;
    }
    
    private RpcCmdContext() {
        map = new ConcurrentHashMap<>();
        
        cacheList = new CopyOnWriteArrayList<>();
        
        freeList = new LinkedList<>();
    }
    
    
    /**
     * 并发可能会出现脏读
     *
     * @param key key
     * @return hasKey
     */
    public synchronized boolean hasKey(String key) {
        return map.containsKey(key);
    }
    
    /**
     * 并发可能会出重复添加
     *
     * @param key key
     * @return RpcContent
     */
    public synchronized RpcContent addKey(String key) {
        RpcContent rpcContent = createRpcContent();
        map.put(key, rpcContent);
        return rpcContent;
    }
    
    
    /**
     * 空闲队列处理
     *
     * @return RpcContent
     */
    private RpcContent findRpcContent() {
        synchronized (freeList) {
            RpcContent cacheContent = freeList.getFirst();
            if (!cacheContent.isUsed()) {
                cacheContent.init();
                freeList.remove(cacheContent);
                return cacheContent;
            }
        }
        
        RpcContent rpcContent = new RpcContent(getWaitTime());
        rpcContent.init();
        return rpcContent;
    }
    
    private RpcContent createRpcContent() {
        if (cacheList.size() < cacheSize) {
            RpcContent rpcContent = new RpcContent(getWaitTime());
            rpcContent.init();
            cacheList.add(rpcContent);
            return rpcContent;
        } else {
            return findRpcContent();
        }
    }
    
    public RpcContent getKey(String key) {
        RpcContent rpcContent = map.get(key);
        clearKey(key);
        return rpcContent;
    }
    
    private void clearKey(String key) {
        RpcContent rpcContent = map.get(key);
        if (cacheList.contains(rpcContent)) {
            synchronized (freeList) {
                freeList.add(rpcContent);
            }
        }
        map.remove(key);
    }
    
    
    public void setRpcConfig(RpcConfig rpcConfig) {
        cacheSize = rpcConfig.getCacheSize();
        // TC or TX init after
        waitTime = rpcConfig.getWaitTime() == -1 ? 500 : rpcConfig.getWaitTime();
    }
    
    public long getWaitTime() {
        return waitTime;
    }
}
