function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$(function () {

    $(".levels li").mouseover(function () {
        if ($(this).hasClass("active") == false) {
            $(this).addClass("activeFont");
        }
    }).mouseleave(function () {
        $(this).removeClass("activeFont");
    })


    // $(".levels li").click(function () {
    //     $(this).addClass("active").siblings().removeClass("active");
    // })


    $(".courseClass li").click(function () {
        var direction_id = $(this).attr("name");
        window.location.href = getRootPath() + "meant?id=" + direction_id;
    })

    $(".teacherClass li").click(function () {
        var tid = $(this).attr("name");
        var direction_id;
        var condition;
        $(".courseClass li").each(function () {
            if ($(this).hasClass("active")) {
                direction_id = $(this).attr("name");
            }
        })

        $(".condition li").each(function () {
            if ($(this).hasClass("active")) {
                condition = $(this).attr("name");
            }
        })
        window.location.href = getRootPath() + "conditionCourse?tid=" + tid + "&direction_id=" + direction_id + "&condition=" + condition;
    })

    $(".condition li").click(function () {
        var condition = $(this).attr("name");
        var direction_id;
        var tid;
        $(".teacherClass li").each(function () {
            if ($(this).hasClass("active")) {
                tid = $(this).attr("name");
            }
        })

        $(".courseClass li").each(function () {
            if ($(this).hasClass("active")) {
                direction_id = $(this).attr("name");
            }
        })
        window.location.href = getRootPath() + "conditionCourse?tid=" + tid + "&direction_id=" + direction_id + "&condition=" + condition;
    })


    $(function () {

        $(".collectAll").mouseover(function () {
            if ($(".collectAll").find(".collect").eq(1).text() == "已收藏") {
                $(".collectAll").find(".collect").eq(1).text("取消收藏")
            }
        }).mouseleave(function () {
            if ($(".collectAll").find(".collect").eq(1).text() == "取消收藏") {
                $(".collectAll").find(".collect").eq(1).text("已收藏")
            }

        })

    })

    $(".collectAll").click(function () {
        var courseId = Number($(this).attr("name"));
        if ($(this).find(".collect").eq(0).attr("my-icon") == "collect2") {
            $(this).find(".collect").eq(0).attr("my-icon", "collect1");
            $(this).find(".collect").eq(1).text("收藏");
            $.ajax({
                url: getRootPath() + "deleteCollect",
                type: "POST",
                data: {
                    "courseId": courseId
                }
            });
        } else {
            $(this).find(".collect").eq(0).attr("my-icon", "collect2");
            $(this).find(".collect").eq(1).text("已收藏");
            $.ajax({
                url: getRootPath() + "doCollect",
                type: "POST",
                data: {
                    "courseId": courseId
                }
            });
        }
    })


})


function registerUser() {
    //获取项目根路径
    var str = getRootPath();

    console.log($("#tab form").serialize());
    //发送ajax请求到后台去进行校验
    $.ajax({
        url: str + "regUser",
        type: "POST",
        data: $("#tab form").serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                $("#login").modal('hide');
                $("#reg_success_info").text(result.msg);
                $("#regSuccessInfo").modal({
                    backdrop: "static"
                });
            } else {
                $("#login").modal('hide');
                $("#reg_info").text(result.msg);
                $("#regInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}




function Userlogin() {
    var str = getRootPath();

    //发送ajax请求到后台去进行校验
    $.ajax({
        url: str + "juifyUser",
        type: "POST",
        data: $("#room form").serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                var user = result.o;
                addUser(user);
                $("#login").modal('hide');
                // window.location.reload();
            } else {
                //显示失败信信息
                $("#login").modal('hide');
                var error = result.msg;
                // $("#login_info").val(error);
                $("#login_info").text(error);
                $("#logInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}

