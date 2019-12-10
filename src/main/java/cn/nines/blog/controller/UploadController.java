package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.util.FastDFSClient;
import cn.nines.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private static String file_server_url;

    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file){
        //1. 获取文件扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        try {
            //2. 创建一个 FastDFS 的客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //3. 执行上传处理
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            //4. 拼接返回的 url 和 ip 地址，拼接成完整的 url
            String url = file_server_url + path;

            return new JsonResult(200, url);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ExceptionEnum.UPLOAD_FAILED);
        }
    }

}
