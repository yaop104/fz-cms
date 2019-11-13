package com.fangzhi.yao.fzcms.openapi.process;


import static com.fangzhi.yao.fzcms.openapi.DeviceManager.getDeviceStatus;
import static com.fangzhi.yao.fzcms.openapi.DeviceManager.queryDevice;

public class DeviceProcess {

    public static void main(String[] args) {
        DeviceProcess deviceProcess = new DeviceProcess();
        deviceProcess.queryDevices();
    }

    public void deviceStatus(){
        getDeviceStatus("a1r31BeM5cr", "dbcErLkoaG8I0D3SIV2K","");
    }

    public void queryDevices(){
        queryDevice("a1r31BeM5cr", 1, 1);
    }



}
