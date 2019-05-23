package com.fangzhi.yao.fzcms.thirdparty.sms.impl;

import com.alibaba.fastjson.JSONObject;
import com.fangzhi.yao.fzcms.config.third.FzSms;
import com.fangzhi.yao.fzcms.dto.third.MessageBody;
import com.fangzhi.yao.fzcms.dto.third.SmsMessage;
import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.fangzhi.yao.fzcms.repo.SendMessageRepo;
import com.fangzhi.yao.fzcms.thirdparty.sms.SmsSendMessageService;
import com.fangzhi.yao.fzcms.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SendMessageImpl implements SmsSendMessageService {

	@Autowired
	FzSms fzSms;

	@Autowired
	SendMessageRepo sendMessageRepo;

	private static ExecutorService executor =
			new ThreadPoolExecutor(5, 20, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

	//================== begin ======================

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendMessage(SmsMessage smsMessage) {
		try {
			//添加记录
			SendMessage sendMessage = new SendMessage();
			sendMessage.setFzCompanyCode("0001");
			sendMessage.setFzSendCode(smsMessage.getSmsCode());
			sendMessage.setFzSendMobile(smsMessage.getPhone());
			sendMessage.setFzSendState("2");
			sendMessage.setFzSendType("1");
			sendMessageRepo.insert(sendMessage);

			//发送短信
			sendMessageInfo(smsMessage.getPhone(), smsMessage.getSmsCode());

		} catch (Exception e) {
//			log.error("sendMessage ERROR : " , e);
		}
		
	}


	public void sendMessageInfo(String mobile, String code) {
		if(StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(code)){
			executor.submit(() -> sendPhone(mobile, code));
		}
	}

	public boolean sendPhone(String phone, String pwd){

		Boolean flag = false;

		String host = fzSms.getUrl();
		String path = fzSms.getPath();
		String method = "GET";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + fzSms.getAppcode());
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("param", pwd);
		querys.put("phone", phone);
		querys.put("sign", fzSms.getSign());
		querys.put("skin", fzSms.getSkin());

		try {

			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			//获取response的body
			String responseBody = EntityUtils.toString(response.getEntity());
			MessageBody messageBody = JSONObject.parseObject(responseBody, MessageBody.class);
			if(messageBody.getCode().equals("OK")){
				flag = true;
			}
		} catch (Exception e) {
//			log.error("SMS SEND ERROR : " , e);
		}
		return  flag;
	}
	//================== end ======================
}
