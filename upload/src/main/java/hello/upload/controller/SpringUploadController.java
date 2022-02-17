package hello.upload.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

/*    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        log.info("request={}", request);
        log.info("itemName={}", itemName);
        log.info("multipartFile={}", file);
        System.out.println("-----"+file.getBytes());
        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        return "upload-form";
    }*/
    @PostMapping("/upload")
    public String saveFile(@ModelAttribute Form form, HttpServletRequest request) throws IOException {

        log.info("request={}", request);
        log.info("itemName={}", form.getItemName());
        log.info("multipartFile={}", form.getFile());

        System.out.println("-----"+form.getFile().getBytes());
        if (!form.getFile().isEmpty()) {
            String fullPath = fileDir + form.getFile().getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            form.getFile().transferTo(new File(fullPath));
        }
        return "upload-form";
    }


    @Data
    static class Form{

        private String itemName;
        private MultipartFile file;

    }

}
