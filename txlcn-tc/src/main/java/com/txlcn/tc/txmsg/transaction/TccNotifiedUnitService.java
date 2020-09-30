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
package com.txlcn.tc.txmsg.transaction;

import com.txlcn.tc.core.context.TCGlobalContext;
import com.txlcn.tc.core.template.TransactionCleanTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Date: 2018/12/11
 *
 * @author 侯存路
 */
@Service("rpc_tcc_notify-unit")
@Slf4j
public class TccNotifiedUnitService extends DefaultNotifiedUnitService {

    @Autowired
    public TccNotifiedUnitService(TransactionCleanTemplate transactionCleanTemplate, TCGlobalContext context) {
        super(transactionCleanTemplate, context);
    }
}
