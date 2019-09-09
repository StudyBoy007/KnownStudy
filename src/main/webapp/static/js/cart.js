function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$(function () {
    $(".js-coures-num").text($(".hook").length);
    $(".js-choice-num").text($(".hook").length);
    var total = AllMoney();
    $(".jsAltogether").text(total);

    $(".hook").click(function () {
        if ($(this).attr("my-icon") == "select02") {
            $(this).attr("my-icon", "select01");
            var sum = AllMoney();
            $(".jsAltogether").text(sum);
            juifyAllSelect()
        } else {
            $(this).attr("my-icon", "select02");
            var sum = AllMoney();
            $(".jsAltogether").text(sum);
            juifyAllSelect()
        }
    })

    $(".AllSelect").click(function () {
        if ($(this).attr("my-icon") == "select02") {
            $(this).attr("my-icon", "select01");
            $(".hook").attr("my-icon", "select01");
            var sum = AllMoney();
            $(".jsAltogether").text(sum);
        } else {
            $(this).attr("my-icon", "select02");
            $(".hook").attr("my-icon", "select02");
            var sum = AllMoney();
            $(".jsAltogether").text(sum);
        }
    })

    $(".delete").click(function () {
        $(this).closest(".item").remove();
        var sum = AllMoney();
        $(".jsAltogether").text(sum);
        juifyAllSelect()

        var str = getRootPath();
        var cartId = $(this).attr("name");

        //去删除对呀的cart记录
        $.ajax({
            url: str + "delCart",
            type: "POST",
            data: {"cartId": cartId},
        });


    })

})

function AllMoney() {
    var courses = $(".hook").length;
    $(".js-coures-num").text(courses);
    var sum = 0;
    var flag1 = 0;
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            var onePrice = $(this).parent().siblings(".item-3").find("span").text();
            sum = sum + Number(onePrice);
            flag1 = flag1 + 1;
        }
    })
    $(".js-choice-num").text(flag1);
    return sum;
}


function juifyAllSelect() {
    var flag = true;
    if ($(".hook").length != 0) {
        $(".hook").each(function () {
            if ($(this).attr("my-icon") == "select02") {
                flag = false;
            }
        })
        if (flag == true) {
            $(".AllSelect").attr("my-icon", "select01");
        } else {
            $(".AllSelect").attr("my-icon", "select02");
        }
    } else {
        $(".AllSelect").attr("my-icon", "select02");
    }

}

function createOrder() {
    //课程id拼接字符串
    var str = '';

    //购物车序号拼接字符串
    var cartStr = '';
    var num = 0;
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            str = str + $(this).attr("id") + ",";
            cartStr = cartStr + $(this).attr("name") + ",";
            num++;
        }
    })
    if (num == 0) {
        $("#replay").text("请至少需要选择一件商品");
        $("#cartModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    } else {
        var totalMoney = Number($(".jsAltogether").text());
        $.ajax({
            url: getRootPath() + "createOrder",
            type: "POST",
            data: {
                "totalMoney": totalMoney,
                "strid": str.substring(0, str.length - 1),
                "cartIds": cartStr.substring(0, cartStr.length - 1)
            },
            success: function (result) {
                window.location.href = getRootPath() + "displayOrder?id=" + result.o;
            }
        });
    }
}

