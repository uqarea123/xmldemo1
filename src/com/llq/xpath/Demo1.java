package com.llq.xpath;

import static org.junit.Assert.*;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Demo1 {

	/**
	 * 应用xpath提取xml文档中的数据
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));
		
		String value=document.selectSingleNode("//作者").getText();
		System.out.println(value);

		
	}
}
