package com.monopoly.Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Kaan
 * what up Kaan
 */
public class About extends JFrame implements MouseListener {
    private BufferedImage boardImage;
    private Image cursorImage;
    private Image Logo;

    public About () {
        JPanel panel = new JPanel();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        cursorImage = tk.getImage("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\Cursor.gif");
        Point cursorHotSpot = new Point(0,0);
        Cursor customCursor = tk.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\nuke.jpg");
        this.setIconImage(icon);

        try{
            Logo = ImageIO.read(new File("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\monopolylogo.png"));
        } catch (IOException ioe) {ioe.toString();}

        this.addMouseListener(this);
        this.setLocation(((dim.width/2)-(350/2)), (dim.height/2)-(350/2));
        this.setCursor(customCursor);
        this.setTitle("Monopoly : About");
        this.setSize(350, 425);
        this.setVisible(false);
        this.repaint();
    }

    public void paint(Graphics g){

        super.paintComponents(g);

        g.drawImage(Logo, 85, 50, this);
        g.setColor(Color.BLACK);

        g.drawString("Monopoly is a board game in which players roll two", 15, 110);
        g.drawString("six-sided dice to move around the game board, buying", 15, 125);
        g.drawString("and trading properties, and developing them with ", 15, 140);
        g.drawString("houses and hotels.  Players collect rent from their ", 15, 155);
        g.drawString("opponents, with the goal being to drive them into", 15, 170);
        g.drawString("bankruptcy. Money can also be gained or lost through", 15, 185);
        g.drawString("Chance and Community Chest cards, and tax squares;", 15, 200);
        g.drawString("players can end up in jail, which they cannot move ", 15, 215);
        g.drawString("from until they have met one of several conditions. ", 15, 230);
        g.drawString("The game has numerous house rules, and hundreds of ", 15, 245);
        g.drawString("different editions exist, as well as many spin-offs", 15, 260);
        g.drawString("and related media. ", 15, 275);

        g.drawString("Thank you for playing!", 15, 330);
        g.drawString("Please contact: ", 15, 345);
        g.drawString("OOAD for inquiries", 15, 360);
        g.drawString("Lead Design & Programming:", 15, 375);
        g.drawString("Zhewei Wu " + "Xiangrong Xu", 15, 390);
        g.drawString("XuXiang Jiang " + "Bu Liu", 15, 405);

    }



    public void mouseReleased(MouseEvent event){
        this.setVisible(false);
        this.dispose();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
