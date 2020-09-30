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
package com.txlcn.tracing.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.txlcn.tracing.Tracings;

/**
 * Description: 接收Tracing需要的数据
 * Date: 19-1-28 下午5:08
 *
 * @author ujued
 */
@Activate(group = {Constants.PROVIDER})
public class DubboTracingApplier implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Tracings.apply(invocation::getAttachment);
        return invoker.invoke(invocation);
    }
}
