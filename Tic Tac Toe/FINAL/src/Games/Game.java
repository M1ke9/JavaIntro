package Games;

import java.time.LocalDateTime;

import Model.Player;

public class Game implements Comparable<Game>{


    /*
     * if result ==  0  - > its a tie
     *           ==  1  - > player x won
     *           == -1  - > player o won
     */

    private Player [] gamePlayers ;
    private int result ;
    //private Player  winner ;
    private int score1 , score2 ;
    private LocalDateTime dateAndTime  ;


    public Game () {
        this.result = 0 ;
        gamePlayers = new Player [2] ;
        //winner = new Player ();
        this.score1 = this.score2 = 0 ;

    }


    public void addGamePlayer (Player p, int pos) {
        if (pos < 2 && pos > 0 )
            gamePlayers [ pos ] = p ;
    }


    public Player[] getGamePlayers() {
        return gamePlayers;
    }


    public void setGamePlayers(Player[] gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    /*
       public Player getWinner() {
           return winner;
       }


       public void setWinner(Player winner) {
           this.winner = winner;
       }

     */
    public int getResult() {
        return result;
    }


    public void setResult(int result) {
        this.result = result;
    }


    public int getScore1() {
        return score1;
    }


    public void setScore1(int score1) {
        this.score1 = score1;
    }


    public int getScore2() {
        return score2;
    }


    public void setScore2(int score2) {
        this.score2 = score2;
    }


    @Override
    public int compareTo(Game g) {
        if (Integer.compare(g.getResult(),this.getResult()) != 0) {
            return Integer.compare(g.getResult(),this.getResult()) ;
        }
        else {
            if (Integer.compare(g.getScore2(),this.getScore2())!= 0) {


                if (g.gamePlayers[0].getName().equals(this.gamePlayers[0].getName()))
                    return Integer.compare(g.getScore2(),this.getScore2()) ;

                else if (g.gamePlayers[1].getName().equals(this.gamePlayers[1].getName()))
                    return Integer.compare(g.getScore1(),this.getScore1()) ;

                else if (g.gamePlayers[0].getName().equals(this.gamePlayers[1].getName()))
                    return Integer.compare(g.getScore1(),this.getScore2()) ;
                else
                    return Integer.compare(g.getScore2(),this.getScore1()) ;
            }
            else return g.getDateAndTime().compareTo(this.dateAndTime) ;

        }


    }


    public Object gameStats(String name) {
        if (this.gamePlayers[0].getName().equals(name) ) {
            if (this.result == 1 ) {
                return ( "\n\n Win vs " + this.gamePlayers[1].getName()) ;
            }
            else if (this.result == - 1) {
                return ( "\n\n Lose vs " + this.gamePlayers[1].getName()) ;
            }
            else return ( "\n\n Draw vs " + this.gamePlayers[1].getName()) ;
        }else if (this.gamePlayers[1].getName().equals(name)) {
            if (this.result == 1 ) {
                return ( "\n\n Lose vs " + this.gamePlayers[0].getName()) ;
            }
            else if (this.result == - 1) {
                return ( "\n\n Win vs " + this.gamePlayers[0].getName()) ;
            }
            else return ( "\n\n Draw vs " + this.gamePlayers[0].getName()) ;
        }
        return null ;
    }




    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }


    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


}