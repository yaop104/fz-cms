layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dictionaryList',
        url : '/dictionary/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dictionaryListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'dicId', title: '编码', width:200, align:"center"},
            {field: 'dicName', title: '名字', width:200, align:'center'},
            {field: 'dicType', title: '类型', width:80, align:'center',templet:function(d){
                if (d.dicType === 1) {
                    return '<span class="layui-badge layui-bg-green">大类</span>';
                } else if (d.dicType === 2) {
                    return '<span class="layui-badge layui-bg-green">小类</span>';
                } else {
                    return '<span class="layui-badge layui-bg-green"></span>';
                }
            }}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dictionaryListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                dicName: $(".dicName").val(),
                dicType: $(".dicType").val()
            }
        })
    });

    //添加
    function addDictionary(edit){
        var h = "280px";
        if (edit){
            h = "280px";
        }
        layui.layer.open({
            title : "添加",
            type : 2,
            area : ["420px",h],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#dicType").val(edit.dicType);
                    body.find("#dicId").val(edit.dicId);
                    body.find("#dicName").val(edit.dicName);
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
        addDictionary();
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('dictionaryListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addDictionary(data[0]);
        }else{
            layer.msg("请选择需要修改的记录");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('dictionaryListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].dicId);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/dictionary/delBatch",{
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
