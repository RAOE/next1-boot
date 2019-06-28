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

// 初始化表格数据
var selectAll = function () {
    $('#allBlog').bootstrapTable({
        method: 'post',
        url: "../role/queryAll.do",
        dataType: "json",
        striped: false, // 使表格带有条纹
        pagination: true, // 在表格底部显示分页工具栏
        pageSize: 10,
        pageNumber: 1,
        sortStable: true,
        sortable: true,
        pageList: [10, 20, 50],
        idField: "id", // 标识哪个字段为id主键
        showToggle: true, // 名片格式
        cardView: true, // 设置为True时显示名片（card）布局
        showColumns: true, // 显示隐藏列
        showRefresh: false, // 显示刷新按钮
        // singleSelect: true,//复选框只能选择一条记录
        search: false, // 是否显示搜索框
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
                title: '序号',
                align: 'center',
                field: 'orderId',
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
                field: 'img',
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
                field: 'name',
                align: 'center',
                width: '4%',
                formatter: function (value, row, index) {
                    var name = '<span>' + row.description + '</span>';
                    return name;
                }
            }
            , {
                title: '是否启用',
                field: 'price',
                align: 'center',
                width: '4%',
                formatter: function (value, row, index) {
                    var status = row.isDeleted;
                    if (status == 0) {
                         status='<span class="badge badge-primary" >' + '启用' + '</span>';
                    } else if (status == 1) {
                        status='<span class="badge badge-danger" >' + '禁用' + '</span>';
                    }
                    return status;
                }
            },
            {
                title: '操作',
                field: 'status',
                align: 'center',
                width: '10%',
                formatter: function (value, row, index) {
                    var a = '<button class="  btn btn-info  btn-xs"   onclick="editById(\'' + row.id + '\')"><i class="fa fa-edit" ></i>编辑权限</button> ';
                    // var b = '<a  class="  btn btn-danger  btn-xs"  onclick="deleteOrg(\'' + row.id + '\')"><i class="fa fa-warning" ></i>禁用</a> ';
                    // var c= '<a  class="  btn btn-danger btn-xs"  onclick="deleteOrg(\'' + row.id + '\')"><i class="fa fa-remove" ></i>删除</a> ';
                    return a ;
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

// 格式化时间工具类
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1, // 月份
        "d+": datetime.getDate(), // 日
        "h+": datetime.getHours(), // 小时
        "m+": datetime.getMinutes(), // 分
        "s+": datetime.getSeconds(), // 秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
        "S": datetime.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

