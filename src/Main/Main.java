/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 *
 * @author guerra
 */
public class Main {
    final String appID = "1679649692066290";
    final String appSecret = "37f5ed9d9357dff29314217f82fc3228";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccessToken at = new AccessToken();
        
        User user = facebookClient.fetchObject("me", User.class);
        Page page = facebookClient.fetchObject("cocacola", Page.class,
                Parameter.with("fields", "fan_count"));

        out.println("User name: " + user.getName());
        out.println("Page likes: " + page.getFanCount());
    }

}
