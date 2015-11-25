package com.Universities.web.Controller;

import com.Universities.web.facade.ProfessorFacade;
import com.Universities.web.facade.StudentFacade;
import com.Universities.web.services.ProfessorService;
import com.Universities.web.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by andreealibotean on 11/24/2015.
 */
@Controller
public class DownloadStudentsController {


    @RequestMapping(value="/studentsPdf", method = RequestMethod.GET)
    public void donwloadCoursesPdf(HttpServletResponse response) throws IOException {

        File file=new File("C:\\Users\\andreealibotean\\Desktop\\University\\src\\main\\webapp\\resources\\pdf\\students.pdf");

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());


    }
}