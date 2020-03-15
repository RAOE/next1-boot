//登陆请求
function login() {
    //loading层
    var index = layer.load(2, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    var name = $("#username").val();
    var password = $("#password").val();
    var salt = new Date().getTime();
    var verifyCode = $("#verifyCode").val();
    $.ajax({
        url: ctx + "/web/me/loginSubmit",
        method: "post",
        timeout: 2000, //通过timeout属性，设置超时时间
        data: {username: name, password: password, salt: salt, verifyCode: verifyCode},
        success: function (resp) {
            layer.close(index);
            if (resp.status == 200) {
                window.location.href = ctx ;
            } else {
                changeCode();
                layer.msg(resp.msg);
            }
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.close(index);
                layer.msg('请求超时');
            }
        }
    })
}

//提交表单登陆
$("#submit").click(function () {
    login();
})
//点击回车 登陆
$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        login();
    }
});

$("#logout").click(function (e) {
    window.location.href = ctx + "/web/login"
})


function changeCode() {
    //点击刷新验证码
    var img = document.getElementsByTagName("img")[0];
    img.src = ctx + "/web/me/imageCode?" + new Date().getTime();
}
