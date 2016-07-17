package com.uwaytech.security.ctrl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.util.RandCodeUtil;
import com.uwaytech.spring_interceptors.UnAccessTokenAuth;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 负责输出验证码
 */
@RestController
@RequestMapping("/randcode/v0.1")
public class RandCodeImageController {

    private InputStream imageStream;
    public static final Logger logger = Logger.getLogger(RandCodeImageController.class);
    ;

    /**
     * 生成验证码,并存放在session中
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/randcode", method = {RequestMethod.GET})
    @UnAccessTokenAuth
    public void randcode(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            RandCodeUtil creatimg = new RandCodeUtil("PNG");
            this.imageStream = creatimg.getInputStream();
            String randcode = creatimg.getRandcode();
            session.setAttribute("randcode", randcode);
            logger.debug("session." + session.getId() + ".randcode = " + randcode);
            OutputStream os = response.getOutputStream();
            byte[] btImg = readStream(this.imageStream);
            os.write(btImg);
            os.flush();
            os.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 读取管道中的流数据
     */
    public byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 检查验证码是否有效
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkCode", method = {RequestMethod.GET})
    @UnAccessTokenAuth
    public Object checkCode(HttpServletRequest request, @RequestParam(value = "checkCode") String checkCode) {
        String code = (String) request.getSession().getAttribute("randcode");
        if (!(StringUtils.isNotEmpty(code) && code.trim().equalsIgnoreCase(checkCode))) {
            throw new ParamErrorException("验证码不正确");
        }
        return new SuccessResult();
    }

    public InputStream getImageStream() {
        return imageStream;
    }

}
