package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import Control.GameController;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Model.GameModel;


@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {

    public static final int CELL_PADDING = 10;

    int row, col;

    public boolean highlighted;

    public BoardCell(GameController gc, int row, int col) {
        super(gc);
        this.setBackground(Color.WHITE);
        this.row = row;
        this.col = col;
        this.addMouseListener(this);
        this.highlighted = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered cell " + this);
        this.highlight();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited on cell " + this);
        this.unHighlight();
    }

    private void highlight() {
        if (!highlighted && getModel().inPlay() && !getModel().EndOfGame()) {
            highlighted = true;
            repaint();
        }
    }

    private void unHighlight() {
        if (highlighted && getModel().inPlay()) {
            highlighted = false;
            repaint();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        Random r = new Random();

        String mark = getModel().getBoardMark(this.row, this.col);
        Graphics2D g2d = (Graphics2D) g;
        int size = this.getSize().width - 2 * CELL_PADDING;
        g2d.setStroke(new BasicStroke(6));

        if (mark == null) {
            if (highlighted) {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(CELL_PADDING, CELL_PADDING, size, size);
            }

            return;
        } else if (mark.equals("X")) {

            g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size, CELL_PADDING + size);
            g2d.drawLine(CELL_PADDING + size, CELL_PADDING, CELL_PADDING, CELL_PADDING + size);
            g2d.setBackground(Color.ORANGE);

        } else if (mark.equals("O")) {

            g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);


        } else
            return;

        if (this.getModel().getGamePlayers()[0].getTypeOfPlayer() == -1) {

            if (this.getModel().getmover() && !this.getModel().EndOfGame()) {

                int r1 = r.nextInt(3);
                int r2 = r.nextInt(3);


                if (getModel().getGameBoard()[r1][r2] == null && !this.getModel().EndOfGame()) {
                    this.getModel().makeMove(r1, r2);
                    getModel().Check();
                }
            }
        }

        if (this.getModel().getGamePlayers()[1].getTypeOfPlayer() == -1) {

            if (!this.getModel().getmover() && !this.getModel().EndOfGame()) {


                int r1 = r.nextInt(3);
                int r2 = r.nextInt(3);


                if (getModel().getGameBoard()[r1][r2] == null && !this.getModel().EndOfGame()) {
                    this.getModel().makeMove(r1, r2);
                    getModel().Check();

                }
            }
        }


        if (this.getModel().getGamePlayers()[0].getTypeOfPlayer() == 1) {
            if ( this.getModel().getmover() &&!this.getModel().EndOfGame()) {
                Point BestMove = getModel().findBestMove0(getModel().getGameBoard());
                getModel().makeMove(BestMove.x, BestMove.y);
                getModel().Check();
            }
        }

        if (this.getModel().getGamePlayers()[1].getTypeOfPlayer() == 1) {
            if (!this.getModel().getmover() && !this.getModel().EndOfGame()) {
                Point BestMove = getModel().findBestMove1(getModel().getGameBoard());
                getModel().makeMove(BestMove.x, BestMove.y);
                getModel().Check();
            }
        }


    }


    @Override
    public String toString() {
        return "(" + this.row + "," + this.col + ")";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked on cell " + this);
        if (getModel().inPlay() && !getModel().EndOfGame()) {

            getModel().makeMove(row, col);
            repaint();
            getModel().Check();
        }

    }



    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}