/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lixo;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import java.util.Date;

/**
 *
 * @author guerra
 */
public class LoggedInFacebookClient extends DefaultFacebookClient {
    private final String appID = "1679649692066290";
    private final String appSecret = "37f5ed9d9357dff29314217f82fc3228";
    
    private AccessToken accessToken;
    
    public LoggedInFacebookClient() {
       this.accessToken = this.obtainAppAccessToken(appID, appSecret);
    }

    public String getStringToken(){
        return this.accessToken.getAccessToken();
    }
    
    public AccessToken getAccessToken() {
        return accessToken;
    }
    
    public Date getExpires(){
        return accessToken.getExpires();
    }
    
   
}
