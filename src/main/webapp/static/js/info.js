//得到项目根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

function modify() {
    console.log($("#edit_user_Email").serialize());
    var str = getRootPath();
    //发送ajax请求到后台去进行校验
    $.ajax({
        url: str + "/updateUserInfo",
        type: "POST",
        data: $("#edit_user_Email").serialize(),
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                $("#ModifyModal").modal('hide');
                $("#modify_info_success").text(result.msg);
                $("#modifyInfo_success").modal({
                    backdrop: "static"
                });
                var user = result.o;
                $(".username").text(user.username);
                $("#motto").text(user.motto);
                $("#age").text(user.age);
                $("#email").text(user.email);
                $("#phone").text(user.phone);
                $("#address").text(user.address);
                $("#account").text(user.account);
            } else {
                $("#ModifyModal").modal('hide');
                $("#modify_info").text(result.msg);
                $("#modifyInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}


//是否重新修改
function oneMore() {
    $("#modifyInfo").modal('hide');

    $("#ModifyModal").modal({
        backdrop: "static"
    });
}


//确认上传更新头像
function avatar() {
    var str = getRootPath();
    //获取表单数据
    var formData = new FormData($('#avatarUpload')[0]);

    //发送ajax请求到后台去进行校验
    $.ajax({
        type: "POST",
        url: str + "changAvatar",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                $("#avatarModal").modal('hide');
                $("#modify_info_success").text(result.msg);
                $("#modifyInfo_success").modal({
                    backdrop: "static"
                });
                var user = result.o;
                $("#avatarImg").attr("src", "/pic/avatar/" + user.avatar);
            } else {
                $("#avatarModal").modal('hide');
                $("#modify_info").text(result.msg);
                $("#modifyInfo").modal({
                    backdrop: "static"
                });
            }
        }
    });
}

function select() {
    return $('#avatar').click();
}

$(function () {
    $(".modify_button").click(function () {
        $("#ModifyModal").modal({
            backdrop: "static"
        });
    })


    $(".cc-profile-image").on('click', 'img', function () {
        select();
    });


    $("#avatar").change(function () {
        var filePath = $("#avatar").val();
        var fileType = filePath.substring(filePath.lastIndexOf("."));
        if (fileType == ".jpg" || fileType == ".png") {
            $(".img").attr("src", URL.createObjectURL($(this)[0].files[0]));
            $("#avatarModal").modal({
                backdrop: "static"
            });
        } else {
            var flag = confirm("上传图片格式不正确，请重新选择(.jpg或.png)");
        }
    });

})

function exitUser() {
    var str = getRootPath();
    window.location.href = str + "/exit";
}

