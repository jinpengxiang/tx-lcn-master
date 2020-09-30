package com.txlcn.tc.txmsg.transaction;

import com.txlcn.common.exception.TxClientException;
import com.txlcn.tc.corelog.aspect.AspectLogHelper;
import com.txlcn.tc.txmsg.RpcExecuteService;
import com.txlcn.tc.txmsg.TransactionCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Description:
 * Date: 19-2-12 上午10:56
 *
 * @author ujued
 */
@Component("rpc_delete-aspect-log")
public class DeleteAspectLogService implements RpcExecuteService {

    private final AspectLogHelper aspectLogHelper;

    @Autowired
    public DeleteAspectLogService(AspectLogHelper aspectLogHelper) {
        this.aspectLogHelper = aspectLogHelper;
    }

    @Override
    public Serializable execute(TransactionCmd transactionCmd) throws TxClientException {
        aspectLogHelper.delete(transactionCmd.getGroupId());
        return null;
    }
}
