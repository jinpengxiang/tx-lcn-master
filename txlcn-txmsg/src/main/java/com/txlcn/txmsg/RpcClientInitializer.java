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
package com.txlcn.txmsg;

import com.txlcn.txmsg.dto.TxManagerHost;

import java.net.SocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author ujued
 */
public interface RpcClientInitializer {


    /**
     * message client init
     *
     * @param hosts manager host list
     * @param sync  is sync
     */
    void init(List<TxManagerHost> hosts, boolean sync);

    /**
     * 建立连接
     *
     * @param socketAddress 远程连接对象
     * @return future
     */
    Optional<Future> connect(SocketAddress socketAddress);

}
