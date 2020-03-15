//init ...
$(document).ready(function () {
    //转圈加载 .... 加载完成后才能执行其他操作
    $.ajax({
        url: ctx + "/web/marketRadar/getAllCity",
        type: "post",
        success: function (resp) {
            var data = resp.data;
            for (var i = 0; i < data.length; i++) {
                $("#city").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
            }
            $.ajax({
                url: ctx + "/web/marketRadar/getAllProvinceByCityId/" + data[0].areaId,
                type: "post",
                success: function (resp) {
                    var data = resp.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#province").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
                    }
                    $.ajax({
                        url: ctx + "/web/marketRadar/getAllRegionByProvinceId/" + data[0].areaId,
                        type: "post",
                        success: function (resp) {
                            var data = resp.data;
                            for (var i = 0; i < data.length; i++) {
                                $("#region").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
                            }
                            console.log(data);
                        }
                    })
                }
            })
            console.log(data);
        }
    })
    $.ajax({
        url: ctx + "/web/marketRadar/getAllCategory",
        type: "post",
        success: function (resp) {
            var data = resp.data;
            for (var i = 0; i < data.length; i++) {
                $("#category").append("<option value=" + data[i].id + ">" + data[i].name + "</option>")
            }
        }
    })
})

//所在城市发生了改变
function changeCity() {
    $.ajax({
        url: ctx + "/web/marketRadar/getAllProvinceByCityId/" + $("#city").val(),
        type: "post",
        success: function (resp) {
            var data = resp.data;
            $("#province").empty();
            for (var i = 0; i < data.length; i++) {
                $("#province").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
            }
            $.ajax({
                url: ctx + "/web/marketRadar/getAllRegionByProvinceId/" + data[0].areaId,
                type: "post",
                success: function (resp) {
                    var data = resp.data;
                    $("#region").empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#region").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
                    }
                    console.log(data);
                }
            })
        }
    })
}

//所在省份发生了改变
function changeProvince() {
    $.ajax({
        url: ctx + "/web/marketRadar/getAllRegionByProvinceId/" + $("#province").val(),
        type: "post",
        success: function (resp) {
            var data = resp.data;
            $("#region").empty();
            for (var i = 0; i < data.length; i++) {
                $("#region").append("<option value=" + data[i].areaId + ">" + data[i].areaName + "</option>")
            }
            console.log(data);
        }
    })
}
