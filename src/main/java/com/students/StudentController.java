package com.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

	@Autowired
	StudentRepository repository;

	@Autowired
	ClassesRepository classRepository;

	@RequestMapping("/student/{id}")
	public String developer(@PathVariable Long id, Model model) {
        model.addAttribute("student", repository.findOne(id));
        model.addAttribute("classes", classRepository.findAll());
        return "student";
	}

    @RequestMapping(value="/students",method=RequestMethod.GET)
	public String studentsList(Model model) {
        model.addAttribute("students", repository.findAll());
        return "students";
	}

    @RequestMapping(value="/students",method=RequestMethod.POST)
	public String studentsAdd(@RequestParam String email, 
						@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, Model model) {
        Student newStudent = new Student();
        newStudent.setEmail(email);
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setAddress(address);
        repository.save(newStudent);

        model.addAttribute("student", newStudent);
        model.addAttribute("classes", classRepository.findAll());
        return "redirect:/student/" + newStudent.getId();
	}

    @RequestMapping(value="/student/{id}/classes", method=RequestMethod.POST)
	public String studentsAddClass(@PathVariable Long id, @RequestParam Long classId, Model model) {
    	Class newClass = classRepository.findOne(classId);
    	Student student = repository.findOne(id);

    	if (student != null) {
    		if (!student.hasClass(newClass)) {
    			student.getClasses().add(newClass);
    		}
    		repository.save(student);
            model.addAttribute("student", repository.findOne(id));
            model.addAttribute("classes", classRepository.findAll());
            return "redirect:/student/" + student.getId();
    	}

        model.addAttribute("students", repository.findAll());
        return "redirect:/students";
    }

}