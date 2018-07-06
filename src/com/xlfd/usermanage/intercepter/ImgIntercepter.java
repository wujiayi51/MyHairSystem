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
	 * ��ִ�п�����ǰҪִ��preHandle�������������Ϊtrue,�����ظ����󣬷������ظ�����
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {

		// �жϵ�ǰ�����ǽ������ű༭ҳ�滹��ִ�����ŵı༭����
		//����������ļ����ϴ�������������һ��MultpartHttpServletRequest����
	   if(req instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest)req;
			//ȡ���������������ϴ����ļ�
			Map<String,MultipartFile> fileMap = multipartReq.getFileMap();
			//�õ�key�ĵ�����
			Iterator<String> keyIt = fileMap.keySet().iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
				MultipartFile mf = fileMap.get(key);
				String contentType = mf.getContentType();
				if(!contentType.startsWith("image/")){
					
					req.setAttribute("msg", "�ļ�"+key+"error�ϴ����Ϸ�����ͼƬ�ļ�");
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/img_upload_error.jsp");
					rd.forward(req, res);
					return false;
				}
				long fileSize = mf.getSize();
				if(fileSize >51250){
		
					req.setAttribute("msg","�ļ�"+key+"noneͼƬ�����ϴ�Ҫ���С");
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
