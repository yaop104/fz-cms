package com.fangzhi.yao.fzcms.context;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import com.fangzhi.yao.fzcms.util.GsonUtil;
import org.apache.dubbo.rpc.RpcInvocation;

import static com.fangzhi.yao.fzcms.code.ConstantYao.ATTACHMENT_KEY_OF_DJCONTEXT;

/**
 * <p>
 * 覆盖原有的ConsumerContextFilter
 */
@Activate(group = Constants.CONSUMER, order = -10001)
public class ConsumerContextFilter implements Filter {

    private static final Log LOGGER = LogFactory.getLog(ConsumerContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        DJContext djContext = DJContext.getContext();
        //add some other data into dongjiaContext.attachments

        String spanId = djContext.getSpanId();
        //spid递增
        djContext.setSpanId(djContext.increaseNextSpandId());
        RpcContext.getContext()
                .setAttachment(ATTACHMENT_KEY_OF_DJCONTEXT, GsonUtil.GSON.toJson(djContext));

        djContext.setSpanId(spanId);

        if (invocation instanceof RpcInvocation) {
            ((RpcInvocation) invocation).setInvoker(invoker);
        }


        //执行远程调用
        return invoker.invoke(invocation);
    }
}
