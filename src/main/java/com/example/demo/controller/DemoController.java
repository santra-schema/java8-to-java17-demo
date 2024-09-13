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
		java8ThreadStop();
		
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


	private void java8ThreadStop() {
		Thread thread = new Thread(() -> {
            try {
                while (true) {
                    // Simulate some work
                    Thread.sleep(1000);
                    System.out.println("Thread is running");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        });

        thread.start();

        try {
            // Let the thread run for a while
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.stop(); // This is deprecated
        System.out.println("Thread stopped");
    }
}
