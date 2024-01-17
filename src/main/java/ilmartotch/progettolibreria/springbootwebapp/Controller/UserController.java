package ilmartotch.progettolibreria.springbootwebapp.Controller;

import ilmartotch.progettolibreria.springbootwebapp.Model.LoginForm;
import ilmartotch.progettolibreria.springbootwebapp.Model.PersonaForm;
import ilmartotch.progettolibreria.springbootwebapp.Model.User;
import ilmartotch.progettolibreria.springbootwebapp.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    ArrayList<PersonaForm>utenti = new ArrayList<>();

    @GetMapping("/registrazione")
    public String showRegister(PersonaForm personaForm){
        return "registrazioneUtente";
    }

    @PostMapping("/postRegistrazione")
    public String  postRegistrazione(@Valid PersonaForm personaForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registrazioneUtente";
        }
        userRepo.save(new User(personaForm.name,personaForm.surname,personaForm.username,personaForm.password));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        return "loginUtente";
    }

    @PostMapping("/postLogin")
    public String postLogin(LoginForm loginForm) {
        boolean b = false;

        for (PersonaForm pf : utenti){
            if(pf.username.equals(loginForm.username) && pf.password.equals(loginForm.password)){
                b = true;
            }
        }

        if(b){
            return "redirect:/home";
        }else{
            return "loginUtente";
        }
    }

    @GetMapping("/home")
    public String showHome(Model m ) {
        m.addAttribute("libri",BookController.libri); //da sostituire con la parte del DB
        return "home";
    }

}