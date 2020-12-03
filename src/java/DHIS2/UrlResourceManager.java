package DHIS2;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLException;
import org.apache.xml.security.utils.Base64;

/**
 *
 * @author smomoh
 */
public class UrlResourceManager implements Serializable
{
    public static List getUrlResource(String urlString,String userName,String password) 
    {
        
        List resultList=new ArrayList();
        URL url;
        try 
        {
            url = new URL(urlString);
                URLConnection uc;
             uc =url.openConnection();
            uc.setRequestProperty("X-Requested-With", "Curl");
            String userpass = userName+ ":" + password;
            String basicAuth = "Basic " + new String(Base64.encode(userpass.getBytes()));
            if(userName !=null && password !=null)
            uc.setRequestProperty("Authorization", basicAuth);
            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) 
            {
                //System.err.println(line);
                resultList.add(line);
            }
        }
        catch (UnknownHostException unex) 
        {
                System.err.println("Unable to connect: "+unex.getMessage());
        }
        catch (ProtocolException pex) 
        {
                System.err.println("Too many redirects, Unable to pull data: "+pex.getMessage());
        }//
        catch (SocketException sex) 
        {
                System.err.println("Unable to connect: "+sex.getMessage());
        }
        catch (SSLException sslex) 
        {
                System.err.println("SSL Handshake error: "+sslex.getMessage());
        }
        catch (IOException e) 
        {
                e.printStackTrace();
        }
        catch (Exception e) 
        {
               System.err.println("Error: "+e.getMessage());
        }
        return resultList;
    }
    
}