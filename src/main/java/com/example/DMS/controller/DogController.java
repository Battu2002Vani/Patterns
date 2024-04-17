package com.example.DMS.controller;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.expression.Mvc;

import com.example.DMS.Models.Dog;
import com.example.DMS.Models.Trainer;
import com.example.DMS.repository.DogRepository;
import com.example.DMS.repository.TrainerRepository;

import ch.qos.logback.core.model.Model;

@Controller
public class DogController {
	ModelAndView moviw = new ModelAndView();
	@Autowired
	DogRepository dogRepo;
	@Autowired
	TrainerRepository trainerRepo;

//	@RequestMapping("dogHome")
//	public String home() {
//		return "home";
//	}
	@RequestMapping("dogHome")
	public ModelAndView home() {
		moviw.setViewName("home");
		return moviw;
	}

	@RequestMapping("add")
	public ModelAndView add() {
		moviw.setViewName("addNewDog.html");
		Iterable<Trainer> trainerList= trainerRepo.findAll();
		moviw.addObject("trainers", trainerList);
		return moviw;
	}

	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog,@RequestParam("trainerId") int id) {
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());
		dog.setTrainer(trainer);
		dogRepo.save(dog);
		moviw.setViewName("home");
		return moviw;
	}

	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {
		moviw.setViewName("addNewTrainer");
		return moviw;
	}

	@RequestMapping("trainerAdded")
	public ModelAndView addNewTrainer(Trainer trainer) {
		trainerRepo.save(trainer);
		moviw.setViewName("home");
		return moviw;
	}

	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		moviw.setViewName("viewDogs");
		Iterable<Dog> dogList = dogRepo.findAll();
		moviw.addObject("dogs", dogList);
		return moviw;

	}

	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog) {
		dogRepo.save(dog);
		moviw.setViewName("editDog");
		return moviw;
	}

	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog) {
		// based on Id
		// Optional <Dog> dogFound= dogRepo.findById(dog.getId());
		// if(dogFound.isPresent()) {
		// dogRepo.delete(dog);

		// return home();

		// based on name
//	List<Dog> dogsFound =dogRepo.findByName(dog.getName());
//		for(Dog d : dogsFound) {
//			dogRepo.delete(d);
//		}
//		return home();
//		
//	
		Dog d = dogRepo.findById(dog.getId()).orElse(new Dog());
		dogRepo.delete(d);
		return home();
	}

	@RequestMapping("search")
	public ModelAndView searchById(int id) {
		Dog dogFound = dogRepo.findById(id).orElse(new Dog());
		moviw.addObject(dogFound);
		moviw.setViewName("searchResults");
		return moviw;

	}
}
