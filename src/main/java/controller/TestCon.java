package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by czq
 * time on 2019/8/19  16:45
 */
@Controller
public class TestCon {
    @RequestMapping("/")
    public String listDisplay() {
        return "video";
    }


    @RequestMapping("/test1")
    public String indexDisplay1() {
        return "info1";
    }
}
