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