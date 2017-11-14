/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.Connection;
import java.util.List;

/**
 *
 * @author guerra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final String accessToken = "1679649692066290|37f5ed9d9357dff29314217f82fc3228";

        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_6);

        try {
            Connection<JsonObject> currentPage = facebookClient.fetchConnection("cocacola/likes", JsonObject.class);
            System.out.println(currentPage);
            System.out.println("---");
            System.out.println(currentPage.getData());
            while (true) {
                for (JsonObject obj : currentPage.getData()) {
                    System.out.println(obj);//já vão ser um vértico no grafo
                }
                if (!currentPage.hasNext()) {
                    break;
                }
                currentPage = facebookClient.fetchConnectionPage(currentPage.getNextPageUrl(), JsonObject.class);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*for (List<JsonObject> job : currentPage

    
        ) {
            for (JsonObject obj : job) {
            System.out.println(obj);
        }
    }*/
}
