layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $.post("/role/selectListData",{
        available : 1
    },function(data){
        var roleList = data.data;
        roleList.forEach(function(e){
            $("#roleSelect").append("<option value='"+e.id+"'>"+e.roleName+"</option>");
        });
        $("#roleSelect").val($("#roleId").val());//默认选中
        form.render('select');//刷新select选择框渲染
    });

    $.post("/org/typeSelectData",function(data){
        var sysOrgTypeList = data.data;
        sysOrgTypeList.forEach(function(e){
            $("#sysOrgTypeSelect").append("<option value='"+e.code+"'>"+e.name+"</option>");
        });
        //编辑
        if($("#userCompanyType").val()!==""){
            $("#sysOrgTypeSelect").val($("#userCompanyType").val());//默认选中
            getParentSelectData($("#userCompanyType").val());
        }
        form.render('select');//重新渲染
    });

    form.on('select(userCompanyType)', function(data){
        if(data.value == 1){
            $("#sysOrgCodeSelect").attr("disabled","disabled");
            $("#sysOrgCodeSelect").removeAttr("lay-verify");
            form.render('select');//重新渲染
        }else{
            $("#sysOrgCodeSelect").removeAttr("disabled");
            $("#sysOrgCodeSelect").attr("lay-verify","required");
            form.render('select');//重新渲染
            getParentSelectData(data.value);
        }
    });

    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/user/add",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        } else {
            $.post("/user/edit",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        }
        return false;
    })


    function getParentSelectData(sysOrgType){
        $.post("/org/parentSelectData",{sysOrgType: sysOrgType},function(data){
            var parentSelectList = data.data;
            $("#sysOrgCodeSelect").empty();//清空下拉列表
            $("#sysOrgCodeSelect").append("<option value=''>请选择权限</option>");
            parentSelectList.forEach(function(e){
                $("#sysOrgCodeSelect").append("<option value='"+e.sysOrgCode+"'>"+e.sysOrgName+"</option>");
            });
            //编辑
            if($("#userCompanyCode").val()!==""){
                $("#sysOrgCodeSelect").val($("#userCompanyCode").val());//默认选中
            }
            form.render('select');//重新渲染
        });
    }

})