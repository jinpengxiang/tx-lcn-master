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
package com.txlcn.common.exception;

/**
 * Description:
 * Date: 19-1-14 上午9:58
 *
 * @author ujued
 */
public class TxcLogicException extends Exception {

    private Object attachment;

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public TxcLogicException() {
    }

    public TxcLogicException(String message) {
        super(message);
    }

    public TxcLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public TxcLogicException(Throwable cause) {
        super(cause);
    }

    public TxcLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
