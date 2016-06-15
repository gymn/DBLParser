package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import db.Connector;
import model.Document;

public class SAXParserHandler extends DefaultHandler {
	static Connection conn = Connector.getConn();
	static PreparedStatement pstmt = null;
	int docIndex = 0;
	Document doc = null;
	String value = null;
	boolean isEnd = false;
	public static Set<String> types = new HashSet<String>();
	static {
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("INSERT INTO documents("
						+ "doc_type, doc_authors, doc_title, doc_year) VALUES ( ?, ?, ?, ?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		types.add("article");
		types.add("inproceedings");
		types.add("proceedings");
		types.add("book");
		types.add("incollection");
		types.add("phdthesis");
		types.add("mastersthesis");
		types.add("www");
	}
	
	//用来遍历xml的开始标签
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(types.contains(qName)) {
			doc = new Document();
			isEnd = false;
			doc.setDoc_type(qName);
		}
	}
	
	//用来遍历xml的结束标签
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("dblp")){
			try {
				pstmt.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("last batch committed!");
		}else if(types.contains(qName)){
			isEnd = true;
			docIndex++;
			//将Document存入数据库
			try {
				pstmt.setString(1, doc.getDoc_type());
				pstmt.setString(2, doc.getAuthors());
				pstmt.setString(3, doc.getDoc_title());
				pstmt.setInt(4, doc.getDoc_year());
				pstmt.addBatch();
				if (docIndex%50000 == 0){
					System.out.println(docIndex);
					pstmt.executeBatch();
					conn.commit();
				}
			} catch (SQLException e) {
				System.out.println("插入数据时发生错误！");
				e.printStackTrace();
			}
		}else if (qName.equals("author") && isEnd ==false) {
			if (doc.getAuthors().equals("")){
				doc.setAuthors(value);
			}else
				doc.setAuthors(doc.getAuthors()+"|"+value);
		}else if ( (qName.equals("title")||qName.equals("booktitle") ) && isEnd ==false) {
			doc.setDoc_title(value);
		}else if (qName.equals("year") && isEnd ==false) {
			doc.setDoc_year(Integer.parseInt(value));
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value = new String(ch, start, length);
	}
	
	//用来标识解析开始
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("XML解析开始。。。");		
	}
	
	//用来标识解析结束
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("XML解析结束！");
	}
	
}
