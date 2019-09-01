$(function () {
    var loginAndReg = $(".loginAndReg");
    // loginAndReg.append($("<button></button>").addClass("btn btn-success marginTop btn-lg").attr("data-toggle", "modal").attr("data-target", "#login").append("注册/登录"));

    // $("html").animate({scrollTop: $("#about").offset().top}, 1000);

    loginAndReg.click(function () {
        reset_form("#login #user_form");
        reset_form("#login #register_user_form");
    })


    $("#AllCourses").click(function () {
        window.location.href = "course.html";
    })


    $(".team-info>h3").mouseover(function () {
        $(this).css("color", "#29CA8E");
    }).mouseout(function () {
        $(this).css("color", "black");
    })
})


//刷新验证码
//原生js
function reloadCode() {
    var date = new Date().getTime();
    var str = getRootPath();
    console.log(str);
    document.getElementById("safeCode").src = str + "/getJuifyCode?date=" + date;
}

//jquery
$(function () {
    $("#safeCode").click(function () {
        var date = new Date().getTime();
        // var _ctx = [[${application.ctx}]];
        var str = getRootPath();
        console.log(str);
        $(this).attr("src", str + "/getJuifyCode?date=" + date);
    })
})

//得到项目根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

function registerUser() {
    //获取项目根路径
    var str = getRootPath();
    alert(123);
    console.log($("#tab form").serialize());
    //发送ajax请求到后台去进行校验
    $.ajax({
        url: str + "regUser",
        type: "POST",
        data: $("#tab form").serialize(),
        success: function (result) {
            console.log("注册成功");
            if (result.code == 100) {
                $("#login").modal('hide');
                $("#reg_success_info").text(result.msg);
                $("#regSuccessInfo").modal({
                    backdrop: "static"
                });
            } else {
                $("#login").modal('hide');
                $("#reg_info").text(result.msg);
                $("#regInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}


function Userlogin() {
    var str = getRootPath();
    // var args = $("#room form").serialize();
    // window.location.href = str + "/juifyUser?" + args;

    //发送ajax请求到后台去进行校验
    $.ajax({
        url: str + "juifyUser",
        type: "POST",
        data: $("#room form").serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                var user = result.o;
                addUser(user);
                $("#login").modal('hide');
                // window.location.reload();
            } else {
                //显示失败信信息
                $("#login").modal('hide');
                var error = result.msg;
                // $("#login_info").val(error);
                $("#login_info").text(error);
                $("#logInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}

function addUser(obj) {
    $("#loginUser").remove();
    $(".information").append(
        "                <li id='cover'><a href='/known/userInfo'><img\n" +
        "                        src='/pic/avatar/" + obj.avatar + "'\n" +
        "                        class='img-circle avatar'\n" +
        "                        width='38px' height='38px'></a>\n" +
        "                </li>"
    )
    $("#fullName1").val(obj.username);
    $("#user_email1").val(obj.email);
    $("#avatar1").attr("src", "/pic/avatar/" + obj.avatar);
    $("#info").attr("href", "/known/userInfo");
}


//是否重新登陆
function oneMore() {
    $("#logInfo").modal('hide');

    reset_form("#login #user_form");
    reset_form("#login #register_user_form");
    $("#login").modal({
        backdrop: "static"
    });
}

//去登录
function gologin() {
    $("#regSuccessInfo").modal('hide');

    //重置模态框
    reset_form("#login #user_form");
    reset_form("#login #register_user_form");

    $("#login").modal({
        backdrop: "static"
    });
    $("#log").click();
}


//是否重新注册
function oneMoreReg() {
    $("#regInfo").modal('hide');

    reset_form("#login #user_form");
    reset_form("#login #register_user_form");

    $("#login").modal({
        backdrop: "static"
    });

    $("#reg").click();
}


//清空模态框表单
function reset_form(ele) {
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}


//点击跳转到个人信息
//头像

//按钮