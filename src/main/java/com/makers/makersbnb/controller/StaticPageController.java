package com.makers.makersbnb.controller;

import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StaticPageController {
//    @GetMapping("/")
//    public String landingPage() {
//        return "Welcome to Maker's BnB";
//    }

    @GetMapping("/")
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView("/LandingPage");
        Integer nSpaces = 100;
        modelAndView.addObject("nSpaces", nSpaces);
        // imagine these reviews were stored in a database
        String[] reviews = new String[] {"Awesome", "Nice", "Perfect"};
        modelAndView.addObject("reviews", reviews);
        return modelAndView;
    }

    @GetMapping("/contactus")
    public ModelAndView contactusPage() {
        return new ModelAndView("/ContactUs");
    }

    @GetMapping("/termsandconditions")
    public ModelAndView termsAndConditionsPage() {
        return new ModelAndView("/TermsAndConditions");
    }

    @GetMapping("/team")
    public String[] StaffController() {
        String[] team = new String[3];
        team[0] = "Toby";
        team[1] = "Katerina";
        team[2] = "Sandy";
        return team;
    }

    // Injecting the repository to interact with the database
    @Autowired
    private SpaceRepository spaceRepository;

    @GetMapping("/spaces")
    public ModelAndView listSpaces() {
        List<Space> spaces = (List<Space>) spaceRepository.findAll();
        System.out.println(spaces);
        ModelAndView modelAndView = new ModelAndView("/spaces/Spaces");
        modelAndView.addObject("spaces", spaces);
//        return ((List<Space>) spaceRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/spaces/new")
    public ModelAndView newSpaceForm() {
        Space space = new Space();
        ModelAndView newSpaceForm = new ModelAndView("/spaces/NewSpace");
        newSpaceForm.addObject("space",space);
        return newSpaceForm;
    }

    @PostMapping("/spaces")
    public RedirectView create(Space space) {
        spaceRepository.save(space);
        return new RedirectView("/spaces");
    }

}
