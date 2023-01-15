package Model;


import Control.GameController;
import View.GameBoard;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;

import Games.Game;

public class GameModel {
    PlayersCatalogue  playerCatalogue;
    Player [] gamePlayers;
    String[][] gameBoard;
    GameController gc;
    ListOfCharts<Player> List;
    ListOfCharts<Game> GameList ;
    ListOfCharts<Game> GameList2 ;
    int PlayerPos;
    double PlayerScore;
    boolean Xvictory;
    boolean Ovictory;
    boolean draw;
    Boolean mover;
    int moves;
    private StringBuilder sb ;
    private LocalDateTime time;
    Game currentGame ;
    private Game[] playerGames;



    public GameModel(GameController gc) {

        this.gc=gc;
        this.currentGame = new Game() ;
        this.sb = new StringBuilder("") ;
        gamePlayers = new Player[2];
        gameBoard=null;
        playerCatalogue= new PlayersCatalogue();
        //mover=false;
        moves=0;
        PlayerPos=getPlayerCatalogue().getNumOfPlayers();
        Xvictory=false;
        Ovictory=false;
        draw=false;
        List=new ListOfCharts<>(10);
        GameList = new ListOfCharts<Game>(30) ;
        GameList2 = new ListOfCharts<Game>(30) ;
        time = LocalDateTime.now() ;
        this.playerGames = new Game [30] ;


    }


    public void LeftPlayerFirstMove(){
        this.mover=true;

    }
    public void RightPlayerFirstMove(){

        this.mover=false;
    }

    public void selectPlayer(Player player, int pos) {
        if (pos<0 && pos>1)return;
        gamePlayers[pos]=player;
    }


    public boolean ready() {
        return (gamePlayers[0] != null && gamePlayers[1] !=null);
    }


    public void startGame() {
        gameBoard= new String[3][3];
    }


    public boolean inPlay() {

        return gameBoard !=null && moves <9;
    }

    public boolean NoPlay() {
        return !inPlay();
    }

    public boolean EndOfGame(){
        if(Xvictory || Ovictory || draw) {
            gc.EndGame();
            return true;
        }
        else
            return false;
    }

    public void GiveDataToPlayers() {

        String player1 = getGamePlayers()[0].getName();
        String player2 = getGamePlayers()[1].getName();

        AddDataToPlayer1(player1);
        AddDataToPlayer2(player2);

    }

    public void HallOfScores() {

        for (int i = 0; i < List.size(); i++) {
            gc.getView().getMainPanel().getHallOfFame().SetPlayerScore(List.get(i).toString());

        }

    }


    public ListOfCharts<Player> getList() {
        return List;
    }


    public int getMover() {
        return mover.compareTo(false);
    }

    public boolean getmover(){

        return mover;
    }




    public Player[] getGamePlayers() {
        return gamePlayers;
    }


    public String[][] getGameBoard() {
        return gameBoard;
    }

    /* Changes for Lab 08 */
    public void checkDimValidity(int row, int col) {
        if (row <0 || col < 0 || row > 2 || col >2) {
            throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
        }
    }


    public void checkMoveValidity(int row, int col) {
        checkDimValidity(row, col);
        if (gameBoard[row][col]!=null) {
            throw new IllegalArgumentException("Non playable cell");


        }



    }


