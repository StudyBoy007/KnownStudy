function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$("#focus").mouseover(function () {
    if ($(this).text().trim() == "已关注") {
        $(this).text("取消关注")
    }
}).mouseleave(function () {
    if ($(this).text().trim() == "取消关注") {
        $(this).text("已关注")
    }
})


$("#focus").click(function () {
    var teacherId = Number($(this).attr("name"));
    console.log($(this).text());
    if ($(this).text().trim() == "关注Ta") {
        $.ajax({
            url: getRootPath() + "doFocusTeacher",
            type: "POST",
            data: {
                "teacherId": teacherId
            },
            success: function (result) {
                $("#replay").text(result.msg);
                $("#js-tea-fan-num").text(result.o.focus);
                $("#teacherModal").modal({
                    //点击背景不关闭
                    backdrop: "static",
                    //触发键盘esc事件时不关闭
                    keyboard: false
                });
            }
        });
        $(this).text("已关注")
    } else {
        $.ajax({
            url: getRootPath() + "deleteFocusTeacher",
            type: "POST",
            data: {
                "teacherId": teacherId
            },
            success: function (result) {
                $("#replay").text(result.msg);
                $("#js-tea-fan-num").text(result.o.focus);
                $("#teacherModal").modal({
                    //点击背景不关闭
                    backdrop: "static",
                    //触发键盘esc事件时不关闭
                    keyboard: false
                });
            }
        });
        $(this).text("关注Ta");
    }
})

$(function () {
    $("#goWar").click(function () {
        $("html,body").animate({scrollTop: $("#Shizhan").offset().top}, 1000);
    })
    $("#goCourse").click(function () {
        $("html,body").animate({scrollTop: $("#Course").offset().top}, 1000);
    })
})


