package com.llq.xpath;

import static org.junit.Assert.*;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Demo5 {

	/**
	 * 查找xml文档中是否有和用户相匹配的用户名和密码
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		
		String username="aaa";
		String password="123";
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/users.xml"));
		
		Node node=document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
		if(node==null){
			System.out.println("用户名或密码错误");
		}else{
			System.out.println("登录成功");
		}
		
	}
}
