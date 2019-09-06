package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.CartService;
import service.CommentService;
import service.CourseService;
import service.UserService;
import util.DateDefine;
import util.Msg;
import util.auth.RequireRole;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Create by czq
 * time on 2019/8/27  16:44
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


    static Comparator<Replay> comparator = new Comparator<Replay>() {
        @Override
        public int compare(Replay o1, Replay o2) {
            LocalDateTime o1Time = LocalDateTime.parse(o1.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime o2Time = LocalDateTime.parse(o2.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            long o1m = o1Time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long o2m = o2Time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            if (o1m > o2m) {
                return -1;
            } else if (o1m < o2m) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    @ResponseBody
    @RequestMapping("/displayVideoComment")
    public Msg displayComment(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        int videoId = (int) request.getSession().getAttribute("videoId");
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Comment> comments = commentService.selectCommentByVideoId(videoId);
        for (Comment comment : comments) {
            List<Replay> replays = comment.getReplays();
            Collections.sort(replays, comparator);
        }
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(comments, 3);
        return Msg.result(100, "老师信息", comments).add("pageInfo", page);
    }

    @ResponseBody
    @RequestMapping("/addComment")
    public Msg addComment(Comment comment, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int videoId = (int) request.getSession().getAttribute("videoId");
        comment.setUser(user);
        comment.setVideoId(videoId);
        comment.setTime(DateDefine.getStringDate2());
        Msg msg = commentService.insertCommentService(comment);
        return msg;
    }

    @ResponseBody
    @RequestMapping("/addReplayComment")
    public Msg addReplayComment(Replay replay, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        replay.setAnswer(user);
        replay.setDate(DateDefine.getStringDate2());
        Msg msg = commentService.insertReplayComment(replay);
        return msg;
    }


}
