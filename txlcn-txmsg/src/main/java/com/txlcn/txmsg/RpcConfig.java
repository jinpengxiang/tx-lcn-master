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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Date: 19-1-9 下午6:04
 *
 * @author ujued
 */
@NoArgsConstructor
@Data
public class RpcConfig {
//    /**
//     * 最大等待时间 (ms)
//     */
//    private long waitTime = -1;
//
//    /**
//     * 最大缓存锁的数量
//     */
//    private int cacheSize = 1024;
//
//    /**
//     * appName 参数延迟删除时间(ms)
//     */
//    private long attrDelayTime = -1;
//
//    /**
//     * 断线重连次数
//     */
//    private int reconnectCount = 8;
//
//    /**
//     * 重连延迟时间(ms)
//     */
//    private long reconnectDelay = 6000;

    /**
     * 最大等待时间 (ms)
     */
    private long waitTime = -1;

    /**
     * 最大缓存锁的数量
     */
    private int cacheSize = 1024;

    /**
     * appName 参数延迟删除时间(ms)
     */
    private long attrDelayTime = -1;

    /**
     * 断线重连次数
     */
    private int reconnectCount = 8;

    /**
     * 重连延迟时间(ms)
     */
    private long reconnectDelay = 6000;

    /**
     * 是否限制断线重连次数
     */
    private boolean reconnectLimit = false;
}
