package com.llq.sax;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class Demo2 {

	/**
	 * sax方式解析book1.xml文件
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		
		// 1.创建工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// 2.用工厂创建解析器
		SAXParser sp = factory.newSAXParser();

		// 3.利用解析器得到reader
		XMLReader reader = sp.getXMLReader();

		// 4、在解析xml文档之前，设置好事件处理器
		BeanListHandler handler=new BeanListHandler();
		reader.setContentHandler(handler);

		// 5.利用reader读取 xml文档
		reader.parse("src/book.xml");
		
		List list=handler.getBooks();

	}

}

//把xml文档中的每一本书封装到一个book对象，并把多个book对象放在一个list集合中返回
class BeanListHandler extends DefaultHandler{

	private List list=new ArrayList();
	private String currentTag;
	private Book book;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		currentTag=qName;
		if("书".equals(qName)){
			book=new Book();
			book.setName(qName);
		}
		
		super.startElement(uri, localName, qName, attributes);
	}

	
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if("书名".equals(currentTag)){
			String name=new String(ch,start,length);
			book.setName(name);
		}
		
		if("作者".equals(currentTag)){
			String author=new String(ch,start,length);
			book.setAuthor(author);
		}
		
		if("售价".equals(currentTag)){
			String price=new String(ch,start,length);
			book.setPrice(price);
		}
		
		super.characters(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if("书".equals(qName)){
			list.add(book);
			book=null;
		}
		
		currentTag=null;
		
		super.endElement(uri, localName, qName);
	}



	public List getBooks() {
		return list;
	}
	
	
	
}
