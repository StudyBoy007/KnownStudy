//得到项目根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

$(function () {
    $(".input-file").change(function () {
        var filePath = $(".input-file").val();
        var fileType = filePath.substring(filePath.lastIndexOf("."));
        if (fileType == ".jpg" || fileType == ".png") {
            $("#face").attr("src", URL.createObjectURL($(this)[0].files[0]));
        } else {
            var flag = confirm("上传图片格式不正确，请重新选择(.jpg或.png)");
            $(".input-file").val("");
        }
    });
})