    public String getBoardMark(int row, int col) {
        checkDimValidity(row, col);
        return gameBoard[row][col];
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public PlayersCatalogue getPlayerCatalogue() {
        return playerCatalogue;
    }

    public void setPlayerCatalogue(PlayersCatalogue playerCatalogue) {
        this.playerCatalogue = playerCatalogue;
    }


    public void makeMove(int row, int col) {
        checkMoveValidity(row, col);
        gameBoard[row][col]=getMoverMark();
        mover=!mover;
        moves++;
    }


    public String getMoverMark() {
        return mover? "X": "O";
    }


    public String getPlayerStats(String player) {
        StringBuilder sb = new StringBuilder("");
        sb.append(player).append("\n\n\n");
        sb.append("Total:").append("\t").append(4).append("\n");
        sb.append("Won:").append("\t").append("75%").append("\n");
        sb.append("Lost:").append("\t").append("25%").append("\n");
        return sb.toString();
    }



    public void MrBeanFirstMove() {
        Random r=new Random();

       /* if((this.getGamePlayers()[0].getTypeOfPlayer()==-1 && moves==0 && !getmover()) ||
                this.getGamePlayers()[1].getTypeOfPlayer()==-1 && moves==0 && !getmover())

        */
        if ((this.getGamePlayers()[1].getName().equals("Mr.Bean") && moves==0 && !getmover()) ||
                (this.getGamePlayers()[0].getName().equals("Mr.Bean") && moves==0 && getmover()) ) {

            if (!this.EndOfGame()) {


                int r1 = r.nextInt(3);
                int r2 = r.nextInt(3);


                if (getGameBoard()[r1][r2] == null && !this.EndOfGame()) {
                    this.makeMove(r1, r2);
                    Check();

                }
            }
        }
    }

    public void HalFirstMove(){

        /*
        if(this.getGamePlayers()[0].getTypeOfPlayer()==1 && moves==0 && getmover() ||
                this.getGamePlayers()[1].getTypeOfPlayer()==1 && moves==0 && !getmover())
*/
        if(this.getGamePlayers()[0].getName().equals("Hal") && moves==0 && getmover() )
        {
            if(!this.EndOfGame()){

                Point BestMove = findBestMove0(gameBoard);
                makeMove(BestMove.x, BestMove.y);

            }
        }

        if(this.getGamePlayers()[1].getName().equals("Hal") && moves==0 && !getmover())
        {
            if(!this.EndOfGame())
            {
                Point BestMove=findBestMove0(gameBoard);
                makeMove(BestMove.x,BestMove.y);
            }
        }

    }


    public void AddDataToPlayer1(String PlayerName1) {
        for (int i = 0; i < getPlayerCatalogue().getNumOfPlayers(); i++) {
            if (PlayerName1.equals(getPlayerCatalogue().getPlayer(i).getName())) {
                getPlayerCatalogue().getPlayer(i).AddGamesToPlayer();
                if (Xvictory) {
                    getPlayerCatalogue().getPlayer(i).AddVictoryToPlayer();

                    break;
                }
                else
                if (Ovictory) {
                    getPlayerCatalogue().getPlayer(i).AddLoseToPlayer();
                    break;
                }


                else {
                    getPlayerCatalogue().getPlayer(i).AddDrawToPlayer();
                    break;
                }
            }

        }


    }



    public void AddDataToPlayer2(String PlayerName2) {
        for (int i = 0; i < getPlayerCatalogue().getNumOfPlayers(); i++) {
            if (PlayerName2.equals(getPlayerCatalogue().getPlayer(i).getName())) {
                getPlayerCatalogue().getPlayer(i).AddGamesToPlayer();

                if (Ovictory)
                    getPlayerCatalogue().getPlayer(i).AddVictoryToPlayer();

                else if (Xvictory)
                    getPlayerCatalogue().getPlayer(i).AddLoseToPlayer();
                else
                    getPlayerCatalogue().getPlayer(i).AddDrawToPlayer();


            }

        }

    }




    public void Check(){
        if((gameBoard[0][0]!=null) && (gameBoard[0][1]!=null) && (gameBoard[0][2]!=null))
            if((gameBoard[0][0].equals("X")) && (gameBoard[0][1].equals("X")) && (gameBoard[0][2].equals("X"))) {
                Xwins(0,1,2);

            }
        if((gameBoard[1][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[1][2]!=null))
            if((gameBoard[1][0].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[1][2].equals("X"))) {

                Xwins(3,4,5);
            }
        if((gameBoard[2][0]!=null) && (gameBoard[2][1]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[2][0].equals("X")) && (gameBoard[2][1].equals("X")) && (gameBoard[2][2].equals("X"))) {
                Xwins(6,7,8);
            }
        if((gameBoard[0][0]!=null) && (gameBoard[1][0]!=null) && (gameBoard[2][0]!=null))
            if((gameBoard[0][0].equals("X")) && (gameBoard[1][0].equals("X")) && (gameBoard[2][0].equals("X"))) {
                Xwins(0,3,6);
            }
        if((gameBoard[0][1]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][1]!=null))
            if((gameBoard[0][1].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][1].equals("X"))){
                Xwins(1,4,7);
            }
        if((gameBoard[0][2]!=null) && (gameBoard[1][2]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[0][2].equals("X")) && (gameBoard[1][2].equals("X")) && (gameBoard[2][2].equals("X"))) {
                Xwins(2,5,8);
            }
        if((gameBoard[0][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[0][0].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][2].equals("X"))) {
                Xwins(0,4,8);
            }
        if((gameBoard[0][2]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][0]!=null))
            if((gameBoard[0][2].equals("X")) && (gameBoard[1][1].equals("X")) && (gameBoard[2][0].equals("X"))) {
                Xwins(2,4,6);
            }

