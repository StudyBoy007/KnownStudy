$(function () {
    $(".collect").mouseover(function () {
        $(this).find(".collectNo").hide().siblings(".collectOk").show();
        $(this).find("span").css("color", "black");
    }).mouseleave(function () {
        $(this).find(".collectOk").hide().siblings(".collectNo").show();
        $(this).find("span").css("color", "#857d86");
    })


    // $(".Xcontent34>img").click(function () {
    //     // $("#carModall").modal({
    //     //     backdrop: "static"
    //     // });
    //     alert(123);
    // })
    $(".collectCar").click(function () {
        $("#carModal").modal({
            backdrop: "static"
        });
    })
})