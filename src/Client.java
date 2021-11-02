public class Client implements Comparable<Client> {

    private String FirstName;

    private String LastName;

    public Client(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public int compareTo(Client client) {
        int lnCmp = LastName.compareTo(client.LastName);
        int fnCmp = FirstName.compareTo(client.FirstName);
        if (lnCmp != 0) {
            return lnCmp;
        }
        else if (fnCmp != 0) {
            return fnCmp;
        }
        else {
            return 0;
        }
    }
}


