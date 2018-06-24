package trustline.models;

import trustline.util.InvalidMessageException;

import java.util.concurrent.atomic.AtomicInteger;

public class Trustline {


    private  AtomicInteger trustlinePennies;
    private  String userId;
    private  String counterparty;


    public Trustline(String userId, String counterparty)
    {
        if ( userId == null || userId.length() == 0 || counterparty == null || counterparty.length()==0)
            throw new InvalidMessageException("Trustline cant be created with invalid user ids");


        this.userId=userId;
        this.counterparty=counterparty;
        this.trustlinePennies = new AtomicInteger(0) ;
    }


    public int addDebt(int value)
    {
        return this.addCredit(value*-1);
    }

    public int addCredit(int value)
    {
        return trustlinePennies.addAndGet(value);
    }

    public String toString()
    {
        return "Trustline balance for " + userId + " with " + counterparty + " :" + String.valueOf(trustlinePennies.get());
    }

    public String getUserId()
    {
        return userId;
    }

    public String getCounterparty()
    {
        return counterparty;
    }


    public int getTrustlinePennies()
    {
        return this.trustlinePennies.get();
    }

}
