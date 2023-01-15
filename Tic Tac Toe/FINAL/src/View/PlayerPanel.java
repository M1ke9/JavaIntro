package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Control.GameController;
import Model.Player;

public class PlayerPanel extends GamePanel{

    JButton selectPlayerBtn;
    int pos;
    Player currentPlayer = new Player();
    JTextField plName;
    JLabel plMark;
    JTextArea plStats;
    JButton startGame;



    public PlayerPanel(GameController c, int pos) {
        super(c);
        this.pos=pos;
        //AllPlayers=new String[20];

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
        this.setBorder(new LineBorder(Color.GRAY,1,true));
        this.setAlignmentX(CENTER_ALIGNMENT);
        selectPlayerBtn = new JButton("Select Player");
        selectPlayerBtn.setPreferredSize(new Dimension(200,40));
        selectPlayerBtn.setAlignmentX(RIGHT_ALIGNMENT);
        selectPlayerBtn.addActionListener((e)->{changePlayer();});
        selectPlayerBtn.setBackground(Color.white);

        this.startGame = new JButton ("Start game") ;
        this.startGame.setPreferredSize(new Dimension (200,40));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.startGame.setBackground(Color.WHITE);
        this.startGame.addActionListener(e->{gc.startGame();});


        plName = new JTextField();
        plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH +20,40));
        plName.setMaximumSize(plName.getPreferredSize() );
        plName.setAlignmentX(CENTER_ALIGNMENT);
        plName.setHorizontalAlignment(JTextField.CENTER);
        plName.setEnabled(false);
        plName.setBackground(Color.GREEN);

        /* Changes for Lab 08: Player Mark */
        plMark = new JLabel(pos==0? "X" : "O");
        plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
        plMark.setAlignmentX(CENTER_ALIGNMENT);
        plMark.setHorizontalAlignment(JTextField.CENTER);
        plMark.setEnabled(false);
        Font markf = new Font("SansSerif", Font.BOLD,90);
        plMark.setFont(markf);
        plMark.setBackground(Color.white);

        /* Changes for Lab 08: Player Stats JTextArea */
        plStats = new JTextArea(10,100);
        plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
        plStats.setAlignmentX(CENTER_ALIGNMENT);
        Font statsf = new Font("SansSerif", Font.BOLD,20);
        plStats.setFont(statsf);
        plStats.setEnabled(false);
        plStats.setMargin(new Insets(10, 10, 10, 10));

        plStats.setBackground(Color.DARK_GRAY);
        this.add(selectPlayerBtn);
        this.add(startGame) ;
        this.add(plMark);
        //this.add(plName);
        this.add(plStats);




    }


    public void changePlayer() {
        //Get the list of all players
        String selPlayer;


        String[] allPlayers = getModel().getPlayerCatalogue().getPlayersNames();



        selPlayer = (String) JOptionPane.showInputDialog(this,
                "Choose a Player...",
                "Player selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                allPlayers,
                currentPlayer
        );



        //Set the selected player
        if(selPlayer != null) {
            if (getModel().getGamePlayers()[pos==0?1:0] != null && selPlayer.equals(getModel().getGamePlayers()[pos==0?1:0].getName())) {
                JOptionPane.showMessageDialog(gc.getView(),
                        "Player already selected",
                        "Ooops...",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                this.currentPlayer.setName(selPlayer);
                gc.selectPlayer(getModel().getPlayerCatalogue().findPlayerByName(selPlayer),pos);
                this.plName.setText(currentPlayer.getName());
                this.plStats.setText("\nPlayer : " + this.currentPlayer.getName());
                this.plStats.append(getModel().getPlayerCatalogue().findPlayerByName(selPlayer).getPlayerStatus());
                this.plName.setText(selPlayer);
                //this.getModel().getCurrentGame().addGamePlayer(currentPlayer, pos);

                this.plStats.append("\n               Best Games ");

                this.plStats.append(this.getModel().showPlayerBestGames(selPlayer,pos));

                //HERE YOU CAN SEE PLAYER MOST RECENT GAMES

                //this.plStats.append(this.getModel().showPlayerMostRecentGames(selPlayer, pos));


                this.getModel().getSb().setLength(0);

                this.repaint();
            }
        }
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public JTextField getPlName() {
        return plName;
    }

    public JTextArea getPlStats() {
        return plStats;
    }

    public void setPlayerStats(String stats) {
        this.plStats.setText(stats);
    }

    public JButton getSelectPlayerBtn() {
        return selectPlayerBtn;
    }

    public JLabel getPlMark() {
        return plMark;
    }



    public void setPlMark(JLabel plMark) {
        this.plMark = plMark;
    }

    public JButton getStartGame() {
        return startGame;
    }

    public void setStartGame(JButton startGame) {
        this.startGame = startGame;
    }


}