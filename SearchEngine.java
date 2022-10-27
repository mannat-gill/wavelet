import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    String message = "Hello";
    String nameOutput= ""; 
    List<String> names = new ArrayList<String>();
    public String handleRequest(URI url) {
        
        if (url.getPath().equals("/")) {
            return String.format(message);
        } else if (url.getPath().equals("/addSmiley")) {
            message += " there :) ";
            return String.format(message);
        } else if(url.getPath().equals("/remove")) {
            if(message.length()>5){
                message = message.substring(0, 5); 
            }
            return String.format(message);
        }
        else {
            
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/addCheckup")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("Name")) {
                    names.add(parameters[1]);
                    return "Added: "+ parameters[1];
                }
                else if(parameters[0].equals("printFriends")){
                    String outputFriends = ""; 
                    for(String x: names){
                        if(x.contains(parameters[1])){
                            outputFriends += x + " "; 
                        }
                    }
                    return outputFriends; 
                }
            }
            

            return "Not Found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}
