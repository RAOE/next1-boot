//author ：徐塬峰
//首页配置
//刷新iframe页面
$("#refresh").click(function () {
    refreshTab();
})
// 全屏显示
$('#fullScreen').on('click', function () {
    $('#wrapper').fullScreen();
});
//搜索
$("#search").click(function () {
    $('#allBlog').bootstrapTable('refresh');
})
//重置
$("#reset").click(function () {
    //拿到所有的input表单
    var inputs = $("input");
    inputs.each(function () {
        $(this).val('');
    });
    $('#allBlog').bootstrapTable('refresh');
})
//刷新iframe
function refreshTab() {
    var currentId = $('.page-tabs-content').find('.active').attr('data-id');
    var target = $('.J_iframe[data-id="' + currentId + '"]');
  //  console.log(target);
    var url = target.attr('src');
    target.attr('src', url).ready();
}

