package com.bandiClasses.DogManagementSystem.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bandiClasses.DogManagementSystem.Model.Dog;
import com.bandiClasses.DogManagementSystem.Model.Trainer;
import com.bandiClasses.DogManagementSystem.repository.DogRepository;
import com.bandiClasses.DogManagementSystem.repository.TrainerRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


/**
 * 
 * @author S555058
 *
 */
@Controller
public class DogController {

	ModelAndView mv = new ModelAndView();
	@Autowired
	DogRepository dogRepo; 
	@Autowired
	TrainerRepository trainerRepo;
//	@RequestMapping("dogHome")
//	public String home()
//	{
//		return "home";
//		
//	}
	@RequestMapping("dogHome")
	public ModelAndView home()
	{
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("add")
	public ModelAndView add()
	{
		mv.setViewName("addNewDog.html");
		Iterable<Trainer> trainerList = trainerRepo.findAll();
		mv.addObject("trainers", trainerList);
		return mv;
	}
	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog,@RequestParam("trainerId")int id)
	{
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());
		dog.setTrainer(trainer);
		
		dogRepo.save(dog);
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("addTrainer")
	public ModelAndView addTrainer()
	{
		mv.setViewName("addNewTrainer");
		return mv;
		
	}
	@RequestMapping("trainerAdded")
	public ModelAndView addNewTrainer(Trainer trainer)
	{
		trainerRepo.save(trainer);
		mv.setViewName("home");
		return mv;
		
	}
	
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs()
	{
		
		mv.setViewName("viewDogs");
		Iterable<Dog> dogList =dogRepo.findAll();
		mv.addObject("dogs", dogList);
		return mv;
	}
	
	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog)
	{
		dogRepo.save(dog);
		mv.setViewName("editDog");
		//Iterable<Dog> dogList =dogRepo.findAll();
		//mv.addObject("dogs", dogList);
		return mv;
	}
	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog)
	{
//		//based on id
//		java.util.Optional<Dog> dogFound = dogRepo.findById(dog.getId());
//		if(dogFound.isPresent())
//		{
//			dogRepo.delete(dog);
//		}
//		return home();
		// based on the name
//		List<Dog> dogFound = dogRepo.findByName(dog.getName());
//		for(Dog d : dogFound)
//		{
//			
//			dogRepo.delete(d);
//		}
//		return home();
		
		Dog d = dogRepo.findById(dog.getId()).orElse(new Dog());
		dogRepo.delete(d);
		return home();
	}
	@RequestMapping("search")
	public ModelAndView searchById(int id)
	{
		Dog d = dogRepo.findById(id).orElse(new Dog());
		mv.addObject(d);
		mv.setViewName("searchResults");
		return mv;
		
	}
}
