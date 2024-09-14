package com.example.demo.controller;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.security.cert.X509Certificate;
import com.example.demo.model.Book;

@RestController
@RequestMapping("/v1")
public class DemoController {
	
	@GetMapping("/ping")
	@ResponseBody
	public ResponseEntity<String> test() {
		java8Jaxb();		//@@@@ JAXB JDK dependency to explicit jakarta.xml.bind
		java8StringFormat();	//@@@@ Format() TO Formatted()
		java8InstanceOf();	//@@@@ obj instanceof String str - singlline pattern matching
		java8Certificate();	//@@@@ javax.security.cert TO java.security.cert
		return new ResponseEntity<>("RUNNING", HttpStatus.OK);
	}
	private void java8Jaxb() {
		try {
            Book book = new Book();
            book.setId(1L);
            book.setName("Java Test");
            book.setAuthor("Sudipta Santra");
            book.setDate(new Date());

            JAXBContext context = JAXBContext.newInstance(Book.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(book, new File("book.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
		
    }
	private void java8StringFormat() {
	String name = "Santra";
        int age = 30;
        String message = String.format("My name is %s and I am %d years old.", name, age);
        System.out.println(message);
    }
	private void java8InstanceOf() {
		 Object obj = getSomeObject();
	        if (obj instanceof String) {
	            String str = (String) obj;
	            System.out.println("String length: " + str.length());
	        } else if (obj instanceof Integer) {
	            Integer num = (Integer) obj;
	            System.out.println("Integer value: " + num);
	        }
	}
	private static Object getSomeObject() {
        return "Hello, Santra";
    }

	private void java8Certificate() {
	        X509Certificate cert = getCertificate(); 
	        
	        System.out.println("Certificate Subject: " + cert.getSubjectDN());
	        System.out.println("Certificate Issuer: " + cert.getIssuerDN());
	        
	    }

    // Dummy method to simulate certificate retrieval
    public static X509Certificate getCertificate() {
        return null;
    }
}