        if((gameBoard[0][0]!=null) && (gameBoard[0][1]!=null) && (gameBoard[0][2]!=null))
            if((gameBoard[0][0].equals("O"))&& (gameBoard[0][1].equals("O")) && (gameBoard[0][2].equals("O"))) {
                Owins(0,1,2);
            }
        if((gameBoard[1][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[1][2]!=null))
            if((gameBoard[1][0].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[1][2].equals("O"))) {
                Owins(3,4,5);
            }
        if((gameBoard[2][0]!=null) && (gameBoard[2][1]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[2][0].equals("O")) && (gameBoard[2][1].equals("O")) && (gameBoard[2][2].equals("O"))) {
                Owins(6,7,8);
            }
        if((gameBoard[0][0]!=null) && (gameBoard[1][0]!=null) && (gameBoard[2][0]!=null))
            if((gameBoard[0][0].equals("O")) && (gameBoard[1][0].equals("O")) && (gameBoard[2][0].equals("O"))) {
                Owins(0,3,6);
            }
        if((gameBoard[0][1]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][1]!=null))
            if((gameBoard[0][1].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][1].equals("O"))){
                Owins(1,4,7);
            }
        if((gameBoard[0][2]!=null) && (gameBoard[1][2]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[0][2].equals("O")) && (gameBoard[1][2].equals("O")) && (gameBoard[2][2].equals("O"))) {
                Owins(2,5,8);
            }
        if((gameBoard[0][0]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][2]!=null))
            if((gameBoard[0][0].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][2].equals("O"))) {
                Owins(0,4,8);
            }
        if((gameBoard[0][2]!=null) && (gameBoard[1][1]!=null) && (gameBoard[2][0]!=null))
            if((gameBoard[0][2].equals("O")) && (gameBoard[1][1].equals("O")) && (gameBoard[2][0].equals("O"))) {
                Owins(2,4,6);

            }
        Draw();
    }

    public void  Xwins(int a,int b,int c){
        System.out.println("X wins");
        this.Xvictory=true;

        GiveDataToPlayers();

        this.currentGame.setResult(1);
        this.currentGame.setScore1(this.gamePlayers[0].GetScore());
        this.currentGame.setScore2(this.gamePlayers[1].GetScore());
        this.gc.addGame(currentGame);
        EndOfGame();

    }
    public void Owins(int a,int b,int c){
        System.out.println("O wins");
        this.Ovictory=true;
        GiveDataToPlayers();

        this.currentGame.setResult(-1);
        this.currentGame.setScore1(this.gamePlayers[0].GetScore());
        this.currentGame.setScore2(this.gamePlayers[1].GetScore());
        this.gc.addGame(currentGame);
        EndOfGame();

    }


    public void Draw() {
        if (!Xvictory && !Ovictory && moves == 9) {
            System.out.println("Draw");
            this.draw = true;
            GiveDataToPlayers();

            this.currentGame.setResult(0);
            this.currentGame.setScore1(this.gamePlayers[0].GetScore());
            this.currentGame.setScore2(this.gamePlayers[1].GetScore());
            this.gc.addGame(currentGame);
            EndOfGame();

        }

    }



    public String showPlayerBestGames (String p, int pos) {



        for (Game g : this.gc.getListOfGames()) {
            if (g != null) {
                if ( (g.getGamePlayers()[0] != null && g.getGamePlayers()[0].getName().equals(p))
                        ||g.getGamePlayers()[1] != null && g.getGamePlayers()[1].getName().equals(p)){

                    if (pos == 0) {
                        if (!this.checkIfGameExistsOnList1(g))
                            GameList.add(g) ;
                    }
                    else {
                        if (!this.checkIfGameExistsOnList2(g))
                            GameList2.add(g);
                    }
                }

                else return null  ;
            } }

        if (pos == 0) {
            for (int i = 0; i < GameList.size() ; ++i) {
                if( i > 4 )
                    break ;
                sb.append(GameList.get(i).gameStats(p) );

            }
        }else
        {
            for (int i = GameList2.size() - 1  ; i >= 0 ; --i) {

                sb.append(GameList2.get(i).gameStats(p) );
            } }
        return sb.toString() ;
    }



    public String showPlayerMostRecentGames (String p  , int pos) {

        for (Game g : this.gc.getListOfGames()) {
            if (g != null) {
                if ( (g.getGamePlayers()[0] != null && g.getGamePlayers()[0].getName().equals(p))
                        ||g.getGamePlayers()[1] != null && g.getGamePlayers()[1].getName().equals(p)){

                    if (pos == 0) {
                        if (!this.checkIfGameExistsOnList1(g))
                            GameList.add(g) ;
                    }
                    else {
                        if (!this.checkIfGameExistsOnList2(g))
                            GameList2.add(g);
                    }
                }
                else return null  ;
            } }

        this.GameList.toArray(this.playerGames) ;

        for (int i = 0 ; i < this.gc.getNumOfGames() ; ++i) {
            if (this.playerGames[i+1] != null) {
                if (this.playerGames[i+1].getDateAndTime().isAfter(this.playerGames[i].getDateAndTime())) {
                    this.playerGames[i] = this.playerGames[i+1] ;
                }
            }
        }

        if (pos == 0) {
            for (int i = 0; i < GameList.size() ; ++i) {
                if( i > 4 )
                    break ;
                sb.append(GameList.get(i).gameStats(p) );

            }
        }else
        {
            for (int i = GameList2.size() - 1  ; i >= 0 ; --i) {

                sb.append(GameList2.get(i).gameStats(p) );
            } }
        return sb.toString() ;

    }


    public boolean checkIfGameExistsOnList1 (Game g) {

        for (int i = 0 ; i < GameList.size() ; ++i) {
            if (GameList.get(i).equals(g))
                return true ;

        }
        return false;

    }

    public boolean checkIfGameExistsOnList2 (Game g) {
        for (int i = 0 ; i < GameList2.size() ; ++i) {
            if (GameList.get(i) != null) {
                if (GameList.get(i).equals(g))
                    return true ;

            } }
        return false;

    }



    public int getMoves() {
        return moves;
    }


    public boolean getXvictory() {
        return Xvictory;
    }

    public boolean getOvictory() {
        return Ovictory;
    }

    public boolean getDraw() {
        return draw;
    }

    static Boolean isMovesLeft(String[][] board)
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j]==null)
                    return true;
        return false;
    }


    //======================================================================================================
    // PERFECT PLAYER  in position 0( X )


    static int evaluate0(String[][] b)
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if(b[row][0]!=null &&  b[row][1]!=null && b[row][2]!=null) {
                if (b[row][0].equals(b[row][1]) &&
                        b[row][1].equals(b[row][2])) {
                    if (b[row][0].equals("X"))
                        return +1;
                    else if (b[row][0].equals("O"))
                        return -1;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if(b[0][col]!=null  && b[1][col]!=null && b[2][col]!=null) {
                if (b[0][col].equals(b[1][col]) &&
                        b[1][col].equals(b[2][col])) {
                    if (b[0][col].equals("X"))
                        return +1;

                    else if (b[0][col].equals("O"))
                        return -1;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if(b[0][0]!=null && b[1][1]!=null && b[2][2]!=null) {
            if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) {
                if (b[0][0].equals("X"))
                    return +1;
                else if (b[0][0].equals("O"))
                    return -1;
            }
        }

        if(b[0][2]!=null && b[1][1]!=null && b[2][0]!=null) {
            if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) {
                if (b[0][2].equals("X"))
                    return +1;
                else if (b[0][2].equals("O"))
                    return -1;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }


/*
     This is the minimax function. It considers all
   the possible ways the game can go and returns
 the value of the board

 */

    public int minimax0(String[][] board, int depth, Boolean isMax)
    {
        int score = evaluate0(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 1)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -1)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;

        // If this maximizer's move

        if (isMax)
        {
            int  best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]==null)
                    {
                        // Make the move
                        board[i][j]="X";
                        //makeMove(i,j);

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax0(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = null;
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] ==null)
                    {
                        // Make the move
                        board[i][j] = "O";
                        //makeMove(i,j);

                        best = Math.min(best, minimax0(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = null;
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    public Point findBestMove0(String[][] board)
    {
        int bestVal = -1000;
        //Move bestMove = new Move();

        Point p =new Point();
        p.x=-1;
        p.y=-1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == null) {
                    // Make the move
                    board[i][j] = "X";

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax0(board, 0, false);

                    // Undo the move
                    board[i][j] = null;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        p.x = i;
                        p.y = j;

                        bestVal = moveVal;

                    }
                }
            }

        }


        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);
        return p;
    }

