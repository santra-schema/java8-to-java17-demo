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

import com.example.demo.model.Book;

@RestController
@RequestMapping("/v1")
public class DemoController {
	
	@GetMapping("/ping")
	@ResponseBody
	public ResponseEntity<String> test() {
		java8Jaxb();
		java8StringFormat();
		
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
}
