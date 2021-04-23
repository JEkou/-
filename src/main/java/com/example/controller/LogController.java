package com.example.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * @author USER
 */
@RestController
public class LogController {

    /**
     * 获取日志信息
     * @param data 日志的日期，以yyyy-mm-dd的格式输出
     * @return 日志的内容
     */
    @GetMapping("/log")
    public String getLog(String data) {
        List<String> lines = new ArrayList();
        Scanner sc = null;
        try {
            if (data == null) {
                InputStream in = new BufferedInputStream(new FileInputStream("log/spring.log"));
                sc = new Scanner(in);
                addContentToList(lines,sc);
            } else {
                String sourceDir = "log/spring.log." + data;
                while (true) {
                    int num = 0;
                    sourceDir += "." + num + ".gz";
                    InputStream in = new GZIPInputStream(new FileInputStream(sourceDir));
                    sc = new Scanner(in);
                    addContentToList(lines,sc);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toString();
    }

    /**
     * 下载日志文件
     * @param request 系统的请求对象，获取请求的参数
     * @return 日志文件的二进制数据
     * @throws IOException I/O异常,包括编码不支持异常、文件没找到异常
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downLoad(HttpServletRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        File file = new File("log/spring.log");
        String fileName = file.getName();
        if (file.exists()) {
            //下载显示的文件名，解决中文名称乱码问题
            String userAgent = request.getHeader("user-agent").toLowerCase();
            String downloadFileName;

            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                downloadFileName = URLEncoder.encode((fileName), "UTF-8");
            } else {
                downloadFileName = new String((fileName).getBytes("UTF-8"), "iso-8859-1");
            }
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        } else {
            throw new FileNotFoundException("文件不存在");
        }
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);


    }

    /**
     * 将数据添加到list
     * @param lines 保存的列表
     * @param sc 数据源
     */
    private void addContentToList(List<String> lines,Scanner sc){
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine()+"\r\n");
        }
    }

}
