package com.fangzhi.yao.fzcms.context;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.fangzhi.yao.fzcms.code.GrayConst;
import com.fangzhi.yao.fzcms.generator.SpanIdGenerator;
import com.fangzhi.yao.fzcms.generator.UniqueIdGenerator;
import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import com.fangzhi.yao.fzcms.log.LoggerName;
import com.fangzhi.yao.fzcms.util.GsonUtil;
import com.fangzhi.yao.fzcms.util.Utils;

import javax.servlet.http.HttpServletRequest;

import static com.fangzhi.yao.fzcms.code.ConstantYao.ATTACHMENT_KEY_OF_DJCONTEXT;
import static com.fangzhi.yao.fzcms.util.IpAddrUtil.getIp;


/**
 * <p>
 * dubbo provider Filter
 * 将rpcContext中dongjiaContext反序列化
 * 将rpcContext中DJContext反序列化
 */
@Activate(group = Constants.PROVIDER, order = Integer.MAX_VALUE)
public class ProviderContextFilter implements Filter {

    private static final Log INTERFACE_LOGGER = LogFactory.getLog(LoggerName.INTERFACE);
    private static final Log LOGGER = LogFactory.getLog(ProviderContextFilter.class);
    private final static String DEVICE_ID = "Device-Id";
    private final static String ATTACHMENT_DEVICE_ID = "deviceId";
    private final static String IP = "ip";


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        RpcContext rpcContext = RpcContext.getContext();
        DJContext djContext;

        //rest接口从head中初始化DJContext
        if (null != rpcContext.getRequest() && rpcContext.getRequest()
                instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) rpcContext.getRequest();

            djContext = DJContext.getContext();
            initDJContext(djContext, request);
        }
        //dubbo接口调用，从arpcContext中获取附件信息
        else {

            //DJContext set ThreadLocal
            String contextStr = rpcContext.getAttachment(ATTACHMENT_KEY_OF_DJCONTEXT);
            if (Utils.isEmpty(contextStr)) {
                djContext = DJContext.getContext();
                LOGGER.debug("请求没有context信息");
            } else {
                djContext = GsonUtil.GSON.fromJson(contextStr, DJContext.class);
                DJContext.setContext(djContext);
            }

            //SpanId在接入新的请求的时候需要初始化为.0结尾
            djContext.setInitialSpanId(djContext.getSpanId());
            djContext.setSpanId(SpanIdGenerator.formatSpan(djContext.getSpanId()));
            djContext.setNextSpanId(djContext.getSpanId());
        }

        //处理接口到达日志
        StringBuilder logRecMsg = new StringBuilder().append("Receive ")
                .append(invoker.getInterface().getSimpleName()).append(".")
                .append(invocation.getMethodName()).append(" request");
        for (int i = 0; i < invocation.getArguments().length; i++) {
            if (i != invocation.getArguments().length - 1) {
                logRecMsg.append(" {").append(invocation.getArguments()[i])
                        .append(":").append(invocation.getParameterTypes()[i]
                        .getSimpleName()).append("},");
            } else {
                logRecMsg.append(" {").append(invocation.getArguments()[i])
                        .append(":").append(invocation.getParameterTypes()[i]
                        .getSimpleName()).append("};");
            }
        }

        INTERFACE_LOGGER.info(logRecMsg.toString());
        LOGGER.debug("djContext: {}", djContext);
        Result result = null;
        try {
            result = invoker.invoke(invocation);
        } finally {
            //处理接口离开日志
            logRecMsg.setLength(0);
            logRecMsg.append("Handle ").append(invoker.getInterface().getSimpleName()).append(".")
                    .append(invocation.getMethodName()).append(" request end");
            if (null != result) {
                logRecMsg.append(", response ").append(result.getValue());
            }
            INTERFACE_LOGGER.info(logRecMsg.toString(), djContext.getUniqueId());

            //清除TheadLocal数据
            DJContext.removeContext();
        }
        return result;
    }

    private void initDJContext(DJContext context, HttpServletRequest request) {
        //如果zipkin已经设置了tranceid，这边就不需要再设置
        if (null == context.getUniqueId()) {
            context.setUniqueId(UniqueIdGenerator.getInstance().generate());
        }
        String userId = request.getHeader("uid");
        String customerId = request.getHeader("customerId");
        String adminFlag = request.getHeader("af");
        context.setUid(Utils.isEmpty(userId) ? 0L : Long.valueOf(userId));
        context.setCustomerId(Utils.isEmpty(customerId) ? 0 : Long.valueOf(customerId));
        context.setAf(Utils.isEmpty(adminFlag) ? 0 : Integer.valueOf(adminFlag));
        context.setSpanId(SpanIdGenerator.initSpanId());

        // 取出灰度数据
        context.setAttachment(GrayConst.KEY, request.getHeader(GrayConst.KEY));
        context.setAttachment(ATTACHMENT_DEVICE_ID, request.getHeader(DEVICE_ID));
        context.setAttachment(IP, getIp(request));

    }
}
