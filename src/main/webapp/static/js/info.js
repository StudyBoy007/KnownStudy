function modify() {

}


//确认上传更新头像
function avatar() {
    $("form[name='avatarUpload']").submit();
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

