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


    // $(".delete").click(function () {
    //
    // })

    $("#history").on("click", '.delete', function () {
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
        $(".modal-footer").append("<button type='button' class='btn btn-default modal_modify' id='buttonContent1' ONCLICK='delOrderAJAX()'>确认</button>")
        $("#orderModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    }
}

function pay() {
    var num = 0;
    $(".hook").each(function () {
        if ($(this).attr("my-icon") == "select01") {
            num++;
        }
    })
    if (num == 0) {
        $("#replay").text("请选择付款的订单");
        $("#orderModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    } else {
        var orderIds = '';
        $(".hook").each(function () {
            if ($(this).attr("my-icon") == "select01") {
                orderIds = orderIds + $(this).attr("name") + ",";
            }
        })
        $.ajax({
            url: getRootPath() + "payOrder",
            type: "POST",
            data: {
                "orderIds": orderIds.substring(0, orderIds.length - 1),
                "totalMoney": Number($("#js-pay-price").text())
            },
            success: function (result) {
                console.log(result);
                $("#replay").text(result.msg);
                if (result.code == 405) {
                    $(".modal-footer").append("<button type='button' class='btn btn-default modal_modify' id='buttonContent' ONCLICK='goRecharge()'>前往充值</button>")
                }
                $("#orderModal").modal({
                    //点击背景不关闭
                    backdrop: "static",
                    //触发键盘esc事件时不关闭
                    keyboard: false
                });

                if (result.code == 100) {
                    $("#js-actual-price").text(0);
                    $("#js-pay-price").text(0);
                    $(".hook").each(function () {
                        if ($(this).attr("my-icon") == "select01") {
                            $(this).closest(".noPay").remove();
                        }
                        $("#history").empty();
                        $(".historyTitle").text("订单历史记录");
                        var orders = result.extend.orders;
                        $.each(orders, function (index, order) {
                            $("#history").append("            <div class='detail-box historyOrder' >\n" +
                                "                <ul>\n" +
                                "                    <li>\n" +
                                "                        <span class='delete' my-icon='del2' name='" + order.id + "'></span></li>\n" +
                                "                </ul>\n" +
                                "            </div>")
                        })
                        $.each(orders, function (index, order) {
                            var courses = order.courses;
                            $.each(courses, function (index1, course) {
                                $("#history .historyOrder").eq(index).find("ul").append(
                                    "                    <li class='clearfix js-item-cart js-each-217' data-type='1'\n" +
                                    "                        data-typeid='217'\n" +
                                    "                        data-goodsid='879'>\n" +
                                    "                        <a href='' target='_blank'><img\n" +
                                    "                                src='/pic/course/" + course.courseDirection.courseDirection + "/" + course.pic + "' alt=''\n" +
                                    "                                class='l'></a>\n" +
                                    "                        <div class='text-info-box l'>\n" +
                                    "                            <p class='package-title'></p>\n" +
                                    "                            <a href='' target='_blank'><p class='package-info-title'>\n" +
                                    "                                " + course.cname + "</p></a>\n" +
                                    "                        </div>\n" +
                                    "                        <div class='info-price l'>\n" +
                                    "                            <em>$</em><span class='price'>" + course.price + "</span>\n" +
                                    "                        </div>\n" +
                                    "                    </li>\n"
                                )
                            })
                        })
                    })
                }
            }
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

function goRecharge() {
    window.location.href = getRootPath() + "userAccountCharge";
}

function deleteButton() {
    $("#buttonContent").remove();
    $("#buttonContent1").remove();
}