package trustline.models;

import trustline.util.InvalidMessageException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrustlineFactory {
    
    /* Get rid of statics and use Spring Application Scope, I guess?? How? :( " */
    private static Map<String,Map<String,Trustline>> trustlines  = new HashMap<>();
    private static Set<String> validUserIds =  new HashSet<String>();


    public TrustlineFactory()
    {

    }

    // Lazy initialization
    public static Trustline getTrustline(String userid, String counterparty)
    {
        if ( userid == null || userid.length()==0 || counterparty ==null || counterparty.length()==0) {
            throw new InvalidMessageException("Trustline cannot be created with invalid user ids");
        }
        
        // cant run this user's trustline on this server
        if ( !isConfiguredUser(userid)){
            return null;
        }

        synchronized (TrustlineFactory.class) {
            if (trustlines.get(userid) == null) {

                Map<String, Trustline> map = new HashMap<String, Trustline>();
                trustlines.put(userid, map);
            }

            Map<String,Trustline> map = trustlines.get(userid);
            if ( map.get(counterparty) == null) {
                Trustline t = new Trustline(userid, counterparty);
                map.put(counterparty, t);
            }
        }
        return trustlines.get(userid).get(counterparty);

    }

    public static boolean userExists(String userId)
    {
        if ( userId == null || userId.length()==0) return false;

        // we do lazy initialization so we need to check for configured users too
        return ( trustlines.get(userId) != null || TrustlineFactory.isConfiguredUser(userId));
    }



    private static boolean isConfiguredUser(String userId)
    {
        //TODO: move this from here or abstract out System properties
        if ( validUserIds.isEmpty()) {
            String configuredUserId = System.getProperty("userid");
            if (configuredUserId != null && configuredUserId.length() > 0) {
                validUserIds.add(configuredUserId);
            }
        }
        return validUserIds.contains(userId);

    }


}
