layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $.post("/org/typeSelectData",function(data){
        var sysOrgTypeList = data.data;
        sysOrgTypeList.forEach(function(e){
            $("#sysOrgTypeSelect").append("<option value='"+e.code+"'>"+e.name+"</option>");
        });
        //编辑
        if($("#sysOrgType").val()!==""){
            $("#sysOrgTypeSelect").val($("#sysOrgType").val());//默认选中
            $("#sysOrgTypeSelect").attr("disabled","disabled");
            getParentSelectData($("#sysOrgType").val());
        }
        form.render('select');//重新渲染
    });

    form.on('select(sysOrgType)', function(data){
        if(data.value == 1){
            $("#sysOrgPcodeSelect").attr("disabled","disabled");
            $("#sysOrgPcodeSelect").removeAttr("lay-verify");
            form.render('select');//重新渲染
        }else{
            $("#sysOrgPcodeSelect").removeAttr("disabled");
            $("#sysOrgPcodeSelect").attr("lay-verify","required");
            form.render('select');//重新渲染
            getParentSelectData(data.value);
        }
    });

    function getParentSelectData(sysOrgType){
        $.post("/org/parentSelectData",{sysOrgType: sysOrgType},function(data){
            var parentSelectList = data.data;
            $("#sysOrgPcodeSelect").empty();//清空下拉列表
            $("#sysOrgPcodeSelect").append("<option value=''>请选择权限</option>");
            parentSelectList.forEach(function(e){
                $("#sysOrgPcodeSelect").append("<option value='"+e.sysOrgCode+"'>"+e.sysOrgName+"</option>");
            });
            //编辑
            if($("#sysOrgPcode").val()!==""){
                $("#sysOrgPcodeSelect").val($("#sysOrgPcode").val());//默认选中
                $("#sysOrgPcodeSelect").attr("disabled","disabled");
            }
            form.render('select');//重新渲染
        });
    }

    form.on("submit(addOrg)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        $.post("/org/save",data.field,function(res){
            layer.close(index);
            if (res.data){
                layer.msg("保存成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                layer.msg(res.msg);
            }
        });
        return false;
    });

})