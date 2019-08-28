function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$(function () {
    $(".hook").click(function () {
        if ($(this).attr("my-icon") == "select02") {
            $(this).attr("my-icon", "select01");
            var sum = totalMoney();
            $("#js-pay-price").text(sum);
            $("#js-actual-price").text(sum);
        } else {
            $(this).attr("my-icon", "select02");
            var sum = totalMoney();
            $("#js-pay-price").text(sum);
            $("#js-actual-price").text(sum);
        }
    })


    $(".delete").click(function () {
        var orderId = Number($(this).attr("name"));
        $(this).closest(".historyOrder").remove();
        if ($(".historyOrder").size() == 0) {
            $(".historyTitle").text("暂无订单历史记录");
        }
        $.ajax({
            url: getRootPath() + "deleteHistoryOrder",
            type: "POST",
            data: {
                "orderId": orderId
            }
        });
    })

})

function totalMoney() {
    var sum = 0;
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            var oneCourse = 0;
            $(this).parent().siblings().find(".price").each(function () {
                oneCourse = Number(oneCourse + Number($(this).text()));
            })
            sum = sum + oneCourse;
        }
    })
    return sum;
}


function deleteOrder() {
    var num = 0;
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            num++;
        }
    })
    if (num == 0) {
        $("#replay").text("请选择取消的订单");
        $("#orderModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    } else {
        $("#replay").text("是否确认删除该订单");
        $(".modal-footer").append("<button type='button' class='btn btn-default modal_modify' id='buttonContent' ONCLICK='delOrderAJAX()'>确认</button>")
        $("#orderModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    }
}

function delOrderAJAX() {
    var orderIds = '';
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            orderIds = orderIds + $(this).attr("name") + ",";
            $(this).closest(".noPay").remove();
        }
    })
    $.ajax({
        url: getRootPath() + "deleteOrder",
        type: "POST",
        data: {
            "orderIds": orderIds.substring(0, orderIds.length - 1)
        }
    });
    $("#orderModal").modal('hide');
}