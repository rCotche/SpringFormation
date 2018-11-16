/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringMVCnetbeans.Controller;

import Repository.CompteRepository;
import com.example.SpringMVCnetbeans.Entity.Compte;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 *
 * @author rcotche.cdi
 */
@Controller
@RequestMapping(path = "/demo")
@Service
public class GestionController {

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String sample() {
        return "Nouvellepage";
    }

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ompteRepository compteRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET) // Map ONLY GET Requests
    public @ResponseBody ModelAndView addNewUser(@RequestParam String login,
            @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            Compte n = new Compte();
            n.setLogin(login);
            n.setPassword(encryptThisString(password));
            compteRepository.save(n);
        } catch (Exception e) {
            System.out.println("erreur" + e);
        }
        return new ModelAndView("redirect:" + "sample");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    Compte getCompte(@RequestParam String password, @RequestParam String login) {
        /*if(compteRepository.existsById(id)){
            System.out.println("ui");
        }
        return compteRepository.findOneById(id);*/
        return compteRepository.findOneByPasswordAndLogin(password, login);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET) // Map ONLY GET Requests
    public @ResponseBody
    void deleteUser(@RequestParam Long id) {
        compteRepository.deleteById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET) // Map ONLY GET Requests
    public @ResponseBody void updateUser(@RequestParam String login, @RequestParam String password, @RequestParam Long id) {
        if(compteRepository.existsById(id)){
            Compte unCompte = new Compte(id);
            unCompte.setLogin(login);
            unCompte.setPassword(encryptThisString(password));
            compteRepository.save(unCompte);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Compte> getAllUsers() {
        // This returns a JSON or XML with the users
        return compteRepository.findAll();
    } //To change body of generated methods, choose Tools | Templates.
    
    public static String encryptThisString(String password) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
            byte[] messageDigest = md.digest(password.getBytes()); 
 
            BigInteger no = new BigInteger(1, messageDigest);  
            String hashtext = no.toString(16); 
 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
 
            return hashtext; 
        } 
 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
