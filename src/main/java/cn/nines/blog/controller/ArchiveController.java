package cn.nines.blog.controller;

import cn.nines.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BlogService blogService;

    @GetMapping("/years")
    public List<String> getYears(){
        try {
            return blogService.getYears();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/blogs")
    public List findArchiveBlog(@RequestBody List<String> years){
        try {
            return blogService.findArchiveBlogByYears(years);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

}
