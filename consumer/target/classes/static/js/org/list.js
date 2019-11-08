layui.config({
    base : "/static/js/modules/"
}).extend({
    "treetable" : "treetable/treetable"
});
layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treetable = layui.treetable;

    // 渲染表格
    layer.load(2);
    treetable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'sysOrgCode',
        treePidName: 'sysOrgPcode',
        elem: '#org-table',
        url: '/org/listData',
        page: false,
        id: 'orgListTable',
        cols: [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'sysOrgName', minWidth: 100, title: '组织名称'},
            {field: 'sysOrgOutercode', width: 200, title: '组织编码'},
            {
                field: 'sysOrgType', width: 200, align: 'center', templet: function (d) {
                    if (d.sysOrgType === 1) {
                        return '<span class="layui-badge layui-bg-blue">公司</span>';
                    }else if (d.sysOrgType === 2) {
                        return '<span class="layui-badge-rim">部门</span>';
                    } else if (d.sysOrgType === 3)  {
                        return '<span class="layui-badge layui-bg-gray">小组</span>';
                    }
                }, title: '组织类型'
            },
            {
                field: 'sysOrgState', width: 200, align: 'center', templet: function (d) {
                    if (d.sysOrgState === "1") {
                        return '<span class="layui-badge layui-bg-green">正常</span>';
                    } else {
                        return '<span class="layui-badge layui-bg-cyan">停用</span>';
                    }
                }, title: '组织状态'
            }
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    //添加
    function addOrg(edit){
        var title = edit===null?"添加":"编辑";
        layui.layer.open({
            title : title,
            type : 2,
            area : ["500px","450px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    console.info(edit);
                    body.find("#sysOrgId").val(edit.sysOrgId);
                    body.find("#sysOrgName").val(edit.sysOrgName);
                    body.find("#sysOrgOutercode").val(edit.sysOrgOutercode);
                    body.find("#sysOrgStateSelect").val(edit.sysOrgState);
                    body.find("#sysOrgPcode").val(edit.sysOrgPcode);
                    body.find("#sysOrgType").val(edit.sysOrgType);
                    body.find("#sysOrgCode").val(edit.sysOrgCode);
                    body.find("#sysOrgOrder").val(edit.sysOrgOrder);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    }

    $(".add_btn").click(function(){
        addOrg(null);
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('orgListTable'),
            data = checkStatus.data;
        console.log(data);
        if(data.length > 0){
            addOrg(data[0]);
        }else{
            layer.msg("请选择需要修改的权限");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('orgListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                if(data.length>1&&data[i].sysOrgType!=="3"){
                    layer.msg("抱歉，只支持批量删除小组！");
                    return;
                }
                idArr.push(data[i].sysOrgId);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "/org/delBatch",
                    type: "post",
                    data: {idArr : idArr.toString()},
                    success: function(res){
                        layer.close(index);
                        if (res.data){
                            layer.msg("删除成功！");
                        } else {
                            layer.msg(res.msg);
                        }
                    },
                    error: function (xmlHttpRequest) {
                        outMsg(xmlHttpRequest);
                    }
                });
                setTimeout(function(){
                    location.reload();//刷新页面
                },1000);
            });
        }else{
            layer.msg("请选择需要删除的记录");
        }
    });

    function outMsg(xmlHttpRequest){
        // 状态码
        console.log(xmlHttpRequest.status);
        // 返回信息
        var jsonObj = $.parseJSON(xmlHttpRequest.responseText);
        console.log(jsonObj.msg);
        layer.msg(jsonObj.msg);
    }

});