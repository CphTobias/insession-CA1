/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.JokeDTO;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Jens
 */
public class JokePopulator {

    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeJoke fj = FacadeJoke.getFacadeJoke(emf);
        fj.create(new JokeDTO("I sold my vacuum the other day. All it was doing was collecting dust", "www.rd.com", "short", 3));
        fj.create(new JokeDTO("I like elephants. Everything else is irrelephant", "www.rd.com", "short", 2));
        fj.create(new JokeDTO("Why did the baby strawberry cry? His parents were in a jam.", "www.fatherly.com", "riddle", 5));
        fj.create(new JokeDTO("If athlete’s get athletes foot what do elves get? Mistletoes", "www.fatherly.com", "riddle", 4));
        fj.create(new JokeDTO("What does a clock do when it’s hungry? It goes back four seconds.", "www.parade.com", "riddle", 2));
        fj.create(new JokeDTO("What’s the No. 1 cause of divorce? Marriage!", "www.parade.com", "riddle", 1));
        fj.create(new JokeDTO("I bought sneakers from a drug dealer. I don’t know what he laced them with but I’ve been tripping all day!", "www.parade.com", "short", 6));
        fj.create(new JokeDTO("Why does Waldo only wear stripes? Because he doesn’t want to be spotted.", "www.parade.com", "riddle", 4));
        fj.create(new JokeDTO("A jumper cable walks into a bar. The bartender says, “I’ll serve you, but don’t start anything.”", "www.parade.com", "short", 4));
       

    }
    public static void main(String[] args) {
        populate();
    }
}
