//package com.fangzhi.yao.fzcms.thirdparty.iot;
//
//import com.aliyun.openservices.iot.api.Profile;
//import com.aliyun.openservices.iot.api.message.MessageClientFactory;
//import com.aliyun.openservices.iot.api.message.api.MessageClient;
//import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
//import com.aliyun.openservices.iot.api.message.entity.Message;
//import lombok.extern.slf4j.Slf4j;
//
//import java.net.UnknownHostException;
//import java.util.concurrent.ExecutionException;
//@Slf4j
////@Component
//public class H2Client {
//
////    @PostConstruct
//    public static void getIotMessage(){
//        // 阿里云accessKey
//        String accessKey = "LTAI4FxtVfRpc1YRFfeay4Y4";
//        // 阿里云accessSecret
//        String accessSecret = "VZneeiCXGjMHIBT6McLzUr3BWEIulh";
//        // regionId
//        String regionId = "cn-shanghai";
//        // 阿里云uid
//        String uid = "1560272274927402";
//        // endPoint:  https://${uid}.iot-as-http2.${region}.aliyuncs.com
//        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";
//
//        // 连接配置
//        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);
//
//        // 构造客户端
//        MessageClient client = MessageClientFactory.messageClient(profile);
//
//        // 数据接收
//        client.connect(messageToken -> {
//            Message m = messageToken.getMessage();
//            System.out.println("receive message from " + m);
//            return MessageCallback.Action.CommitSuccess;
//        });
//    }
//
//
//    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
//        getIotMessage();
//    }
//}