package View;



import javax.swing.JPanel;

import Control.GameController;
import Model.GameModel;

public abstract class GamePanel extends JPanel{
    protected GameController gc;

    public GamePanel(GameController gc) {
        super();
        this.gc = gc;
    }



    protected GameController getController() {
        return this.gc;
    }


    protected GameModel getModel() {
        return gc.getModel();
    }
}
