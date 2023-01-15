package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Control.GameController;

public class HallOfFame extends GamePanel{

    JTextArea PlayerScores;
    JTextField hof;

    public HallOfFame(GameController gc) {
        super(gc);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT- MainWindow.TOP_HEIGHT));
        this.setBorder(new LineBorder(Color.GRAY,1,true));
        this.setAlignmentX(CENTER_ALIGNMENT);


//NEW
        hof = new JTextField();
        hof .setPreferredSize(new Dimension(MainWindow.WIDTH-MainWindow.PLAYER_WIDTH,40));
        hof .setMaximumSize(hof .getPreferredSize() );
        hof.setBorder(new LineBorder(Color.BLACK,1,true));
        Font FontHof= new Font("SansSerif",Font.BOLD,30);
        hof.setFont(FontHof);
        hof.setBackground(Color.BLACK);
        hof .setEnabled(false);
        hof.setText("\tHALL OF FAME\t");


        PlayerScores=new JTextArea();
        PlayerScores.setEnabled(false);
        PlayerScores.setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT- MainWindow.TOP_HEIGHT-40));
        Font FontScore=new Font("SansSerif",Font.BOLD,22);
        PlayerScores.setFont(FontScore);
        PlayerScores.setBackground(Color.BLACK);
        PlayerScores.setEnabled(false);




        this.add(hof);
        this.add(PlayerScores);

    }

    public JTextArea getPlayerScores() {
        return PlayerScores;
    }



    public void SetPlayerScore(String name){

        this.PlayerScores.setText(name +PlayerScores.getText());
    }




}