package no.hist.tdat.kontrollere;

import no.hist.tdat.javabeans.Emne;
import no.hist.tdat.javabeans.beanservice.EmneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eirik on 20.01.14.
 */

@Controller
public class OpprettEmneKontroller {
    @Autowired
    EmneService service;
    @RequestMapping("/lagEmne.htm")
    public String emne(@ModelAttribute(value = "emne") Emne emne, BindingResult result, Model model) {
        try{
            service.opprettEmne(emne);
            model.addAttribute("emnerett", "Emne \""+emne.getEmneNavn()+"\" med emnekode \""+emne.getEmneKode()+"\" er opprettet");
            return "opprettDelemne";
        }catch(org.springframework.dao.DuplicateKeyException e){
            model.addAttribute("emneSQLfeil", "Emnenavn eller emnekode er opprettet fra f&oslash;r");
            return "opprettEmne";
        }
    }
}