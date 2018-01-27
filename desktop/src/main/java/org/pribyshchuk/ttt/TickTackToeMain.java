package org.pribyshchuk.ttt;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TickTackToeMain extends JFrame{
    public TickTackToeMain() {
        this("My Frame");
    }

    public TickTackToeMain(String title){
        super(title);

        setContentPane(new DrawPane());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 400);

        setVisible(true); 
    }

    private class DrawPane extends JPanel {
        private int getWinner(){
            for(int i = 0; i < 3; i++){
                if(map[i][0]==map[i][1]&&map[i][1]==map[i][2]){
                    return map[i][0];
                }
            }
            for (int j = 0; j < 3; j++) {
                if(map[0][j]==map[1][j]&&map[1][j]==map[2][j]){
                    return map[0][j];
                }
            }
            if(map[0][0]==map[1][1]&&map[1][1]==map[2][2]){
                return map[0][0];
            }else if (map[0][2]==map[1][1]&&map[1][1]==map[2][0]) {
                return map[0][2];
            }else {
                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (map[i][j] == 0) {
                            return 0;
                        }
                    }
                }
                return -1;
            }
        }

        int[][] map = new int[3][3];
        int nextPlayer = 1;
        DrawPane() {
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {                    
                    System.out.println("mouseReleased");
                    int i = 0;                    
                    if (e.getX() < getWidth() / 3) {
                        i = 0;
                    }else if (e.getX() > getWidth() / 3 && e.getX() < getWidth() * 2 / 3) {
                        i = 1;                        
                    }else if (e.getX() > getWidth() * 2 / 3) {
                        i = 2;                        
                    }
                    int j = 0;
                    if (e.getY() < getHeight() / 3) {
                        j = 0;
                    }else if (e.getY() > getHeight() / 3 && e.getY() < getHeight() * 2 / 3) {
                        j = 1;                        
                    }else if (e.getY() > getHeight() * 2 / 3) {
                        j = 2;                        
                    }
                    if (map[i][j] == 0){
                        map[i][j] = nextPlayer;
                        DrawPane.this.repaint();
                        if (nextPlayer == 1) {
                            nextPlayer = 2;
                        } else if (nextPlayer == 2) {
                            nextPlayer = 1;
                        }
                    }
                    int winner = getWinner();
                    if (winner != 0) {
                        String message = null;
                        if (winner == -1) {
                            message = "No winners. Would you like to play again?";
                        } else {
                            message = "Plyer #" + winner + " win! Would you like to play again?";
                        }

                        int result = JOptionPane.showInternalConfirmDialog(DrawPane.this, message, "Play again?", 
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        
                        if (result == JOptionPane.YES_OPTION) {
                            map = new int[3][3];
                            DrawPane.this.repaint();
                        }
                    }

                }
            });
        }

        @Override
        public void paintComponent(Graphics g){            
            g.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3);
            g.drawLine(0, getHeight() *2 / 3, getWidth(), getHeight() *2 / 3);
            g.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight());
            g.drawLine(getWidth() * 2 / 3, 0, getWidth() * 2 / 3, getHeight());
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++) {
                    switch(map[i][j]) {
                        case 1: {
                            int x = i * getWidth() / 3;
                            int y = j * getHeight() / 3;
                            g.drawOval(x, y, getWidth() / 3, getHeight() / 3);
                        }
                        break;
                        case 2:{
                            int x1 = i * getWidth() / 3;
                            int y1 = j * getHeight() / 3;
                            int x2 = x1 + getWidth() / 3;
                            int y2 = y1 + getHeight() / 3;
                            g.drawLine(x1, y1, x2, y2);
                            int x3 = x1 + getWidth() / 3;
                            int y3 = y1;
                            int x4 = x3 - getWidth() / 3;
                            int y4 = y2;
                            g.drawLine(x3, y3, x4, y4);
                        }
                        break;
                    }

                }
            }

        }
    }

    public static void main(String args[]){
        new TickTackToeMain();
    }
}