/*======================================================================================================
                     PERFECT PLAYER  in position 1( O )

 */

    static int evaluate1(String[][] b)
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if(b[row][0]!=null &&  b[row][1]!=null && b[row][2]!=null) {
                if (b[row][0].equals(b[row][1]) &&
                        b[row][1].equals(b[row][2])) {
                    if (b[row][0].equals("X"))
                        return -1;
                    else if (b[row][0].equals("O"))
                        return +1;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if(b[0][col]!=null  && b[1][col]!=null && b[2][col]!=null) {
                if (b[0][col].equals(b[1][col]) &&
                        b[1][col].equals(b[2][col])) {
                    if (b[0][col].equals("X"))
                        return -1;

                    else if (b[0][col].equals("O"))
                        return +1;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if(b[0][0]!=null && b[1][1]!=null && b[2][2]!=null) {
            if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) {
                if (b[0][0].equals("X"))
                    return -1;
                else if (b[0][0].equals("O"))
                    return +1;
            }
        }

        if(b[0][2]!=null && b[1][1]!=null && b[2][0]!=null) {
            if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) {
                if (b[0][2].equals("X"))
                    return -1;
                else if (b[0][2].equals("O"))
                    return +1;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }

    public int minimax1(String[][] board, int depth, Boolean isMax)
    {
        int score = evaluate1(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 1)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -1)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;

        // If this maximizer's move

        if (isMax)
        {
            int  best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]==null)
                    {
                        // Make the move
                        board[i][j]="O";
                        //makeMove(i,j);

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax1(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = null;
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] ==null)
                    {
                        // Make the move
                        board[i][j] = "X";
                        //makeMove(i,j);

                        best = Math.min(best, minimax1(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = null;
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    public Point findBestMove1(String[][] board)
    {
        int bestVal = -1000;
        //Move bestMove = new Move();

        Point p =new Point();
        p.x=-1;
        p.y=-1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == null) {
                    // Make the move
                    board[i][j] = "O";

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax1(board, 0, false);

                    // Undo the move
                    board[i][j] = null;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        p.x = i;
                        p.y = j;

                        bestVal = moveVal;

                    }
                }
            }

        }


        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);
        return p;
    }


    public ListOfCharts<Game> getGameList() {
        return GameList;
    }


    public void setGameList(ListOfCharts<Game> gameList) {
        GameList = gameList;
    }


    public ListOfCharts<Game> getGameList2() {
        return GameList2;
    }


    public void setGameList2(ListOfCharts<Game> gameList2) {
        GameList2 = gameList2;
    }


    public StringBuilder getSb() {
        return sb;
    }


    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }


    public Game getCurrentGame() {
        return currentGame;
    }


    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }


    public LocalDateTime getTime() {
        return time;
    }


    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}