//得到项目根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}


var totalRecord, currentPage;
$(function () {
    to_page(1);


    $("#sendComment").click(function () {
        $.ajax({
            url: getRootPath() + "addComment",
            data: $("#new_comment_form").serialize(),
            type: "POST",
            success: function (result) {
                $("#commendModal").modal('hide');
                if (result.code == 100) {
                    to_page(1);
                } else {
                    alert(result.msg)
                }
            }
        });
    })

    $("#comment").on("click", ".replay", function () {
        var answerName = $(this).attr("data-title");
        var commentId = Number($(this).closest(".parentComment").attr("data-id"));
        var answerId = Number($(this).attr("data-id"));
        $("#myModalLabel11").text("回复: " + answerName);
        $("#myModalLabel11").attr("data-id", answerId).attr("name", commentId);
        $("html").animate({scrollTop: $("#" + commentId).offset().top}, 1000);
        document.getElementById("videoDis").pause();
        $(".overlay").show();
        $("#commendReplayModal").modal({
            backdrop: "static"
        });
    })

    $("#sendReplayComment").click(function () {
        var replayeded = $("#myModalLabel11").attr("data-id");
        var commentId = $("#myModalLabel11").attr("name");
        var content = $("#replay_commend").val();
        console.log("回复内容为：" + content);
        $.ajax({
            url: getRootPath() + "addReplayComment",
            data: {
                "commentId": commentId,
                "replay.id": replayeded,
                "content": content
            },
            type: "POST",
            success: function (result) {
                $("#commendReplayModal").modal('hide');
                if (result.code == 100) {
                    to_page(1);
                } else {
                    alert(result.msg)
                }
            }
        });
    })
})


function to_page(pn) {
    $.ajax({
        url: getRootPath() + "displayVideoComment",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            //1、解析并显示员工数据
            bulidComment(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、解析显示分页条数据
            build_page_nav(result);
        }
    });
}


function bulidComment(result) {
    $("#comment").empty();
    var comments = result.extend.pageInfo.list;
    $.each(comments, function (index, item) {
        $("#comment").append("         <div class='wenda-listcon mod-qa-list post-row clearfix parentComment' data-id='" + item.id + "' id='" + item.id + "'>\n" +
            "                                <div class='headslider qa-medias l'>\n" +
            "                                    <a href='/known/otherInfo?uid='" + item.user.id + " class='media' target='_blank' title='" + item.user.username + "'>\n" +
            "                                        <img src='/pic/avatar/" + item.user.avatar + "' width='40' height='40'></a>\n" +
            "                                </div>\n" +
            "                                <div class='wendaslider qa-content'>\n" +
            "                                    <div class='tit'>\n" +
            "                                        <div class='replayName' style='float: left;width: 90%'>\n" +
            "                                            <a href='/known/otherInfo?uid='" + item.user.id + " target='_blank'>" + item.user.username + "</a>\n" +
            "                                        </div>\n" +
            "                                        <div class='replayIcon' style='float: left;width: 10%'><span class='replay' my-icon='replay' data-id='" + item.user.id + "' data-title='" + item.user.username + "'\n" +
            "                                                                                                     style='float: right;position: relative;top: 4px'></span>\n" +
            "                                        </div>\n" +
            "                                        <div style='clear: both'></div>\n" +
            "                                    </div>\n" +
            "                                    <div class='cnt'>" + item.content + "</div>\n" +
            "                                    <div class='replymegfooter qa-footer clearfix'>\n" +
            "                                        <div class='l-box l'>\n" +
            "                                            <a title='赞' href='javascript:void(0);'\n" +
            "                                               class='js-pl-praise moco-btn moco-btn-gray-l' data-id='" + item.user.id + "'>\n" +
            "                                                <span my-icon='thumb01'></span><em>2</em></a>\n" +
            "                                        </div>\n" +
            "                                        <span class='r timeago'>" + item.timeInterval + "</span></div>\n" +
            "                                </div>\n" +
            "                            </div>")
    })


    //将各个评论的子评论给添加到评论中去
    $.each(comments, function (index, comment) {
        var replays = comment.replays;
        $.each(replays, function (index2, item) {
            $("#comment .parentComment").eq(index).append("            <div class='replay1' style='float: right;width: 80%\'>\n" +
                "                                    <div class='headslider qa-medias l'>\n" +
                "                                        <a href='/known/otherInfo?uid='" + item.answer.id + " class='media' target='_blank'>\n" +
                "                                            <img src='/pic/avatar/" + item.answer.avatar + "' width='40' height='40'></a>\n" +
                "                                    </div>\n" +
                "                                    <div class='wendaslider qa-content'>\n" +
                "                                        <div class='tit'>\n" +
                "                                            <div class='replayName' style='float: left;width: 86%'>\n" +
                "                                                <a href='/known/otherInfo?uid='" + item.answer.id + " target='_blank'>" + item.answer.username + "</a><span\n" +
                "                                                    style='margin-left: 10px;margin-right: 10px'>回复</span><a href='/known/otherInfo?uid='" + item.replay.id + "\n" +
                "                                                                                                             target='_blank'>" + item.replay.username + "</a>\n" +
                "                                            </div>" +
                "                                            <div class='replayIcon' style='float: left;width: 14%'><span\n" +
                "                                                    style='float: left'></span>" +
                "                                                  <span class='replay' my-icon='replay' data-id='" + item.answer.id + "' data-title='" + item.answer.username + "' \n" +
                "                                                                                                    style='float: right;position: relative;top: 4px'></span>\n" +
                "                                            </div>\n" +
                "                                        <div style='clear: both'></div>\n" +
                "                                       </div>\n" +
                "                                        <div class='cnt'>" + item.content + "</div>" +
                "                                        <div class='replymegfooter qa-footer clearfix'>\n" +
                "                                            <span class='r timeago'>" + item.timeInterval + "</span></div>\n" +
                "                                    </div>\n" +
                "                                </div>"
            )

        })
    })

    removeReplay($(".replay"));
}


//解析显示分页信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
        result.extend.pageInfo.pages + "页,总" +
        result.extend.pageInfo.total + "条记录");
    totalRecord = result.extend.pageInfo.total;//最后的数据
    currentPage = result.extend.pageInfo.pageNum;//当前页
}


//解析显示分页条，点击分页要能去下一页....
function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    //构建元素
    //上一页和首页
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum - 1);
        });
    }


    //下一页和最后一页
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.extend.pageInfo.pages);
        });
    }


    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    console.log("navigatepageNums" + result.extend.pageInfo.navigatepageNums);
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.pageInfo.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    //把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}




