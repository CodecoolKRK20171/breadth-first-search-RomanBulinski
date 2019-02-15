package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import javax.xml.soap.Node;
import java.util.*;

import static com.codecool.bfsexample.BFSExample.users;

public class Search {

//    private HashMap< UserNode> nodeLookup = new HashMap<>();

//    public void addEdge (String source, String destination){
//        UserNode a = getUserNode(source);
//        UserNode b = getUserNode(destination);
//        a.addFriend(b);
//    }


    public boolean hasPathBFS(String firstname1,String lastname1, String firstname2, String lastname2){


        return hasPathBFS( getUserNode(firstname1, lastname1),getUserNode(firstname2, lastname2));
    }

    private boolean hasPathBFS(UserNode source, UserNode destination){

        LinkedList<UserNode> nextToVisit = new LinkedList<>();
        HashSet<Long> visited = new HashSet<>();
        nextToVisit.add(source);

        while(!nextToVisit.isEmpty()){
            UserNode userNode = nextToVisit.remove();
            if(userNode == destination){
                return  true;
            }
            if(visited.contains(source.getId())){
                continue;
            }
            visited.add(source.getId());
            for(UserNode child : userNode.getFriends()){
                nextToVisit.add(child);
            }
        }
        return false;
    }

    private UserNode getUserNode(String firstName,String lastName){
        UserNode userNode= null;
        for(int i=0; i < users.size(); i++ ){

            if( users.get(i).getFirstName().equals(firstName) && users.get(i).getLastName().equals( lastName) ){
                userNode = users.get(i);
                break;
            }
        }
        return userNode;
    }


    public Set<UserNode> getFriendsOfman(String firstName,String lastName) {
        UserNode userNode= null;
        for(int i=0; i < users.size(); i++ ){
            if( users.get(i).getFirstName().equals(firstName) && users.get(i).getLastName().equals( lastName) ){
                userNode = users.get(i);
                break;
            }
        }
        return userNode.getFriends();
    }

    public String friendsToString(Set<UserNode> friends) {
        String result = "";
        Iterator<UserNode> iteratorAboweFriends = friends.iterator();
        while(iteratorAboweFriends.hasNext()) {
            UserNode friend = iteratorAboweFriends.next();
            result = result  +friend.getFirstName()+" "+friend.getLastName()+"\n";
        }
        return result;
    }
}
