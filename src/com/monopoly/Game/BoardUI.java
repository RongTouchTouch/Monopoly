package com.monopoly.Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardUI {
    static Axis s=new Axis();
    static int money=500;
    static int count=0;
    public static void main(String[] args) {
        //s.init();
        JFrame gameframe=new JFrame("Monopoly");
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setSize(950,650);
        gameframe.setResizable(true);
        gameframe.getContentPane().setBackground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel player1=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\player\\0\\logo.png"));
        panel.add(player1);
        player1.setBounds(100, 100, 30, 30);

        JLabel house = new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\player\\1\\logo.png"));
        panel.add(house);
        house.setBounds(162, 112, 30, 30);
        house.setVisible(false);

        JLabel map=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\1.png")) ;
        panel.add(map);
        map.setBounds(50, 50, 630, 630);

        JButton move = new JButton("move");
        move.setBounds(200,200,50,50);
        panel.add(move);
        move.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                count=(count+1)%40;
                //player1.setBounds(Axis.X[count],Axis.Y[count],30,30);
            }
        });

        JLabel player1info=new JLabel();
        player1info.setText("<html>aaa<br />bbb<br />"+money+"</html>");
        player1info.setBounds(300,300,100,100);
        panel.add(player1info);

        JButton build = new JButton("house");
        build.setBounds(250,200,50,50);
        panel.add(build);
        build.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                house.setVisible(true);
                money=money-200;
                player1info.setText("<html>aaa<br />bbb<br />"+money+"</html>");
            }
        });








        gameframe.setLayout(null);
        gameframe.setContentPane(panel);
        gameframe.pack();
        gameframe.setVisible(true);

    }
}
