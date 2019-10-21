package cn.nines.blog.controller;

import cn.nines.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("博客找不到！");
//        }
//        System.out.printf("id=%s,name=%s\n", id, name);
        return "index.html";
    }

}
