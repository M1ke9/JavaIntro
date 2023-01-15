package Control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Games.Game;
import Model.*;
import View.MainAreaPanel;
import View.MainWindow;


public class GameController extends WindowAdapter {
    MainWindow view;
    GameModel model;
    StoreAndLoad gr;


    Game [] listOfGames ;
    private int numOfGames ;

    public GameController() {

        listOfGames = new Game [30] ;
        numOfGames = 0 ;

    }

    @Override
    public void windowClosing(WindowEvent event) {
        quit();
    }


    public void start() {
        this.view= new MainWindow(this);
        this.model = new GameModel(this);
        this.view.addWindowListener(this);
        this.view.setVisible(true);
        this.gr=new StoreAndLoad(this);
        this.view.getLeftPanel().getStartGame().setEnabled(false);
        this.view.getRightPanel().getStartGame().setEnabled(false);


    }

    public void quit() {
        System.out.println("BYE BYEE...");
        System.exit(0);
    }
    public void StartAfterEnd() {

        this.listOfGames[this.numOfGames-1].setDateAndTime(LocalDateTime.now());
        this.printGames();

        this.gr.storePlayers();
        this.model.getList().clear();
        this.view.getMainPanel().getHallOfFame().getPlayerScores().setText(null);
        this.model.getList().addAll(model.getPlayerCatalogue().GetPlayers());
        this.view.getTopPanel().getDoneBtn().setEnabled(false);
       // this.view.addWindowListener(this);


        this.model.HallOfScores();
        int PreGameNumOfPl=this.getModel().getPlayerCatalogue().getNumOfPlayers();

        this.model = new GameModel(this);
        this.getModel().getPlayerCatalogue().setNumOfPlayers(PreGameNumOfPl);
        this.gr.loadPLayers();

        this.view.getMainPanel().showCard(MainAreaPanel.HOF);
        this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(true);
        this.view.getRightPanel().getSelectPlayerBtn().setEnabled(true);
        this.view.getLeftPanel().getPlStats().setText(null);
        this.view.getRightPanel().getPlStats().setText(null);

        this.view.getLeftPanel().getStartGame().setEnabled(model.ready());
        this.view.getRightPanel().getStartGame().setEnabled(model.ready());


    }


    public void selectPlayer(Player p, int pos) {
        this.model.selectPlayer(p, pos);
        System.out.println("Player " + pos + " set to " + p.getName());

        this.view.getLeftPanel().getStartGame().setEnabled(model.ready());
        this.view.getRightPanel().getStartGame().setEnabled(model.ready());


        this.view.getLeftPanel().getStartGame().addActionListener(e->getModel().LeftPlayerFirstMove());
        this.view.getRightPanel().getStartGame().addActionListener(e->getModel().RightPlayerFirstMove());
    }

    public void startGame() {



        this.model.setGameBoard(new String[3][3]);
        this.view.getLeftPanel().getStartGame().setEnabled(false);
        this.view.getRightPanel().getStartGame().setEnabled(false);
        this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
        this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
        this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());

        this.model.getCurrentGame().setGamePlayers(this.model.getGamePlayers());
        this.getModel().MrBeanFirstMove();
        this.getModel().HalFirstMove();


    }

    public void EndGame(){

        this.view.getTopPanel().getDoneBtn().setEnabled(true);


    }


    public void addGame (Game g) {
        if (numOfGames < 30 ) {
            listOfGames [numOfGames] = g ;
            numOfGames++ ;
        }
        else return ;
    }

    public void printGames () {
        System.out.println( "Games played :" + this.numOfGames ) ;
        for (int i = 0 ;  i < this.numOfGames ; ++i) {
            System.out.println(this.listOfGames[i].getGamePlayers()[0].getName() + " vs " +  this.listOfGames[i].getGamePlayers()[1].getName()
                    + " Game " + (i+1) + "  Result : " + this.listOfGames[i].getResult() +
                    "  Date and time - >  " + this.listOfGames[i].getDateAndTime().format(DateTimeFormatter.ofPattern("E,dd MMM yyyy HH:mm")) ) ;



        }
    }

    public GameModel getModel() {

        return model;
    }

    public MainWindow getView() {

        return view;
    }

    public StoreAndLoad getGr() {
        return gr;
    }

    public Game[] getListOfGames() {
        return listOfGames;
    }

    public void setListOfGames(Game[] listOfGames) {
        this.listOfGames = listOfGames;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }




}