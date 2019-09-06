function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}


$(function () {
    $("#send").on('click', function () {
        $("html").animate({scrollTop: $("#commentArea").offset().top}, 1000);
        document.getElementById("videoDis").pause();
        $(".overlay").show();
        $("#commendModal").modal({
            backdrop: "static"
        });
    })
})