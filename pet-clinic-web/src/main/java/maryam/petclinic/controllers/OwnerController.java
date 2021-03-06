package maryam.petclinic.controllers;

import maryam.petclinic.model.Owner;
import maryam.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/index", "/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
    @RequestMapping({"/find"})
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("/owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping({"","/"})
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
        if(owner.getLastname()==null){
            owner.setLastname("");
        }
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastname()+"%");

        if(results.isEmpty()){
            //no owners
            bindingResult.rejectValue("lastname", "notFound", "not found");
            return "owners/findOwners";
        }else if(results.size()==1){
            owner = results.iterator().next();
            return "redirect:/owners/"+owner.getId();
        }else{
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

}
