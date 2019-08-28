$(function () {
    $(".collect").mouseover(function () {
        $(this).find(".collectNo").hide().siblings(".collectOk").show();
        $(this).find("span").css("color", "black");
    }).mouseleave(function () {
        $(this).find(".collectOk").hide().siblings(".collectNo").show();
        $(this).find("span").css("color", "#857d86");
    })


    // $(".Xcontent34>img").click(function () {
    //     // $("#carModall").modal({
    //     //     backdrop: "static"
    //     // });
    //     alert(123);
    // })
    function getRootPath() {
        var pathName = window.location.pathname.substring(1);
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
    }


    $(".collectCar").click(function () {

        var str = getRootPath();

        //获取商品id
        var courseId = Number($(this).attr("name"));

        //把加入购物车的物品添加
        $.ajax({
            url: str + "addCart",
            type: "POST",
            data: {"courseId": courseId},
            success: function (result) {
                $("#replay").text(result.msg);
                if (result.code == 200) {
                    $("#buttonContent").remove();
                } else if (result.code == 101) {
                    $("#buttonContent").text("前往观看")
                    $("#buttonContent").click(function () {
                        window.location.href = getRootPath() + "displayCourse?id=" + courseId;
                    })
                } else if (result.code == 100 || result.code == 102) {
                    $("#buttonContent").click(function () {
                        window.location.href = getRootPath() + "displayCart"
                    })
                } else if (result.code == 103) {
                    $("#buttonContent").text("查看订单")
                    $("#buttonContent").click(function () {
                        window.location.href = getRootPath() + "displayOrder";
                    })
                }
                $("#carModal").modal({
                    backdrop: "static"
                });
            }
        });
    })
})

