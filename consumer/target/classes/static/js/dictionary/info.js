layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on('select(type_dic)', function(data){
        console.log(data);
        if(data.value == 2){
            $('#parent_dic').removeClass('layui-hide');
        }else{
            $('#parent_dic').addClass('layui-hide');
        }
        form.render(null, 'parent_dic');
    });

    //添加验证规则
    form.verify({
        fzCompanyLimitTime : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    $.post("/dictionary/selectListData",{
        dicType : 1
    },function(data){
        var roleList = data.data;
        roleList.forEach(function(e){
            $("#dicParentSelect").append("<option value='"+e.dicId+"'>"+e.dicName+"</option>");
        });
        $("#dicParentSelect").val($("#dicParent").val());//默认选中
        form.render('select');//刷新select选择框渲染
    });

    form.on("submit(addDictionary)",function(data){
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#dicId").val()==="") {
            $.post("/dictionary/add",data.field,function(data){
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
            $.post("/dictionary/edit",data.field,function(data){
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