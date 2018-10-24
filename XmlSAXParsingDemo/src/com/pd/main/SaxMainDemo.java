package com.pd.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jboss.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

public class SaxMainDemo {

	public static void main(String[] args) {
		
		
		InputStream in=null;
		String url=null;
		if(args.length==0)
		{
			url="http://localhost:8080/RestServiceDemo1/rest/books";
			System.out.println("using "+url);
		}
		else
		{
			url=args[0];
		}
		DefaultHandler handler=new DefaultHandler()
		{
			@Override
			public void startElement(String ns,String lname,String qname,Attributes atts)
			{
				System.out.println("Element start : "+lname);
				if(lname.equalsIgnoreCase("a")&& atts != null)
				{
					for(int i=0;i<atts.getLength();i++)
					{
						String aname=atts.getLocalName(i);
						if(aname.equalsIgnoreCase("href"))
							System.out.println(atts.getValue(i));
					}//for
				}//if anchor
			}//startElemet
			
			
			@Override
			public void endElement(String ns,String lname,String qname)
			{
				System.out.println("Element ends : "+lname);
			}
		};
		
		SAXParserFactory factory=SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
		SAXParser parser=factory.newSAXParser();
		
		in=new URL(url).openStream();
		//in=new FileInputStream("new.html");
		
		parser.parse(in, handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			//in.close();
		}
		
		
		
		
	} 
}
