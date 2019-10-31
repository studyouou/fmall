package org.og.fmall.fmallshop.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.og.fmall.commonapi.constants.OrderConstants;
import org.og.fmall.commonapi.enums.CommonEnum;
import org.og.fmall.commonapi.result.Result;
import org.og.fmall.commonapi.utils.AESUtil;
import org.og.fmall.commonapi.utils.ImageUtil;
import org.og.fmall.commonapi.utils.JSONUtil;
import org.og.fmall.commonapi.utils.ResultUtil;
import org.og.fmall.commontools.redis.RedisService;
import org.og.fmall.fmallshop.vo.FruitVo;
import org.og.fmall.member.verification.annotation.AccessKey;
import org.og.fmall.user.api.session.MemberSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

/**
 * @author:ougen
 * @date:2019/10/723:11
 */
@RestController
public class UtilController {

    private static Logger logger = LoggerFactory.getLogger(UtilController.class);

    @Value("${cookie.domain:localhost}")
    private String domain;

    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public Result<String> getVerifyCod(HttpServletRequest request, HttpServletResponse response) {
        Result<String> result = ResultUtil.build();
        String salt = RandomStringUtils.random(10,true,true);
        try {
            OutputStream out = response.getOutputStream();
            Cookie cookieSalt = new Cookie("registerSalt",salt);
            Cookie cookieValue = new Cookie("registerValue","");
            BufferedImage image = ImageUtil.createVerifyCodeRegister(cookieValue);
            String md5Hex = DigestUtils.md5Hex(salt + cookieValue.getValue());
            cookieValue.setValue(md5Hex);
            cookieSalt.setMaxAge(120);
            cookieValue.setMaxAge(120);
            cookieSalt.setPath("/");
            cookieValue.setPath("/");

            cookieSalt.setHttpOnly(true);
            cookieValue.setHttpOnly(true);
            cookieSalt.setDomain(domain);
            cookieValue.setDomain(domain);
            response.addCookie(cookieSalt);
            response.addCookie(cookieValue);
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("生成验证码错误---注册用户:{}", e);
            return result;
        }
        return result;
    }

    @GetMapping("/isLogin")
    public Result result(MemberSession memberSession){
        Result result = ResultUtil.build();
        if (memberSession == null){
            result.setCode(CommonEnum.MEMBER_NOT_LOGIN.getCode());
            result.setMsg(CommonEnum.MEMBER_NOT_LOGIN.getMsg());
            return result;
        }
        result.setData(memberSession);
        return result;
    }

    @GetMapping("/des/enCode")
    @AccessKey
    public Result<String> enCode(FruitVo fruitVo,MemberSession memberSession){
        Result<String> result = ResultUtil.build();
        if (fruitVo == null){
            result.setCode(CommonEnum.PARAMERTER_WRONG.getCode());
            result.setMsg(CommonEnum.PARAMERTER_WRONG.getMsg());
            return result;
        }
        String j = JSONUtil.beanToString(fruitVo);
        try {
            String encode = AESUtil.encode(memberSession.getSalt() + OrderConstants.ENCODE, j);
            result.setData(encode);
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(CommonEnum.ENCODE_FAIL.getCode());
        result.setMsg("服务器错误");
        return result;
    }

    @GetMapping("/des/decode")
    @AccessKey
    public Result<FruitVo> decode(@RequestParam("encParam") String encParam,MemberSession memberSession){
        Result<FruitVo> result = ResultUtil.build();
        try {
            String decode = AESUtil.decode(memberSession.getSalt()+OrderConstants.ENCODE, encParam);
            FruitVo fruitVo = JSONUtil.stringToBean(decode,FruitVo.class);
            result.setData(fruitVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(CommonEnum.DECODE_FAIL.getCode());
        result.setMsg("服务器错误");
        return result;
    }
}
