package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import Control.GameController;
import Model.Player;

public class TopPanel extends GamePanel{
    JButton quitBtn;
    JButton doneBtn;
    JButton addPlayerBtn ;

    public TopPanel(GameController gc) {
        super(gc);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
        this.setBorder(new LineBorder(Color.GRAY,1,true));
        quitBtn = new JButton("Quit App");
        quitBtn.setPreferredSize(new Dimension(100, 40));
        quitBtn.addActionListener((e)->{this.gc.quit();});



        doneBtn = new JButton("Done");
        doneBtn.setPreferredSize(new Dimension(100, 40));
        doneBtn.setEnabled(false);
        doneBtn.addActionListener((e)->this.gc.StartAfterEnd());

        this.addPlayerBtn = new JButton("Add player") ;
        this.addPlayerBtn.setPreferredSize(new Dimension (100,40));
        this.addPlayerBtn.setEnabled(true);
        this.addPlayerBtn.addActionListener((e)->AddPlayer());



        this.add(addPlayerBtn);
        add(doneBtn);
        add(quitBtn);
        this.setBackground(Color.white);
    }

    public  void AddPlayer() {
        String name;
        name = JOptionPane.showInputDialog("Give a player");
        boolean check=true;
        for (int i = 0; i < getModel().getPlayerCatalogue().getNumOfPlayers(); i++) {
            if(name!=null)
                if (name.equals(gc.getModel().getPlayerCatalogue().getPlayer(i).getName())) {

                    check=false;
                    JOptionPane.showMessageDialog(gc.getView(),
                            "Player already exists",
                            "Ooops...",
                            JOptionPane.ERROR_MESSAGE);
                }
        }
        if(check)
            getModel().getPlayerCatalogue().addPlayer(name);
    }



    public JButton getQuitBtn() {
        return quitBtn;
    }



    public JButton getDoneBtn() {
        return doneBtn;
    }

}