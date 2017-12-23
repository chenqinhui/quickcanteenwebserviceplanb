package com.quickcanteen.controller.api;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

@Controller
public class ResourceController extends APIBaseController {
    public static final String ROOT = "/image";

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/{file}/{companyId}")
    @ResponseBody
    public Map<String, Object> handleFileUpload(@PathVariable MultipartFile file, @PathVariable Integer companyId) {
        Map<String, Object> map = Maps.newHashMap();
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext().getRealPath("/image");
                String fileName = new Date().getTime() + file.getOriginalFilename();
                uploadFile(file.getBytes(), filePath, fileName);
                //Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
                map.put("message", "上传成功");
                map.put("fileName", fileName);
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                map.put("message", "上传出错");
            }
        } else {
            map.put("message", "文件为空");
        }

        return map;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
