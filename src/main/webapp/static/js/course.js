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


    $(".collectAll").click(function () {
        var courseId = Number($(this).attr("name"));
        if ($(this).find(".collect").eq(0).attr("my-icon") == "collect2") {
            $(this).find(".collect").eq(0).attr("my-icon", "collect1");
            $(this).find(".collect").eq(1).text("未收藏");
            $.ajax({
                url: getRootPath() + "deleteCollect",
                type: "POST",
                data: {
                    "courseId": courseId
                }
            });
        } else {
            $(this).find(".collect").eq(0).attr("my-icon", "collect2");
            $(this).find(".collect").eq(1).text("收藏");
            $.ajax({
                url: getRootPath() + "doCollect",
                type: "POST",
                data: {
                    "courseId": courseId
                }
            });
        }
    })

    // $(".hook").click(function () {
    //     if ($(this).attr("my-icon") == "select02") {
    //         $(this).attr("my-icon", "select01");
    //         var sum = AllMoney();
    //         $(".jsAltogether").text(sum);
    //         juifyAllSelect()
    //     } else {
    //         $(this).attr("my-icon", "select02");
    //         var sum = AllMoney();
    //         $(".jsAltogether").text(sum);
    //         juifyAllSelect()
    //     }
    // })


    //动态添加课程
})

