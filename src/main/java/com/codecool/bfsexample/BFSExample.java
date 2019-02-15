package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BFSExample {

    public static List<UserNode> users;

    public static void populateDB(EntityManager em) {

        RandomDataGenerator generator = new RandomDataGenerator();
//        List<UserNode> users = generator.generate();
        users = generator.generate();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (UserNode user : users) {
            em.persist(user);
        }
        transaction.commit();
        GraphPlotter.plot(users);
        System.out.println("Done!");
    }

    public void controler (){
        Search search = new Search();
        Scanner input = new Scanner(System.in);

        int flag = 0;
        while(flag==0) {
            System.out.println("Choose number 1-4 :");
            int number = input.nextInt();
            switch (number) {

                case 1:
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnit");
                    EntityManager em = emf.createEntityManager();
                    em.clear();
                    populateDB(em);
                    break;

                case 2:
                    // print friends of user
                    System.out.println(search.friendsToString( search.getFriendsOfman("Madeline","Cedric") ));
                    System.out.println(search.friendsToString( search.getFriendsOfman("Emery","Tyler") ));
                    break;

                case 3:
                    // print all people
                    System.out.println("number of people : "+users.size()+"\n");
                    for(int i= 0 ; i<users.size(); i++ ){
                        System.out.println(users.get(i).getFirstName()+" "+users.get(i).getLastName());
                    }
                    break;

                case 4:
                    // check
                    Boolean result = search.hasPathBFS("Madeline", "Cedric","Emery","Tyler");
                    Boolean result1 = search.hasPathBFS("Madeline", "Cedric","Tyrone","Myles");
                    Boolean result2 = search.hasPathBFS("Madeline", "Cedric","Naomi","Lydia");
                    Boolean result3 = search.hasPathBFS("Madeline", "Cedric","Charissa","Nicholas");
                    Boolean result4 = search.hasPathBFS("Madeline", "Cedric","Theodore","Abra");
                    Boolean result5 = search.hasPathBFS("Emery", "Tyler","Olympia","Aurora");

                    System.out.println(result);
                    System.out.println(result1);
                    System.out.println(result2);
                    System.out.println(result3);
                    System.out.println(result4);
                    System.out.println(result5);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        BFSExample mainGO = new BFSExample();
        mainGO.controler();
    }
}
