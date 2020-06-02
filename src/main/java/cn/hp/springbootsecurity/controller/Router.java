package cn.hp.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Ironhide
 * @create 2020-04-26-12:38
 */
@Controller
public class Router {

    @GetMapping({"/", "index"})
    public String toIndexPage() {
        return "index";
    }

    @GetMapping("/toLogin")
    public String toLoginPage() {
        return "views/login";
    }

    @GetMapping("/level1/{id}")
    public String tolevel1Page(@PathVariable("id") Integer id) {
        return "views/level1/" + id;
    }

    @GetMapping("/level2/{id}")
    public String tolevel2Page(@PathVariable("id") Integer id) {
        return "views/level2/" + id;
    }

    @GetMapping("/level3/{id}")
    public String tolevel3Page(@PathVariable("id") Integer id) {
        return "views/level3/" + id;
    }


}
