package com.fangzhi.yao.fzcms.openapi.process;

import com.aliyuncs.iot.model.v20180120.CreateProductResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;
import com.fangzhi.yao.fzcms.util.ServiceUtil;

import static com.fangzhi.yao.fzcms.openapi.DeviceManager.registerDevice;
import static com.fangzhi.yao.fzcms.openapi.DeviceTagsManager.*;
import static com.fangzhi.yao.fzcms.openapi.ProductManager.createProductTest;


public class DivceTagsProcess {

    public static void main(String[] args) {
        DivceTagsProcess
            process = new DivceTagsProcess();

        process.deviceOne();
    }

    /**
     * 1 创建一个高级设备产品
     * 2 创建一个设备
     * 3 为这个设备设置标签
     * 4 删除设备下的指定标签
     * 5 查询指定设备的标签列表
     */
    public void deviceOne() {

        //1 创建 高级设备产品
        CreateProductResponse.Data product = createProductTest(ServiceUtil.productNameGenerator(), 0, null, 0,
            null, null, "", "wifi","secret");

        //2添加一个设备
        RegisterDeviceResponse.Data deviceObject = registerDevice(product.getProductKey(), null);

        // 3 为这个设备设置标签
        saveDeviceProp(deviceObject.getProductKey(), deviceObject.getDeviceName(),"{\"key\":\"1"
            + "\",\"keytwo\":\"2\",\"keyThree\":\"3\"}");

        // 4 删除设备下的指定标签
        deleteDeviceProp(deviceObject.getProductKey(), deviceObject.getDeviceName(),"key");

        // 5 查询指定设备的标签列表
        queryDeviceProp(deviceObject.getProductKey(), deviceObject.getDeviceName());

    }

}
