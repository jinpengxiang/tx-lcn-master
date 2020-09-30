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
package com.txlcn.txmsg.params;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Date: 19-1-24 下午5:42
 *
 * @author ujued
 */
@Data
public class RelayNotifyUnitParams implements Serializable {
    private NotifyUnitParams notifyUnitParams;
    private String modId;
}
