package com.xlfd.usermanage.intercepter;


import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ImgIntercepter implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 在执行控制器前要执行preHandle方法，如果返回为true,则不拦截该请求，否则将拦截该请求
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {

		// 判断当前请求是进入新闻编辑页面还是执行新闻的编辑功能
		//如果请求有文件的上传，则该请求就是一个MultpartHttpServletRequest对象
	   if(req instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest)req;
			//取出该请求中所有上传的文件
			Map<String,MultipartFile> fileMap = multipartReq.getFileMap();
			//得到key的迭代器
			Iterator<String> keyIt = fileMap.keySet().iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
				MultipartFile mf = fileMap.get(key);
				String contentType = mf.getContentType();
				if(!contentType.startsWith("image/")){
					
					req.setAttribute("msg", "文件"+key+"error上传不合法；非图片文件");
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/img_upload_error.jsp");
					rd.forward(req, res);
					return false;
				}
				long fileSize = mf.getSize();
				if(fileSize >51250){
		
					req.setAttribute("msg","文件"+key+"none图片超过上传要求大小");
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/img_upload_error.jsp");
					rd.forward(req, res);
					return false;
					
				}
			}
			return true;
		}else{
			return true;
		}
		
		
	}

}
