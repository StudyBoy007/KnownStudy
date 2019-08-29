function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$(function () {
    $(".input").focus(function () {
        $(".font").css("color", "#25E198");
    }).blur(function () {
        $(".font").css("color", "black");
    })

    $("img").click(function () {
        $(this).hide().siblings().show();
    })


    $(".img1").click(function () {
        if ($(".img4").css("display") == "block") {
            $(".img4").hide();
            $(".img3").show();
        }
    })

    $(".img3").click(function () {
        if ($(".img2").css("display") == "block") {
            $(".img2").hide();
            $(".img1").show();
        }
    })

    $("li").click(function () {
        if ($(this).css("border") == "1px solid rgb(165, 173, 175)") {
            $(this).css("border", "1px solid #25E198").css("background", "url(/known/static/images/money/true.png) no-repeat 96px 24px").css("color", "#25E198");
            var i = $(this).find(".content").text();
            $(".charge").text(i);
        } else {
            $(this).css("background", "#f6f6f6").css("border", "1px solid #a5adaf").css("color", "#cbcbcb");
        }
        $(this).siblings().css("background", "#f6f6f6").css("border", "1px solid #a5adaf").css("color", "#cbcbcb");
    })
})

function update() {
    var i = $(".input").val();
    $(".charge").text(i);
    var reg = /^[1-9]\d*$/;
    if (!reg.test($(".input").val())) {
        $(".input").val(null);
        $(".charge").text(0);
    }
}


function addAccount() {
    var accountNumber = Number($(".charge").text());
    if (accountNumber == 0) {
        $("#replay").text("充值金额不能为0");
        $("#rechargeModal").modal({
            //点击背景不关闭
            backdrop: "static",
            //触发键盘esc事件时不关闭
            keyboard: false
        });
    } else {
        $.ajax({
            url: getRootPath() + "recharge",
            type: "POST",
            data: {
                "accountNumber": accountNumber,
            },
            success: function (result) {
                console.log(result);
                $("#replay").text(result.msg);
                $(".col2").text(result.o.account)
                $("#rechargeModal").modal({
                    //点击背景不关闭
                    backdrop: "static",
                    //触发键盘esc事件时不关闭
                    keyboard: false
                });
            }
        });
    }
}