layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#companyList',
        url : '/company/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "companyListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'fzCompanyName', title: '公司名称', minWidth:100, align:"center"},
            {field: 'fzCompanyAddress', title: '公司地址', minWidth:100, align:'center'},
            {field: 'fzCompanyPerson', title: '联系人', minWidth:100, align:'center'},
            {field: 'fzCompanyMobile', title: '联系号码', minWidth:100, align:'center'},
            {field: 'fzCompanyStatus', title: '公司状态状态', width:80, align:'center',templet:function(d){
                if (d.fzCompanyStatus === 1) {
                    return '<span class="layui-badge layui-bg-green">启用</span>';
                } else if (d.fzCompanyStatus === 2) {
                    return '<span class="layui-badge layui-bg-cyan">注销</span>';
                } else {
                    return '<span class="layui-badge layui-bg-orange">已过期</span>';
                }
            }},
            {field: 'fzCompanyLimitTime', title: '有效期', align:'center',minWidth:100},
            {field: 'fzCompanyCtime', title: '注册时间', align:'center',minWidth:100}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("companyListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                fzCompanyName: $(".fzCompanyName").val(),
                fzCompanyMobile: $(".fzCompanyMobile").val(),
                fzCompanyStatus: $(".fzCompanyStatus").val(),
                fzCompanyPerson: $(".fzCompanyPerson").val()
            }
        })
    });

    //添加
    function addCompany(edit){
        var h = "440px";
        if (edit){
            h = "440px";
        }
        layui.layer.open({
            title : "添加",
            type : 2,
            area : ["420px",h],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#fzCompanyCode").val(edit.fzCompanyCode);
                    body.find("#fzCompanyName").val(edit.fzCompanyName);
                    body.find("#fzCompanyAddress").val(edit.fzCompanyAddress);
                    body.find("#fzCompanyPerson").val(edit.fzCompanyPerson);
                    body.find("#fzCompanyMobile").val(edit.fzCompanyMobile);
                    body.find("#fzCompanyStatus").val(edit.fzCompanyStatus);
                    body.find("#fzCompanyLimitTime").val(edit.fzCompanyLimitTime);
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
        addCompany();
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('companyListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addCompany(data[0]);
        }else{
            layer.msg("请选择需要修改的记录");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('companyListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].fzCompanyCode);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/company/delBatch",{
                    idArr : idArr.toString()
                },function(data){
                    layer.close(index);
                    tableIns.reload();
                    if (data.data){
                        layer.msg("删除成功！");
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

})
