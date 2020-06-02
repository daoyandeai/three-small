package com.rs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rs.po.TokenParam;
import com.rs.util.wechat.CheckUtil;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: CompanyController
 * @Description: 三小档案控制层
 * @Author tangsh
 * @DateTime 2019年12月31日 下午3:38:07
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin
@RequestMapping("/app/wx")
public class AppWeChatController extends BaseController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenParam tokenParam;
    
    
    //private static final String APPID = "wxcb58c6781036bf04";
//    private static final String APPID = "wx54e6d3f80c2b7173";
    
    //private static final String APPSECRET = "2fa3e6e8494f66c28b940541a45f7575";
//    private static final String APPSECRET = "4f44eefbbd38e25934629d6d29cdd04d";
//    private static final String REDIRECT_URI ="http://js.vipgz5.idcfengye.com/three-small/app/wx/getAccess_token";
    private static final String REDIRECT_URI ="http://js.vipgz5.idcfengye.com/api/";
    
    
    
	/*
	 * 验证微信号
	 */
	@GetMapping("/verify_wx_token")
    @ResponseBody
    public void verify_wx_token(HttpServletRequest request, HttpServletResponse response)  throws UnsupportedEncodingException {
        System.out.println("测试微信公众号");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(echostr);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            	System.out.println(echostr);
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
	
	
	/**
	 * 提供给前端验签
	 * @param requesturl
	 * @return
	 */
	@GetMapping("wx_config")
    public Object wx_config(@RequestParam String requesturl){
        ValueOperations ops = redisTemplate.opsForValue();
        Object access_token = ops.get("access_token");
        if ( access_token == null){
            HttpResponse response = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+tokenParam.getAppid()+"&secret="+tokenParam.getSecret()).send();
            String jsonStr = response.body();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            access_token = (String) jsonObject.get("access_token");
            ops.set("access_token", access_token, 6000, TimeUnit.SECONDS);

        }


        Object ticket = ops.get("ticket");
        if (ticket == null) {
            HttpResponse response = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi").send();
            String jsonStr = response.body();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            ticket = (String) jsonObject.get("ticket");
            ops.set("ticket", ticket, 6000, TimeUnit.SECONDS);

        }


        Map<String, String> ret = new HashMap<>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String jsApiTicket = (String) ticket;
        String url = requesturl;
        String signature = "";


        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("ticket=============>"+ticket.toString());
        System.out.println("nonceStr=============>"+nonceStr);
        System.out.println("timestamp=============>"+timestamp);
        System.out.println("url=============>"+url);

        try {
            // 初始化对象
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsApiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appid", tokenParam.getAppid());
        
        
        System.out.println("url=============>"+url);
        System.out.println("jsapi_ticket=============>"+jsApiTicket);
        System.out.println("nonceStr=============>"+nonceStr);
        System.out.println("timestamp=============>"+timestamp);
        System.out.println("signature=============>"+signature);
        System.out.println("appid=============>"+tokenParam.getAppid());
        return finish(CODEMSG, ret);
    }

    /**
     * 生成随机字符串
     *
     * @return 随机字符串
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成时间戳
     *
     * @return 时间戳
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param hash 字节数组
     * @return 十六进制字符串
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    
    /**
     * 获得openId
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("getCode")
    private ModelAndView getCode() throws UnsupportedEncodingException {

        String redirect_uri = URLEncoder.encode(REDIRECT_URI, "UTF-8" );
        return new ModelAndView("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + tokenParam.getAppid() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=200#wechat_redirect");
    }

    @GetMapping("getAccess_token")
    @ResponseBody
    private Object getAccess_token(@RequestParam String code){
        HttpResponse response = HttpRequest.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + tokenParam.getAppid() + "&secret=" + tokenParam.getSecret() + "&code=" + code + "&grant_type=authorization_code").send();
        Map<String, String> map = new HashMap<>();
        map.put("openid",response.body());
        CODEMSG = SUCCESS;
        return finish(CODEMSG, map);
    }
    
    

}
