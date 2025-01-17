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
package com.txlcn.txmsg.netty;

import com.txlcn.common.runner.TxLcnInitializer;
import com.txlcn.txmsg.RpcConfig;
import com.txlcn.txmsg.netty.bean.RpcCmdContext;
import com.txlcn.txmsg.netty.bean.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Company: CodingApi
 * Date: 2019/1/16
 *
 * @author codingapi
 */
@Component
public class RpcNettyInitializer implements TxLcnInitializer {

    @Autowired
    private RpcConfig rpcConfig;

    @Override
    public void init() throws Exception {
        RpcCmdContext.getInstance().setRpcConfig(rpcConfig);
        SocketManager.getInstance().setRpcConfig(rpcConfig);
    }
}
