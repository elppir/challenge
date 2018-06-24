package trustline.models;

import trustline.util.InvalidMessageException;

public class Transfer implements TrustlineMessage {


    private String id;

    private String from;
    private String to;


    private  int amountPennies;

    public Transfer(String from, String to, int amountPennies) throws InvalidMessageException {

        if ( from == null || to == null || from.length() ==0 || to.length() == 0 )
            throw new InvalidMessageException("'from' or 'to' fields cant be null or empty");

        this.from=from;
        this.to=to;
        this.amountPennies=amountPennies;

        long now = System.nanoTime();
        id=from+to+amountPennies+String.valueOf(now); //TODO a better hashing scheme here

    }

    public  String getFrom()
    {
        return this.from;
    }

    public String getTo()
    {
        return this.to;
    }

    public int getAmountPennies()
    {
        return this.amountPennies;
    }

    public String getId()
    {
        return this.id;
    }

    @Override
    public String toString()
    {
        return "id="+id+ "&from=" +from+"&to="+to+"&amountPennies="+String.valueOf(amountPennies);
    }

    public Transfer reverse()
    {
        return new Transfer(to,from,amountPennies);
    }
}

