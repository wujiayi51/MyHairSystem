package com.xlfd.usermanage.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.NewsAddress;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.UserDao;
import com.xlfd.usermanage.service.UserService;

@Service(value="userService")

public class UserServiceImpl implements UserService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="userDao")//@Qualifier注解的作用是配置自动注入的来源
	private UserDao dao;
	public void saveUser(User u,String rootPath) {
		MultipartFile mf = u.getUserImg();
		String contentType = mf.getContentType();
		if(contentType.startsWith("image/")){
			String oldFileName = mf.getOriginalFilename();
			//获取原来文件的后缀名
			int lastPontIndex = oldFileName.lastIndexOf(".");
			String extendName = oldFileName.substring(lastPontIndex);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");//now.getTime()长整型，毫秒数
			String newFileName = sdf.format(now)+extendName;
			File  f = new File(rootPath+"\\"+newFileName);//将上传来的文件保存
			
			//入职时间格式化
			//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				
				mf.transferTo(f);//将上传来的文件写到本地路径
				u.setImgName(newFileName);
				u.setHireDate(new java.sql.Date(now.getTime()));//入职时间
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u.setState(1);
			dao.saveUser(u);
		}
	}
		public void deleteUser(int UserId) {
			// TODO Auto-generated method stub
			dao.deleteUser(UserId);
		}
		public User getUser(int UserId) {
			// TODO Auto-generated method stub
			return dao.getUser(UserId);
		}
		public void updateUser(User User4Update,User u) {
			u.setuName(User4Update.getuName());
			
			u.setuSex(User4Update.getuSex());
			if(User4Update.getBelongUserType().getUtName()!=u.getBelongUserType().getUtName()){
				u.setAdvanceUtname(User4Update.getBelongUserType().getUtName());
			}
			dao.updateUser(u);
		}
		public void updateUser(User u,int state,String leaveTimeStr) {
			u.setState(state);
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 		Date leaveDate = null;
	 		try {
				leaveDate = sdf.parse(leaveTimeStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		if(state==1){
	 			u.setHireDate(leaveDate);
	 			u.setLeaveDate(null);
	 		}else{
	 			u.setLeaveDate(leaveDate);
	 		}
			dao.updateUser(u);
		}
		public List<User> queryUser(String keyword,int pagenum,int pagesize) {
			// TODO Auto-generated method stub
			String queryStr = "";
			List paramList = new ArrayList();
			//判断keyword是否有内容：null,"",空格------>为没有内容；
			if(StringUtils.hasText(keyword)){
				queryStr = "uName like ?";
				paramList.add("%"+keyword+"%");
			}
			int startIndex = (pagenum-1)*pagesize;//根据自增id的来看
			return dao.queryUser(queryStr, paramList,startIndex,pagesize);
		}
		public long getUserPageCount(String keyword,int pagesize) {
			String queryStr = "";
			List paramList = new ArrayList();
			//判断keyword是否有内容：null,"",空格------>为没有内容；
			if(StringUtils.hasText(keyword)){
				queryStr = "uName like ?";
				paramList.add("%"+keyword+"%");
			}
			
			long recordCount = dao.queryUserCount(queryStr, paramList);
			long pagecount = (long)Math.ceil(recordCount/(double)pagesize);
			return (long)pagecount;
		}
		public List<UserType> queryAllType() {
			List paramList = new ArrayList();
			return dao.queryAllType(paramList);
		}
		//修改等级
		//修改员工等级
		public void updateUserGrade() {
			String queryStr = " advanceUtname is not null ";
			List<User> userList = dao.updateUserGrade(queryStr);
			 for(Iterator iterators = userList.iterator();iterators.hasNext();){
		          User u = (User) iterators.next();//获取当前遍历的元素，指定为Example对象
		          String newGradeName = u.getAdvanceUtname();
		          String queryStr2 = "";
				  List paramList = new ArrayList();
				  //判断keyword是否有内容：null,"",空格------>为没有内容；
				  if(StringUtils.hasText(newGradeName)){
						queryStr2 = " utName = ?";
						paramList.add(newGradeName);
					}
		          List<UserType> usertype = dao.getUserType(queryStr2,paramList);
		          u.setBelongUserType(usertype.get(0));
		          u.setAdvanceUtname(null);
		      }
		}
	
		
		

}
