// (function () {
//     alert("exec..")
// })();
$(function () {
    $("#send").on('click', function () {
        $("html").animate({scrollTop: $("#header").offset().top}, 1000);
        $("#commendModal").modal({
            backdrop: "static"
        });
        // alert(123);
    })
})