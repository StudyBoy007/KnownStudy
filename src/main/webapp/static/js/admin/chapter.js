//得到项目根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

var totalRecord, currentPage;
$(function () {
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        url: getRootPath() + "displayChapterPage",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            //1、解析并显示员工数据
            build_users_table(result);
            //2、解析并显示分页信息
            build_page_info(result);
            //3、解析显示分页条数据
            build_page_nav(result);
        }
    });
}

function build_users_table(result) {
    console.log(result)
    //清空table表格
    $("#user_table tbody").empty();
    var chapters = result.extend.pageInfo.list;
    $.each(chapters, function (index, item) {
        var checkBoxTd = $("<td width='4%'><input type='checkbox' class='check_item'/></td>");
        var cId = $("<td width='4%'></td>").append(item.id);
        var courseId= $("<td width='4%'></td>").append(item.courseid);
        var cname = $("<td width='12%'></td>").append(item.chapterName);
        var video = $("<td width='4%'></td>").append("<a href='/known/displayVideoAdmin?chapterId=" + item.id + "' target='_self'>查看章节视频</a>");
        var vNum=$("<td width='4%'></td>").append(item.videos.length);

        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
        //为编辑按钮添加一个自定义的属性，来表示当前员工id
        editBtn.attr("edit-id", item.id);
        var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("删除");
        //为删除按钮添加一个自定义的属性来表示当前删除的员工id
        delBtn.attr("del-id", item.id);
        var btnTd = $("<td width='12%'></td>").append(editBtn).append(" ").append(delBtn);
        //var delBtn =
        //append方法执行完成以后还是返回原来的元素
        $("<tr></tr>").append(checkBoxTd)
            .append(cId)
            .append(courseId)
            .append(cname)
            .append(video)
            .append(vNum)
            .append(btnTd)
            .appendTo("#user_table tbody");
    });
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


//清空表单样式及内容
function reset_form(ele) {
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}

//点击新增按钮弹出模态框。
$("#addChapter").click(function () {
    //清除表单数据（表单完整重置（表单的数据，表单的样式））
    reset_form("#chapterAddModel form");
    //弹出模态框
    $("#chapterAddModel").modal({
        backdrop: "static"
    });
});


//点击保存，保存员工。
// $("#blog_save_btn").click(function () {
//
// });

//批量删除
$("#blog_delete_all_btn").click(function () {
    var blogIds = new Array();

    $(".check_item:checked").each(function (i) {
        blogIds[i] = $(this).parent().nextAll(".blogId").text();
    })

    //2、发送ajax请求保存员工
    $.ajax({
        url: "${APP_PATH}/batchDelete2",
        dataType: "json",
        traditional: true,
        contentType: "application/json",
        type: "POST",
        data: JSON.stringify(blogIds),
        success: function (result) {
            //alert(result.msg);
            if (result.code == 100) {
                //2、显示刚才保存的数据
                to_page(1);
            } else {
                //显示失败信息
                console.log(result);
            }
        }
    });
})


//单个删除
$(document).on("click", ".delete_btn", function () {
    //1、弹出是否确认删除对话框
    var empName = $(this).parents("tr").find("td:eq(3)").text();
    var chapterId = $(this).attr("del-id");

    if (confirm("确认删除【" + empName + "】吗？")) {
        //确认，发送ajax请求删除即可
        $.ajax({
            url: getRootPath()+"deleteChapterAdmin?chapterId=" + chapterId,
            type: "POST",
            success: function (result) {
                // alert(result.msg);
                // //回到本页
                // to_page(1);
                if (result.code == 100) {
                    //1.删除成功
                    //2、显示刚才保存的数据
                    to_page(currentPage);
                } else {
                    //显示失败信息
                    console.log(result);
                }
            }
        });
    }
});


$(document).on("click", ".edit_btn", function () {
    var chapterId = $(this).attr("edit-id");
    $.ajax({
        url: getRootPath()+ "editChapterAdmin?id=" + chapterId,
        type: "GET",
        success: function (result) {
            // alert(result.msg);
            // //回到本页
            // to_page(1);
            if (result.code == 100) {
                //1.放回编辑对象的信息
                $("#id_modify_input").val(result.o.id);
                $("#courseId_modify_input").val(result.o.courseid);
                $("#chapterName_modify_input").val(result.o.chapterName);
                $("#fileName_modify_input").val(result.o.path)
                //弹出模态框
                $("#chapterModifyModel1").modal({
                    backdrop: "static"
                });
            } else {
                //显示失败信息
                console.log(result);
            }
        }
    });
})

function editChapter(){
    console.log($("#chapterModifyModel1 form").serialize());
    //2、发送ajax请求保存员工
    $.ajax({
        url: getRootPath()+"modifyChapterAdmin",
        type: "POST",
        data: $("#chapterModifyModel1 form").serialize(),
        success: function (result) {
            //alert(result.msg);
            if (result.code == 100) {
                //员工保存成功；
                //1、关闭模态框
                $("#chapterModifyModel1").modal('hide');

                //2、显示刚才保存的数据
                to_page(currentPage);
            } else {
                //显示失败信息
                console.log(result);
            }
        }
    });
}

function addChapter() {
    console.log($("#chapterAddModel form").serialize());
    //2、发送ajax请求保存员工
    $.ajax({
        url: getRootPath()+"addChapterAdmin",
        type: "POST",
        data: $("#chapterAddModel form").serialize(),
        success: function (result) {
            //alert(result.msg);
            if (result.code == 100) {
                //员工保存成功；
                //1、关闭模态框
                $("#chapterAddModel").modal('hide');

                //2、显示刚才保存的数据
                to_page(totalRecord);
            } else {
                //显示失败信息
                console.log(result);
            }
        }
    });
}

