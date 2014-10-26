package com.llq.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Demo1 {

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
		reader.setContentHandler(new MyContentHandler2());

		// 5.利用reader读取 xml文档
		reader.parse("src/book.xml");

	}

}

// 得到xml文档内容的事件处理器
class MyContentHandler implements ContentHandler {

	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {

		System.out.println("当前解析到了:" + name + ",这个标签是开始标签");
		for (int i = 0; i < atts.getLength(); i++) {
			String attname = atts.getQName(i);
			String attvalue = atts.getValue(i);

			System.out.println(attname + "=" + attvalue);
		}

	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {

		System.out.println("当前解析到了:" + name + ",这个标签是结束标签");

	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		System.out.println("当前解析到了内容：" + new String(ch, start, length));
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}

//用于获取第一个售价节点的值：<售价>109</售价>
class MyContentHandler2 extends DefaultHandler{

	private String currentTag;//记住当前解析的是什么标签
	private int needNumber=2;//记住想获取第几个售价标签的值
	private int currentNumber;//当前解析到的是第几个
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if("售价".equals(currentTag) && currentNumber==needNumber){
			System.out.println(new String(ch,start,length));
		}
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		currentTag=name;
		if("售价".equals(currentTag)){
			currentNumber++;
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		currentTag=null;
	}
}
