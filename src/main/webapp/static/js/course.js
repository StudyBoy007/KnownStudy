$(function () {
    $(".gamelist span").mouseover(function () {
        $(this).addClass("label-select");

    }).mouseout(function () {
        $(this).removeClass("label-select")
    });


    $(".gamelist span").click(function () {
        $(this).addClass("label-active");
        $(this).parent().siblings().find("span").removeClass("label-active");
        console.log($(".gamelist span").each(function () {
            if ($(this).hasClass("label-active")) {
                console.log($(this).text());
            }
        }))
    })

    // $(".collectAll").mouseover(function () {
    //     $(this).find(".collect").eq(0).attr("myicon", "collect4");
    // }).mouseleave(function () {
    //     $(this).find(".collect").eq(0).attr("myicon", "collect3");
    // })

    $(".collectAll").click(function () {
        if ($(this).find(".collect").eq(0).attr("my-icon") == "collect2") {
            $(this).find(".collect").eq(0).attr("my-icon", "collect1");
        } else {
            $(this).find(".collect").eq(0).attr("my-icon", "collect2");
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


})
