$(function () {
    //发送AJAX请求
    $.get("/blogType/typeList",function (data) {
        $(data).each(function () {

        });
    },"json")
});




