package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import Control.GameController;

public class GameBoard extends GamePanel {

    BoardCell[] cells;



    public GameBoard(GameController gc) {
        super(gc);
        this.setLayout(null);
        setSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
        this.setBackground(Color.WHITE);
        this.cells= new BoardCell[9];
        for (int row=0; row< 3; row++) {
            for (int col=0; col< 3; col++) {
                BoardCell cell = new BoardCell(gc, row, col);
                cell.setBounds(
                        boardZero().x+col*cellSize()+BoardCell.CELL_PADDING,
                        boardZero().y+row*cellSize()+BoardCell.CELL_PADDING,
                        cellSize()-2*BoardCell.CELL_PADDING,
                        cellSize()-2*BoardCell.CELL_PADDING
                );
                cells[3*row +col]=cell;
                this.add(cell);

            }

        }

    }


    /**
     * Computes the cell size along with paddings (min Panel dimension / 5) */
    private int cellSize() {
        int minDim = Integer.min(this.getWidth(),this.getHeight());
        return minDim/5;
    }

    /** Returns the internal size of the real board */
    private int boardSize() {
        return 3*cellSize();
    }

    /**
     * Returns the upper left corner of the board */
    private Point boardZero() {
        int x= (this.getWidth() - boardSize())/2;
        int y= (this.getHeight() - boardSize())/2;
        return new Point(x,y);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g ;
        String player1 = this.getModel().getGamePlayers()[0].getName() ;
        String player2 = this.getModel().getGamePlayers()[1].getName() ;
        int x = 200;
        int y = 30 ;
        g2d.setStroke(new BasicStroke(6)) ;
        g2d.setFont(new Font("Current Player",Font.BOLD, 20));
        if (getModel().getMoverMark().equals("X") && getModel().getMoves() < 9 && !getModel().EndOfGame()) {
            g2d.drawString("It is " + player1 + "'s turn", x, y);
        }
        else if(getModel().getMoverMark().equals("O") && getModel().getMoves() < 9 && !getModel().EndOfGame() )
        {g2d.drawString("It is " + player2 + "'s turn", x, y);}
        else if (getModel().getXvictory()){
            g2d.drawString("   " + player1 + " WON !!!", x, y);
        }
        else if (getModel().getOvictory()) {
            g2d.drawString("   " + player2 + " WON !!!", x, y);
        }
        else {
            g2d.drawString("     Its a tie !", x, y);

        }


        drawGrid(g);
        repaint() ;
    }




    /**
     * Draws the grid lines of the board
     * @param g Graphics2D implicit object
     */
    public void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(6));
        for (int i=1; i<=2; i++) {
            //Draw Vertical Line
            g2d.drawLine(
                    boardZero().x +i*cellSize(),boardZero().y, 				// i-based x,  	upperLeft.y
                    boardZero().x+i*cellSize(),boardZero().y+boardSize() 	// i-based x,	boardBottom.y (boardBottom = y + boardSize)
            );

            //Draw Horizontal Line
            g2d.drawLine(
                    boardZero().x,  boardZero().y+i*cellSize(), 			// upperLeft.x, i-based y
                    boardZero().x+boardSize(), boardZero().y+i*cellSize()	// boardRight.x, i-based y (boardRight = x + boardSize)
            );
        }

    }



    public BoardCell[] getCells() {
        return cells;
    }


}
