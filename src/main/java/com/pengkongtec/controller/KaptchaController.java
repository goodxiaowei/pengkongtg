package com.pengkongtec.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class KaptchaController {
	
	private Logger logger = LoggerFactory.getLogger(KaptchaController.class);

	@Resource
	private DefaultKaptcha defaultKaptcha;
	
	@RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{  
        byte[] captchaChallengeAsJpeg = null;
         ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream(); 
         try {
         //生产验证码字符串并保存到session中
         String createText = defaultKaptcha.createText();
         httpServletRequest.getSession().setAttribute("vrifyCode", createText);
         //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中 
         BufferedImage challenge = defaultKaptcha.createImage(createText);
         ImageIO.write(challenge, "jpg", jpegOutputStream);
         } catch (IllegalArgumentException e) {
             httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
             return;
         }
         //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组  
         captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
         httpServletResponse.setHeader("Cache-Control", "no-store");
         httpServletResponse.setHeader("Pragma", "no-cache");  
         httpServletResponse.setDateHeader("Expires", 0);
         httpServletResponse.setContentType("image/jpeg");  
         ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
         responseOutputStream.write(captchaChallengeAsJpeg);
         responseOutputStream.flush();
         responseOutputStream.close();
    }
	
	/**
	 * 验证方法
	 * @Title：
	 * @Description: 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping("/imgvrifyControllerDefaultKaptcha")
	public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){  
	    ModelAndView andView = new ModelAndView();
	     String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
	     String parameter = httpServletRequest.getParameter("vrifyCode");
	     logger.debug("Session  vrifyCode "+captchaId+" form vrifyCode "+parameter);
	    if (!captchaId.equals(parameter)) {
	        andView.addObject("info", "错误的验证码");
	        andView.setViewName("index");
	    } else {
	        andView.addObject("info", "登录成功");
	        andView.setViewName("success");
	    }  
	    return andView;
	}

	@RequestMapping(value = "/loginWithCode")
	public String loginWithCode(Model model){
//		model.addAttribute("info", "验证码登录");
		return "login";
	}
}
