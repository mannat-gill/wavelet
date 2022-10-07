import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    //int num = 0;
    String message = "Hello"; 
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format(message);
        } else if (url.getPath().equals("/addSmiley")) {
            message += " there :) ";
            return String.format(message);
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/addCheckUp")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("Name")) {
                    message += "\n" +parameters[1];
                    return String.format(message);
                }
            }
            return "Not Found!";
        }
    }
}
public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
