package com.fangzhi.yao.fzcms.api;

import com.aliyuncs.iot.model.v20180120.QueryDeviceResponse;
import com.aliyuncs.iot.model.v20180120.RRpcResponse;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.third.IotCommonDTO;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.openapi.DeviceManager;
import com.fangzhi.yao.fzcms.util.Base64Converter;
import com.fangzhi.yao.fzcms.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fangzhi.yao.fzcms.openapi.MessageBrokerManager.rrpc;


/**
 * <p>
 * 发送表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
@RestController
@RequestMapping("iot")
public class IotController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="getDeviceList")
    public ResultInfo getDeviceList(@RequestBody(required = false) IotCommonDTO iotCommonDTO){
        try {
            List<QueryDeviceResponse.DeviceInfo> deviceInfoList = DeviceManager.queryDevice(iotCommonDTO.getProductKey(),iotCommonDTO.getPageSize(), iotCommonDTO.getOffset());
            return  resOk(deviceInfoList);
        } catch (Exception e) {
            logger.error( "getDeviceList-错误：{}, message:{}",GsonUtil.GSON.toJson(iotCommonDTO),  e.getMessage());
            throw new BusinessException("-1", "失败，系统异常，稍后再试！");
        }
    }

    @PostMapping("sendMessage")
    public ResultInfo sendMessageToDevice(@RequestBody(required = false) IotCommonDTO iotCommonDTO){
        try {
            String data = Base64Converter.encode(iotCommonDTO.getRequestMessage());
            RRpcResponse response = rrpc(iotCommonDTO.getProductKey(), iotCommonDTO.getDeviceName() , data, 3000, "");
            return resOk(Base64Converter.decode(response.getPayloadBase64Byte()));
        }catch (Exception e){
            logger.error( "sendMessageToDevice-错误：{}, message:{}", GsonUtil.GSON.toJson(iotCommonDTO), e.getMessage());
            throw new BusinessException("-1", "失败，系统异常，稍后再试！");
        }
    }
}

