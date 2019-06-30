var globalCount = 0;
$("#fakeloader").fakeLoader({
    timeToHide: 10000, // Time in milliseconds for fakeLoader disappear
    zIndex: 999, // Default zIndex
    spinner: "spinner2", // Options: 'spinner1', 'spinner2', 'spinner3',
    // 'spinner4', 'spinner5', 'spinner6', 'spinner7'
    bgColor: "#fff", // Hex, RGB or RGBA colors
});
setTimeout(function () {
    $('body').css('opacity', '1');
    $('body').attr("class", "gray-bg") // 添加样式
}, 100);
//点击按钮调用搜索
function queryAll() {
    var table = $('#allBlog').bootstrapTable('refresh');
}

var returnAllCount = function () {
    if (globalCount == 1) {
        setTimeout(function () {
            $('#fakeloader').css('display', 'none');
        }, 500);
    }
}
// 获取行号
var getSelectRows = function (status) {
    var date = $("#allBlog").bootstrapTable('getSelections');
    var idArray = new Array();
    var title = '';
    var text = '';
    if (status == 1) {
        title = '确定要删除这' + date.length + '条信息吗';
        text = '删除后前台将无法显示，请谨慎操作！';
    } else if (status == 2) {
        title = '确定要将这' + date.length + '条博客置顶吗';
        text = '置顶后,将显示在前台置顶栏目';
    }
    swal({
        title: title,
        text: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false
    }, function () {
        if (status == 1) {
            for (var i = 0; i < date.length; i++) {
                idArray[i] = date[i].id;
                operationVideo(idArray[i], 2, null, null); // 参数2表示 放入回收站
            }
        } else if (status == 2) {
            for (var i = 0; i < date.length; i++) {
                idArray[i] = date[i].id;
                operationVideo(idArray[i], null, null, 1); // 设置为置顶
            }
        }
    });
};
// 初始化表格数据
var selectAll = function () {
    $('#allBlog').bootstrapTable({
        method: 'post',
        url: "../systemLog/queryAll",
        dataType: "json",
        striped: false, // 使表格带有条纹
        pagination: true, // 在表格底部显示分页工具栏
        pageSize: 10,
        pageNumber: 1,
        sortStable: true,
        sortable: true,
        pageList: [10, 20, 50],
        idField: "id", // 标识哪个字段为id主键
        showToggle: false, // 名片格式
        cardView: false, // 设置为True时显示名片（card）布局
        showColumns: true, // 显示隐藏列
        showRefresh: true, // 显示刷新按钮
        // singleSelect: true,//复选框只能选择一条记录
        search: true, // 是否显示搜索框
        searchOnEnterKey: true, // 设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
        // clickToSelect: true,//点击行即可选中单选/复选框
        queryParams: queryParams, // 参数
        // showFullscreen:true, //全屏按钮
        // queryParamsType: "limit", //查询参数组织方式
        sidePagination: "server", // 服务端处理分页
        silent: true, // 刷新事件必须设置
        searchTimeOut: 500, // 设置搜索超时时间
        toolbarAlign: 'left', // 工具栏对齐方式
        buttonsAlign: 'right', // 按钮对齐方式
        toolbar: '#toolbar', // 指定工作栏
        searchAlign: 'right',
        // singleSelect : true,
        clickToSelect: true,
        contentType: "application/x-www-form-urlencoded",
        formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        formatNoMatches: function () { // 没有匹配的结果
            return "无符合条件的记录";
        },
        responseHandler: function (res) {
            return {
                "total": res.data.records, // 总页数
                "rows": res.data.rows // 数据
            };
        },
        columns: [
            {
                checkbox: true,
                width: '3%',
                align: 'center',
                valign: 'middle',
            },
            {
                title: '序号',
                align: 'center',
                field: 'id',
                valign: 'middle',
                width: '4%',
                formatter: function (value, row, index) {
                    var index1 = index + 1;
                    var id = '<span title="ID:' + index1 + '">' + row.id + '</span>';
                    return id;
                }
            },
            {
                title: '角色名称',
                field: 'name',
                align: 'center',
                width: '4%',
                formatter: function (value, row, index) {
                    var index1 = index + 1;
                    var id = '<span title="ID:' + index1 + '">' + row.name + '</span>';
                    return id;
                }
            },
            {
                title: '角色描述',
                field: 'description',
                align: 'center',
                width: '4%',
                formatter: function (value, row, index) {
                    var name = '<span>' + row.description + '</span>';
                    return name;
                }
            }
            , {
                title: '是否启用',
                field: 'isDeleted',
                align: 'center',
                width: '4%',
                formatter: function (value, row, index) {
                    var status='<span class="badge badge-primary" >' + '启用' + '</span>';
                    return status;
                }
            },
            {
                title: '操作',
                field: 'operate',
                align: 'center',
                width: '10%',
                formatter: function (value, row, index) {
                    var a = '<button class="  btn btn-info  btn-xs"   onclick="editById(\'' + row.id + '\')"><i class="fa fa-edit" ></i>编辑权限</button> ';
                    var b= '<a  class="  btn btn-danger btn-xs"  onclick="deleteById(\'' + row.id + '\')"><i class="fa fa-remove" ></i>删除</a> ';
                    return a+b;
                }
            },
        ]
    });
    globalCount++;
    returnAllCount();
};
var operation = function (id, op) {
    var title = "";
    var text = "";
    if (op == "还原") {
        title = '确定要移出回收站吗';
        text = '移出后,将显示在前台页面';
    } else if (op == "删除") {
        title = '确定要删除吗';
        text = '删除后，将不再显示在前台页面';
    }
    swal({
        title: title,
        text: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: false
    }, function () {
        if (op == "还原") {
            operationVideo(id, 1, null, null)
        } else if (op == "删除") {
            operationVideo(id, 2, null, null)
        }
    });
};

// 传参数到后台
function queryParams(params) {
    return {
        pageSize: params.limit,
        page: (params.offset) / params.limit + 1,
        keyword: $(".form-control").val()
    }
}


var formatTableUnit = function (value, row, index) {
    return {
        css: {
            "overflow": 'hidden',
            "text-overflow": 'ellipsis',
            "white-space": 'nowrap'
        }
    };
};



