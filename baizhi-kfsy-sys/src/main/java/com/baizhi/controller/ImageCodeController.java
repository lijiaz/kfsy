package com.baizhi.controller;
import com.baizhi.util.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 生成图片验证码 Controller
 * Created by MaXn on 2017/6/12.
 */
@Controller
@RequestMapping("/imageCode")
public class ImageCodeController {

    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpSession session, HttpServletResponse response){
        //生成字符
        String code = VerifyCodeUtil.generateVerifyCode(5);
        //保存到Session中
        session.setAttribute("imageCode",code);

        //生成图片
        BufferedImage image = VerifyCodeUtil.getImage(250, 60, code);
        response.setContentType("image/png");
        ServletOutputStream os = null;

        try{
            os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
