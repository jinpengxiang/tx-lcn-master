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

import com.txlcn.common.util.id.RandomUtils;
import com.txlcn.txmsg.dto.MessageDto;
import com.txlcn.txmsg.dto.RpcCmd;
import com.txlcn.txmsg.exception.RpcException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author ujued
 */
@Slf4j
public class NettyRpcCmd extends RpcCmd {

    private volatile transient RpcContent rpcContent;

    public String randomKey() {
        String key = RandomUtils.randomKey();
        if (RpcCmdContext.getInstance().hasKey(key)) {
            return randomKey();
        } else {
            rpcContent = RpcCmdContext.getInstance().addKey(key);
        }
        return key;
    }

    @Override
    public MessageDto loadResult() throws RpcException {
        MessageDto msg = rpcContent.getRes();
        if (msg == null) {
            throw new RpcException("request timeout.");
        }
        log.debug("got response. {} ", getKey());
        return msg;
    }

    public RpcContent loadRpcContent() {
        if (rpcContent == null) {
            rpcContent = RpcCmdContext.getInstance().getKey(getKey());
        }
        return rpcContent;
    }

    public void await() {
        if (Objects.nonNull(rpcContent.getRes())) {
            return;
        }
        rpcContent.await();
    }

    public void await(long timeout) {
        if (Objects.nonNull(rpcContent.getRes())) {
            return;
        }
        rpcContent.await(timeout);
    }

}
