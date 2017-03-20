package com.students;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//public class Application implements CommandLineRunner {
public class Application extends SpringBootServletInitializer implements CommandLineRunner  {
    @Autowired
    StudentRepository repository;

    @Autowired
    ClassesRepository classesRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
        return application.sources(Application.class);
    }
    @Override
    public void run(String... args) throws Exception {
    	Class javascript = new Class("Java ", "Javascript language for beginners");
    	Class ruby = new Class("ruby", "Ruby language and more");
    	Class emberjs = new Class("Spring", "Spring framework");
    	Class angularjs = new Class("Angularjs", "Angularjs framework");

    	classesRepository.save(javascript);
    	classesRepository.save(ruby);
    	classesRepository.save(emberjs);
    	classesRepository.save(angularjs);

    	List<Student> students = new LinkedList<Student>();
    	students.add(new Student("Petter", "Nygaard", "petter.nygaard@outlook.com", "Olav M Troviksvei 2, Oslo", "123456",
    			Arrays.asList(new Class[] { javascript, ruby })));
    	students.add(new Student("Raul", "Petersen", "raul@email.com", "Storgatta 16A, Drammen", "password",
    			Arrays.asList(new Class[] { emberjs, ruby })));
    	students.add(new Student("Lasse", "Hallmoy", "lasse@gmail.com",  "Aker Brygge 108, Oslo", "password123",
    			Arrays.asList(new Class[] { angularjs, ruby })));
    	students.add(new Student("Rune", "Hindsgaul", "Runer@gamail.com",  "Drammensvei 14b, Stavanger", "password1",
    			Arrays.asList(new Class[] { emberjs, angularjs, javascript })));
    	students.add(new Student("Stella", "Oskarsdottir", "Stella@mail.com",  "Rosegata 20, Oslo", "password2",
    			Arrays.asList(new Class[] { emberjs })));
    	repository.save(students);
    }
}
