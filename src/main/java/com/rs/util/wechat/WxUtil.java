package com.rs.util.wechat;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rs.util.HttpUtil;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * 
 * @ClassName: WxUtil
 * @Description: 微信工具类
 * @Author tangsh
 * @DateTime 2020年3月9日 下午2:59:57
 */
public class WxUtil {
	
	/**
	 * 
	 * @Title: getAccessToken
	 * @Description: 获取微信access_token 有效期7200秒
	 * @Author tangsh
	 * @DateTime 2020年3月9日 下午3:05:58
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appid,String secret) {
		String access_token="";
		HttpResponse response = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret).send();
        String jsonStr = response.body();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        access_token = (String) jsonObject.get("access_token");
		return access_token;
	}
	
	/**
	 * 
	 * @Title: getWxUserList10000
	 * @Description: 拉去公众号关注的1万个openid
	 * @Author tangsh
	 * @DateTime 2020年3月9日 下午3:09:41
	 * @param access_token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getWxUserListOpenid10000(String access_token) {
		HttpResponse response = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/user/get?access_token="+access_token).send();
        String jsonStr = response.body();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject jonext = (JSONObject)jsonObject.get("data");
        List<String> openidlist=(List<String>)jonext.getObject("openid", List.class);
		return openidlist;
	}
	
	/**
	 * 
	 * @Title: getWxUser
	 * @Description: 根据openid获取指定的用户信息
	 * @Author tangsh
	 * @DateTime 2020年3月9日 下午3:55:46
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static WeiXinUser getWxUser(String access_token,String openid) {
		WeiXinUser wsu=new WeiXinUser();
		String jsonStr=HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN", "utf-8", null);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        wsu.setOpenid(jsonObject.getString("openid"));
        String nickname=jsonObject.get("nickname").toString();
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE) ;
        Matcher emojiMatcher = emoji.matcher(nickname) ;
        if (emojiMatcher.find()) {
        	nickname=nickname.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
        }
        wsu.setNickname(nickname);
        wsu.setSex(jsonObject.getInteger("sex"));
        wsu.setCity(jsonObject.getString("city"));
        wsu.setProvince(jsonObject.getString("province"));
        wsu.setCountry(jsonObject.getString("country"));
        wsu.setHeadimgurl(jsonObject.getString("headimgurl"));
		return wsu;
	}
	
	
	public static void main(String[] args) {
		
		String access_token="31__-pkKVqwJOaAG_646JznM4iLb8eJdbaz5xRxHB7-r5tK_gdvF8rVV279ZG-2K-FprKlg2oikUFoRs-PqwhFSzQC3NPCRQEp6gJwR_bSjF2JY7qG0Ln7NMQq8R0qbBoqq9NW9FXqG-m_rAHr3RSOcAEACDZ";//getAccessToken("wx54e6d3f80c2b7173","4f44eefbbd38e25934629d6d29cdd04d");
		//System.out.println(access_token);
//		List<String> openidlist=getWxUserListOpenid10000(access_token);
//		if(openidlist!=null&&openidlist.size()>0) {
//			for(String openid:openidlist) {
//				System.out.println(openid);
//			}
//		}
//		ovlGAw_OGlwy_NN-kxLL0ZMzilHw
//		ovlGAwzsGG16Gf3t8QH-_bHdiX8c
//		ovlGAw8-4bgHT1GvoPGw7tJp_CwM //tsh
//		ovlGAw7olwoAV35Dr99DaZ_JRvDI //流云
//		ovlGAw_Nz9DTFCoiGWWMrAijLqzo //树先生
//		ovlGAw304BSyCohyiWwYGozu3l-c //王祖一
//		ovlGAw3bo297LvGwpQQyyQ03RjSY //NAMANANA
//		ovlGAw_tMgUiw0mm_Q1Cv7QqUBIw //半个刺猬
//		ovlGAw1KK0Z6rhzzUvksZtmYoA74 //王安石骑大哈雷
//		ovlGAw4OqT-JkoDFdnqyeLMm2w98 //
//		ovlGAw9kaPgShvgxhu27BhExKkzs
//		ovlGAw_6j-x5P5cSdHeWZczXBD-s //小新
//		ovlGAw8ed0m7sMipRvzrIUARQjK4 //sven
		System.out.println(getWxUser(access_token,"ovlGAw7olwoAV35Dr99DaZ_JRvDI").getNickname());
	}
}
