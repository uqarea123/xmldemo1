package com.llq.dom4j;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.DocumentHandler;

public class Demo1 {

	/**
	 * 读取xml文档第二本书的：<书名>JavaScript网页开发</书名>
	 * 
	 * @throws Exception
	 */
	@Test
	public void readText() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element root = document.getRootElement();
		Element book = (Element) root.elements("书").get(1);
		String value = book.element("书名").getText();
		System.out.println(value);

	}

	/**
	 * 读取xml文档第二本书的属性：<书名 name="xxx">JavaScript网页开发</书名>
	 * 
	 * @throws Exception
	 */
	@Test
	public void readAttr() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element root = document.getRootElement();
		Element book = (Element) root.elements("书").get(1);
		String value = book.element("书名").attributeValue("name");
		System.out.println(value);

	}

	/**
	 * 在第一本书上添加一个售价：<售价>209元</售价>
	 * 
	 * @throws Exception
	 */
	@Test
	public void add1() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element book = document.getRootElement().element("书");
		book.addElement("售价").setText("209元");

		// 指定文件
		/*XMLWriter writer = new XMLWriter(new FileWriter("src/book.xml"));*/
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/book.xml"), "UTF-8")); 

		writer.write(document);
		writer.close();
	}
	
	/**
	 * 在第一本书上添加一个售价：<售价>209元</售价>
	 * 
	 * 可以在xml文档中指定字符编码  encoding="UTF-8"
	 * @throws Exception
	 */
	@Test
	public void add2() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element book = document.getRootElement().element("书");
		book.addElement("售价").setText("209元");

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		// 指定文件
//		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/book.xml"), "gb2312"),format); 
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format); 
		
		writer.write(document);
		writer.close();
	}
	
	
	/**
	 * 在第一本书上指定位置上添加一个售价：<售价>309元</售价>  更改保存了所有孩子的list集合的顺序
	 * 
	 * 可以在xml文档中指定字符编码  encoding="UTF-8"
	 * @throws Exception
	 */
	@Test
	public void add3() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element book = document.getRootElement().element("书");
		List list=book.elements();//[书名，作者，售价]
		
		Element price=DocumentHelper.createElement("售价");
		price.setText("309元");
		list.add(2, price);
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format); 
		
		writer.write(document);
		writer.close();
	}
	
	/**
	 * 删除上面的售价节点
	 * @throws Exception
	 */
	@Test
	public void delete1() throws Exception {
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element price=document.getRootElement().element("书").element("售价");
		price.getParent().remove(price);
		
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format); 
		
		writer.write(document);
		writer.close();
		
	}
	
	
	/**
	 * 更新第二本书的作者
	 * @throws Exception
	 */
	@Test
	public void update1() throws Exception {
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element book=(Element) document.getRootElement().elements("书").get(1);
		book.element("作者").setText("小明");
		
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format); 
		
		writer.write(document);
		writer.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
