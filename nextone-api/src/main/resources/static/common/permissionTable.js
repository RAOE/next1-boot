var globalCount = 0;
$("#fakeloader").fakeLoader({
    timeToHide: 10000, // Time in milliseconds for fakeLoader disappear
    zIndex: 999, // Default zIndex
    spinner: "spinner2", // Options: 'spinner1', 'spinner2', 'spinner3',
    bgColor: "#fff", // Hex, RGB or RGBA colors
});
setTimeout(function () {
    $('body').css('opacity', '1');
    $('body').attr("class", "gray-bg") // 添加样式
}, 100);
var returnAllCount = function () {
    if (globalCount == 1) {
        setTimeout(function () {
            $('#fakeloader').css('display', 'none');
        }, 500);
    }
}
//点击按钮调用搜索
function queryAll() {
    $('#allBlog').bootstrapTable('refresh');

}
// 初始化表格数据
var selectAll = function () {
    $('#allBlog').bootstrapTable({
        method: 'post',
        url: "../permission/queryAll",
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
        showFullscreen: true, //全屏按钮
        // queryParamsType: "limit", //查询参数组织方式
        sidePagination: "server", // 服务端处理分页
        silent: true, // 刷新事件必须设置
        searchTimeOut: 500, // 设置搜索超时时间
        toolbarAlign: 'left', // 工具栏对齐方式
        buttonsAlign: 'right', // 按钮对齐方式
        toolbar: '#toolbar', // 指定工作栏
        searchAlign: 'right',
        clickToSelect: true,
        // singleSelect : true,
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
                field: 'orgId',
                valign: 'middle',
                width: '6%',
                formatter: function (value, row, index) {
                    var index1 = index + 1;
                    var id = '<span title="ID:' + index1 + '">' + index1 + '</span>';
                    return id;
                }
            },
            {
                title: '权限路径',
                field: 'path',
                align: 'center',
                width: '6%',
                formatter: function (value, row, index) {
                    console.log(row.path);
                    var path = '<span >' + row.path + '</span>';
                    return path;
                }
            },
            {
                title: '权限描述',
                field: 'description',
                align: 'center',
                width: '6%',
                formatter: function (value, row, index) {
                    console.log(row.description);
                    var desc = '<span>' + row.description + '</span>';
                    return desc;
                }
            },
            {
                title: '状态',
                field: 'orgStatus',
                align: 'center',
                width: '5%',
                formatter: function (value, row, index) {
                    var index1 = index + 1;
                    var id = '<span title="ID:' + row.orgId + '">' + "启用" + '</span>';
                    return id;
                }
            }, {
                title: '操作',
                field: 'status',
                align: 'center',
                width: '15%',
                formatter: function (value, row, index) {
                     var a = '<button class="  btn btn-info btn-xs"   onclick="editById(\'' + row.orgId + '\')""><i class="fa fa-edit" ></i>编辑</button> ';
                     var b = '<a  class="  btn btn-danger btn-xs"  onclick="deleteOrg(\'' + row.orgId + '\')"><i class="fa fa-remove" ></i>删除</a> ';
                    return a+b;
                }
            },


        ]
    });
    globalCount++;
    returnAllCount();
};

// 传参数到后台
function queryParams(params) {
    return {
        pageSize: params.limit,
        page: (params.offset) / params.limit + 1,
        keyword: $(".form-control").val(),
        status: 1 // 1 表示正文
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
// 获取行号
var getSelectRows = function (status) {
    var date = $("#allBlog").bootstrapTable('getSelections');
    var idArray = new Array();
    var title = '';
    var text = '';
    if (status == 2) {
        title = '确定要删除这' + date.length + '条信息吗';
        text = '删除后前台将无法显示，请谨慎操作！';
    }
    swal({
        title: title,
        text: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false,
        cancelButtonText: "取消"
    }, function () {
        for (var i = 0; i < date.length; i++) {
            idArray[i] = date[i].orgId;
            operation(idArray[i], 2, null, null); // 参数2表示 删除
        }
    });
};
