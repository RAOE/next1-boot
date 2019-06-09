//登陆请求
function loginSubmit() {
    console.log("login~@!");
    var param =
        {
            'username': $("#username").val(),
            'password': $("#password").val(),
            'verifyCode': $("#user_input_verifyCode").val()
        }
    $.ajax({
        url: "../adminUser/loginSubmit",
        method: "post",
        data: param,
        dataType: 'json',
        success: function (resp) {
            if (resp.status == 200) {
                console.log("登陆成功～！");
            } else if (resp.status == 500) {
                alert(resp.msg);
                changeCode();
                console.log(resp);
            }
        }
    })
}
//验证码刷新
