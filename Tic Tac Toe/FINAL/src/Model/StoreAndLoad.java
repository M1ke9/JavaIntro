package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Control.GameController;

public class StoreAndLoad {

    GameController gc ;



    public StoreAndLoad(GameController gc) {

        this.gc = gc ;



    }



    public void storePlayers() {
        ObjectOutputStream os = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("players.txt");
            os = new ObjectOutputStream(fos);

            for (Player p : this.gc.getModel().getPlayerCatalogue().GetPlayers()) {
                os.writeObject(p);

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {os.close(); fos.close();}catch (Exception e) {
            }
        }
    }

    public void loadPLayers() {
        ObjectInputStream is = null;
        FileInputStream fis = null;
        int pos = 0;
        try {
            fis = new FileInputStream("players.txt");
            is = new ObjectInputStream(fis);
            Player [] listOfPlayers = new Player [20] ;

            while (fis.available()>0) {
                Player p = (Player) is.readObject();
                if (p != null) {
                    listOfPlayers[pos] = p;
                    pos++;

                }
            }

            this.gc.getModel().getPlayerCatalogue().setPlayers(listOfPlayers);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found for read objects...");
        }finally {
            try {is.close(); fis.close();}catch (Exception e) {
            }
        }
    }


}