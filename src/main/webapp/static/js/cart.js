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

