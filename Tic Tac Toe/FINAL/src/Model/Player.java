package Model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {

    private String name ;
    private int numOfGames ;
    private int numOfWins ;
    private int numOfLoses ;
    private int numOfDraws;
    private int score;
    private String[] BestGames;
    private int  NumOfBestGames;
    /*

     * if typeOfPlayer == 0 then it is a human player
     *                 == 1 then it is a perfect player (Hal)
     *                 == -1 random player (Mr.Bean)

     */
    private int typeOfPlayer ;


    public Player(String name) {
        this.name = name ;
        numOfGames = 0 ;
        numOfLoses = 0 ;
        numOfWins = 0 ;
        numOfDraws=0;
        this.score=0;
        this.BestGames=new String[10];
        this.NumOfBestGames=0;
        this.typeOfPlayer =0 ;
    }
    public Player () {
        numOfLoses = 0 ;
        numOfWins = 0 ;
    }

    public Player (String name, int type) {
        this.name = name ;
        numOfGames = 0 ;
        numOfLoses = 0 ;
        numOfWins = 0 ;
        numOfDraws=0;
        this.score=0;
        this.BestGames=new String[10];
        this.NumOfBestGames=0;
        this.typeOfPlayer = type ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void setNumOfWins(int numOfWins) {
        this.numOfWins = numOfWins;
    }

    public int getNumOfLoses() {
        return numOfLoses;
    }

    public int getNumOfDraws() {
        return numOfDraws;
    }

    public void setNumOfDraws(int numOfDraws) {
        this.numOfDraws = numOfDraws;
    }
    public void AddGamesToPlayer(){
        numOfGames++;
    }

    public void AddVictoryToPlayer(){
        numOfWins++;
    }
    public void AddLoseToPlayer(){
        numOfLoses++;
    }
    public void AddDrawToPlayer(){
        numOfDraws++;
    }

    public void setNumOfLoses(int numOfLoses) {
        this.numOfLoses = numOfLoses;
    }
    public int getNumOfGames() {
        return numOfGames;
    }
    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public String getPlayerStatus () {

        return ("\n\n\n" + "Games Played :" + this.numOfGames
                + "\nWins :" + this.numOfWins
                + "\nLoses :" + this.numOfLoses
                + "\nDraws :" + (this.numOfGames - (this.numOfWins+this.numOfLoses)))+ "\n\n ";
    }

    public String GetPlayerBestGames(int i){



        return getBestGames()[i];


    }



    public int  PlayerScore(){


        if (numOfGames!= 0)
            this.score = 50 * (2*numOfWins + numOfDraws)
                    / (numOfGames);

        else {
            this.score=0;

        }


        return this.score;

    }


    public int  GetScore(){

        return PlayerScore();
    }

    public void AddBestGames(String Game){
        this.BestGames[NumOfBestGames]=Game;
        NumOfBestGames++;
    }

    public String[] getBestGames() {
        return BestGames;
    }

    public void setBestGames(String[] bestGames) {
        BestGames = bestGames;
    }

    public int getNumOfBestGames() {
        return NumOfBestGames;
    }

    @Override
    public String toString(){
        return "   "+this.name+": "+ this.GetScore() +"\n\n";
    }


    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.GetScore(), o.GetScore());
    }


    public int getTypeOfPlayer() {
        return typeOfPlayer;
    }


    public void setTypeOfPlayer(int typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
    }



}