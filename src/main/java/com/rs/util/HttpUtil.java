package com.rs.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * HTTP的GET POST PUT DELETE访问工具类
 * @tokenor sven
 *
 */
public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	
	/**
	 * 环信通用get请求 带有验证参数
	 * @param url
	 * @param charset
	 */
	public static String sendGet(String url,String charset,String token){
		String result = "";
		BufferedReader in = null;
		try {
			logger.info("request url:{}",url);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if(!StringUtils.isEmpty(token)){
				connection.setRequestProperty("Authorization","Bearer "+token);
			}
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//System.out.println("result------"+result);
		return result;
	}
	
	/**
	 * 环信通用post请求 带有验证参数
	 * @param url
	 * @param param
	 * @param charset
	 * @param token
	 * @return
	 */
	public static String sendPost(String url, String param ,String charset,String secret,String token){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			System.out.println("url------------------"+url);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-type", "application/json"); 
			if(!StringUtils.isEmpty(secret)) {
				System.out.println("LemAuthorization--------"+secret);
				connection.setRequestProperty("LemAuthorization", secret); 
			}
			if(!StringUtils.isEmpty(token)){
				System.out.println("Authorization--------"+token);
				connection.setRequestProperty("Authorization","bearer "+token);
			}
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if(!"".equals(param)){
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),charset)), true);
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("result------"+result);
		return result;
	}
	
	/**
	 * 普通post请求,str=url+param
	 * @param str
	 * @return
	 */
	public static String sendPost(String str){
		String sCurrentLine = "";
		String sTotalString = "";
		try{
			System.out.println("url-------" + str);
			URL url = new URL(str);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(1000 * 10);// 设置连接超时
			connection.setReadTimeout(1000 * 10);// 设置读取超时
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.write(str);
			out.flush();
			out.close();
			InputStream l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "UTF-8"));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("result-------"+sTotalString);
		return sTotalString;
	}
	
	
	public static String sendPost(String url,String param,String charset){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			//System.out.println("url------------------"+url);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded"); 
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if(!"".equals(param)){
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),charset)), true);
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("result------"+result);
		return result;
	}
	
	public static void main(String[] args) {
		sendPost("https://uic.youzan.com/sso/open/initToken","client_id=630817969bbe5f0530&client_secret=d20ad858379fc44cca4949280762f950","UTF-8");
		
	}
	
	/**
	 * 环信通用delete请求
	 * @param url
	 * @param param
	 * @param method
	 * @param charset
	 * @return
	 */
	public static String sendDelete(String url,String param,String method,String charset,String token){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if(!("").equals(token) && null != token){
				connection.setRequestProperty("Authorization","Bearer "+token);
			}
			connection.setRequestMethod(method);  
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if(!"".equals(param)){
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),charset)), true);
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("result------"+result);
		return result;
	}
	
	/**
	 * 
	 * @param url      请求地址
	 * @param param    请求参数
	 * @param method   请求方法
	 * @param charset  请求字符集
	 * @param token     请求认证
	 * @return
	 */
	public static String sendPut(String url,String param,String method,String charset,String token){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			// 设置通用的请求属性
			/*connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");*/
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if(!("").equals(token) && null != token){
				connection.setRequestProperty("Authorization","Bearer "+token);
			}
			connection.setRequestMethod(method);  
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if(!"".equals(param) && null != param){
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),charset)), true);
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("result------"+result);
		return result;
	}
	
	/**
	 * 将URL的参数和body参数合并
	 * @Title: getAllParams
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月11日 下午4:31:25
	 * @param request
	 * @return
	 * @throws IOException
	 */
    public static SortedMap<String, Object> getAllParams(HttpServletRequest request) throws IOException {
        SortedMap<String, Object> result = new TreeMap<String, Object>();
        // 获取URL上的参数
        getUrlParams(request, result);
        // 获取body参数
        getAllRequestParam(request, result);
        return result;
    }

	/**
	 * 获取 Body 参数
	 * @Title: getAllRequestParam
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月11日 下午4:31:31
	 * @param request
	 * @param result
	 * @throws IOException
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getAllRequestParam(final HttpServletRequest request, SortedMap<String, Object> result)
        throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str = "";
        StringBuilder wholeStr = new StringBuilder();
        // 一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        wholeStr.trimToSize();
        String s = wholeStr.toString();
        if (!StringUtils.isEmpty(s)) {
            // 转化成json对象
            Map<String, Object> allRequestParam = JSONObject.parseObject(s, Map.class);
            // 将URL的参数和body参数进行合并
            for (Map.Entry entry : allRequestParam.entrySet()) {
                result.put((String)entry.getKey(), (Object)entry.getValue());
            }
        }
    }

    /**
     * 获取url参数
     * @Title: getUrlParams
     * @Description: 
     * @Author sven
     * @DateTime 2019年12月11日 下午4:31:40
     * @param request
     * @param result
     */
    public static void getUrlParams(HttpServletRequest request, SortedMap<String, Object> result) {
        String param = "";
        try {
            String urlParam = request.getQueryString();
            if (urlParam != null) {
                param = URLDecoder.decode(urlParam, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] params = param.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            if (index != -1) {
                result.put(s.substring(0, index), s.substring(index + 1));
            }
        }
    }
}
