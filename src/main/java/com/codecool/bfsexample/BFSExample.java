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
                    System.out.println( "Friend of : Madeline Cedric " + "id : "+search.getIdOf("Madeline","Cedric") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Madeline","Cedric") ));
                    System.out.println( "Friend of : Emery Tyler "+ "id : "+search.getIdOf("Emery","Tyler") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Emery","Tyler") ));
                    System.out.println( "Friend of : Virginia Abra "+ "id : "+search.getIdOf("Virginia","Abra") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Virginia","Abra") ));
                    System.out.println( "Friend of : Naomi Rajah "+ "id : "+search.getIdOf("Naomi","Rajah") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Naomi","Rajah") ));
                    System.out.println( "Friend of : Tanya Abra "+ "id : "+search.getIdOf("Tanya","Abra") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Tanya","Abra") ));
                    System.out.println("----------------");

                    System.out.println( "Friend of : Jena Bryar "+ "id : "+search.getIdOf("Jena","Bryar") );
                    System.out.println(search.friendsToString( search.getFriendsOfman("Jena","Bryar") ));

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
                    System.out.println(  search.getPathBFS("Belle", "Aurora","Axel","Tyler") );
                    System.out.println(  search.getPathBFS("Madeline", "Cedric","Tanya","Abra") );
                    break;
            }
        }
    }

    public static void main(String[] args) {
        BFSExample mainGO = new BFSExample();
        mainGO.controler();
    }
}
