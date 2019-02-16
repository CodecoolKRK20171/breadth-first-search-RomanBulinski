package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import javax.xml.soap.Node;
import java.util.*;

import static com.codecool.bfsexample.BFSExample.users;

public class Search {


//    private HashMap< UserNode> nodeLookup = new HashMap<>();

    public void addEdge (String firstname1,String lastname1, String firstname2, String lastname2){
        UserNode a = getUserNode(firstname1,lastname1);
        UserNode b = getUserNode(firstname2,lastname2);
        a.addFriend(b);
    }


    public boolean hasPathBFS(String firstname1,String lastname1, String firstname2, String lastname2){
        return hasPathBFS( getUserNode(firstname1, lastname1),getUserNode(firstname2, lastname2));
    }

    private boolean hasPathBFS(UserNode startUser, UserNode endUser){

        LinkedList<UserNode> nextToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        nextToVisit.add(startUser);

        while(!nextToVisit.isEmpty()){
            UserNode userNode = nextToVisit.remove();
            if(userNode == endUser){
                return  true;
            }
            if(visited.contains( userNode.getId() )){
                continue;
            }
            visited.add( userNode.getId() );
            for(UserNode friend : userNode.getFriends() ){
                nextToVisit.add(friend);
            }
        }
        return false;
    }

    public String getPathBFS(String firstname1,String lastname1, String firstname2, String lastname2){
        String temp = "";
        if(  hasPathBFS( firstname1, lastname1, firstname2,  lastname2) ){
            temp =  getPathBFS( getUserNode(firstname1, lastname1),getUserNode(firstname2, lastname2));
        }
        return temp;
    }

    private String getPathBFS(UserNode startUser, UserNode endUser){

        String result = "";
        List<Integer> pathOfId = new ArrayList<>();
        LinkedList<UserNode> nextToVisit = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();

        int[] distances = new int[users.size()+1];
        Arrays.fill(distances, -1);
        distances[ startUser.getId() ] = 0;

        int[] prev = new int[users.size()+1];
        Arrays.fill(prev, -1);

        nextToVisit.add(startUser);
        while(!nextToVisit.isEmpty()){
            UserNode userNode = nextToVisit.remove();
            if(userNode == endUser){
                break;
            }
            if(visited.contains( userNode.getId() )){
                continue;
            }
            visited.add( userNode.getId() );
            for(UserNode friend : userNode.getFriends() ){
                if( distances[friend.getId()] == -1){
                    distances[friend.getId()] = distances[userNode.getId()] + 1;
                    nextToVisit.add(friend);
                }
                if( prev[friend.getId()] == -1){
                    prev[friend.getId()] = userNode.getId();
                }
            }
        }
        pathOfId.add( endUser.getId());
        int prevManID = endUser.getId();
        while( prevManID != startUser.getId() ) {
            prevManID = prev[prevManID];
            pathOfId.add(prevManID);;
        }
        for( int i =0; i<pathOfId.size(); i++){
            int index = pathOfId.get(i);
            result = result + distances[index]+" "+ getUserById(index)+"\n";
        }
        return result;
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

    public long getIdOfUeserNode(UserNode userNode) {
        return userNode.getId();
    }

    public long getIdOf(String firstName,String lastName) {
        Integer id = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstName().equals(firstName) && users.get(i).getLastName().equals(lastName)) {
                id = users.get(i).getId();
                break;
            }
        }
        return id;
    }

    public String getUserById(int id){
        String result = "";
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId()==id ){
                result = result +id+" "+ users.get(i).getFirstName() +" "+users.get(i).getLastName();
            }
        }
        return result;
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
            result = result+friend.getId()+" "+friend.getFirstName()+" "+friend.getLastName()+"\n";
        }
        return result;
    }


    public String pathListToString(LinkedList<UserNode> pathList) {
        String result = "";
        Iterator<UserNode> iteratorAbowepathList = pathList.iterator();
        while(iteratorAbowepathList.hasNext()) {
            UserNode friend = iteratorAbowepathList.next();
            result = result  +friend.getFirstName()+" "+friend.getLastName()+"\n";
        }
        return result;
    }



}
