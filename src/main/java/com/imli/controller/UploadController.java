package com.imli.controller;

import cn.hutool.core.io.FileUtil;
import com.imli.pojo.Result;
import com.imli.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping()
public class UploadController {
    private static String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";
    //文件路径 + 分隔符 +文件名
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String mainName = FileUtil.mainName(originalFilename);//文件名
        String extName = FileUtil.extName("文件的后缀");//文件后缀
        File parentFile = new File(ROOT_PATH);
        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH);//如果当前文件的上级目录不存在，就创建目录
        }
        int num =1;
        if (FileUtil.exist(ROOT_PATH + File.separator + originalFilename)) { //如果已经上传过，则为文件添加序号区分
            originalFilename = mainName + "(" + num +")" + extName;

        }
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        file.transferTo(saveFile);
        String url = "http://localhost:8080/file/download/" +originalFilename;
        return Result.success(url);
    }

    @GetMapping("/download/{fileName}")
    public void downLoad(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + fileName;
        if(FileUtil.exist(filePath)) {
            byte[] bytes = FileUtil.readBytes(filePath);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);//文件的字节流数组
            outputStream.flush();
            outputStream.close();
        }
    }
}
