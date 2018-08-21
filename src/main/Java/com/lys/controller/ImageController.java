package com.lys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/getIcon.do")
    public void getIcon(@RequestParam("type") String type, @RequestParam("id") String id,
                        HttpServletResponse response) throws IOException {
        String filename;
        if (!type.equals("bg")) {
            filename = type;
        } else {
            filename = "bg" + id;
        }
        filename += ".jpg";


        File file = new File("/usr/local/tomcat/tomcat9/webapps/poem_war/WEB-INF/classes/icons/" + filename);

        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] bs = new byte[1024];
            while (inputStream.read(bs) > 0) {
                outputStream.write(bs);
            }

            outputStream.close();
            inputStream.close();
        }
    }

}
