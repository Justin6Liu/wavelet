import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;


class SearchEngineHandler implements URLHandler {
    
    ArrayList<String> listOfStrings = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Welcome to search engine.";
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (true) {
                    listOfStrings.add(parameters[1]);
                    return String.format("%s has been added to the list.", parameters[1]);
                }
            } else if (url.getPath().contains("search")) {
                String[] parameters = url.getQuery().split("=");
                String outputInString = "No results found";
                if (true) {
                    String toLookFor = parameters[1];
                    ArrayList<String> output = new ArrayList<>();
                    for (int i = 0; i < (listOfStrings.size()); i++){
                        if (listOfStrings.get(i).contains(toLookFor)){
                            output.add(listOfStrings.get(i));
                        }

                    }
                    if (output.size() != 0) {
                        outputInString = "The results are: \n";
                        for (int j=0; j<output.size(); j++) {
                            outputInString += output.get(j);
                            outputInString += "\n";
                        }
                    }
                    return outputInString;
                }
            }
            return "404 Not Found!";
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

        Server.start(port, new SearchEngineHandler());
    }
}
