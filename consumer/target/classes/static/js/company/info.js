layui.use(['form','layer','laydate'],function(){
    var form = layui.form,
        laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //常规用法
    laydate.render({
        elem: '#fzCompanyLimitTime'
    });

    //添加验证规则
    form.verify({
        fzCompanyName : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        fzCompanyAddress : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        },
        fzCompanyPerson : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        },
        fzCompanyMobile : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        },
        fzCompanyLimitTime : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    form.on("submit(addCompany)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#fzCompanyCode").val()==="") {
            $.post("/company/add",data.field,function(data){
                if (data.data){
                    layer.close(index);
                    layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.close(index);
                    layer.msg(data.msg);
                }
            })
        } else {
            $.post("/company/edit",data.field,function(data){
                if (data.data){
                    layer.close(index);
                    layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.close(index);
                    layer.msg(data.msg);
                }
            })
        }
        return false;
    })

})