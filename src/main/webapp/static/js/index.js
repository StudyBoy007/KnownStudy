$(function () {
    var loginAndReg = $(".loginAndReg");
    loginAndReg.append($("<button></button>").addClass("btn btn-success marginTop btn-lg").attr("data-toggle", "modal").attr("data-target", "#login").append("注册/登录"));

    $("html").animate({scrollTop: $("#about").offset().top}, 1000);

    $("#form-submit-info").click(function () {
        window.location.href = "info.html";
    })


    $("#AllCourses").click(function () {
        window.location.href = "course.html";
    })

})


function registerUser() {

}