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


})
