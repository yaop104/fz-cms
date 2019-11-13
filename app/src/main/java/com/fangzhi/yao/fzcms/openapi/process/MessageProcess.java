package com.fangzhi.yao.fzcms.openapi.process;

import java.util.Base64;

import static com.fangzhi.yao.fzcms.openapi.MessageBrokerManager.rrpc;

public class MessageProcess {


    public static void main(String[] args) {
        MessageProcess productProcess = new MessageProcess();

        productProcess.productTest("hello world 2");

    }

    public void productTest(String data){
        data = Base64.getEncoder().encodeToString(data.getBytes());
        rrpc("a1r31BeM5cr", "dbcErLkoaG8I0D3SIV2K" , data, 3000, "");
    }

}
