package Model;

public class PlayersCatalogue {
    private Player[] players;
    private  int numOfPlayers =4;

    public PlayersCatalogue() {
        players = new Player[20];
        players[0]=new Player("George");
        players[1]=new Player("Michael");
        players[2]=new Player("Mr.Bean", -1);
        players[3]=new Player("Hal", 1);

    }


    public Player getPlayer (int i ) {
        if (i < numOfPlayers && i >=0)
            return players[i] ;
        else
            return null ;
    }

    public Player[] GetPlayers() {
        return players;
    }

    public String[] getPlayersNames () {
        String[] listOfPlayersNames = new String [numOfPlayers]   ;
        for (int i = 0 ; i < this.numOfPlayers ; i++ ) {
            listOfPlayersNames[i] = players[i].getName() ;
        }
        return listOfPlayersNames ;



    }

    public Player findPlayerByName (String player) {
        for (Player p : players ) {
            if (p != null && p.getName().equals(player))
                return p ;

        }
        return null ;
    }

    public void addPlayer (String name ) {

        if (numOfPlayers < 20) {
            this.players[numOfPlayers] =new Player(name);
            numOfPlayers++ ;

        }

    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}