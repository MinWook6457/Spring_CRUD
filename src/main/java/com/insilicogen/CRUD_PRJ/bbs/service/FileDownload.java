package com.insilicogen.CRUD_PRJ.bbs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download/*")
public class FileDownload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "/absolute/path/to/save/directory";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getPathInfo().substring(1); // /download/{filePath}에서 filePath 추출
        System.out.println("파일 경로" + filePath);
        File file = new File(UPLOAD_DIR, filePath);

        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());

            try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 파일이 없는 경우 404 에러
        }
    }
}
