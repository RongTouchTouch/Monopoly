package com.monopoly.Game;

import com.monopoly.Game.Squares.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Promotion extends JFrame implements KeyListener, MouseMotionListener,
        MouseListener, MouseWheelListener{
    private int mouseRow = 9;
    private int mouseCol = 9;
    private int turnCounter = 1;

    private About about;

    JMenuBar bar;
    private BufferedImage boardImage;
    private Image cursorImage;
    private Image icon;
    private Image negIcon;
    private JLabel map;
    private JLabel player1,player2,player3,player4;
    private JPanel panel;

    private Player p1, p2, p3, p4;
    private boolean winner = false;
    private int row1,col1,row2,col2,row3,col3,row4,col4;
    private Game game;
    private Board board;
    private Dimension dim;
    private JTextArea outputText;

    public Promotion(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(20, 50);
        panel.setLocation(10, 550);
        panel.setOpaque(false);

        //Set the cursor
        //options = new Options();
        Toolkit tk = Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();
        cursorImage = tk.getImage("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\Cursor.gif");
        Point cursorHotSpot = new Point(0, 0);
        Cursor customCursor = tk.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");
        this.setCursor(customCursor);

        //Turn = new LinkedList<Move>();
        icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\nuke.jpg");
        negIcon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\nukeNeg.jpg");
        this.setIconImage(icon);
        this.setLocation(((dim.width / 2) - (530 / 2)), (dim.height / 2) - (570 / 2));


        int width = 950,height = 950;
        ImageIcon image = new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\Seminopoly.jpg");//实例化ImageIcon 对象
        image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        map = new JLabel();
        map.setIcon(image);
        map.setSize(width, height);
        panel.add(map);

        this.setTitle("Monopoly");
        this.setSize(1580, 1030);
        dim.setSize(1580, 1030);
        this.setMinimumSize(dim);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.bar = new JMenuBar();      //Menu bar

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(helpMenu);

        JMenuItem NewGame = new JMenuItem("New");
        JMenuItem Load = new JMenuItem("Load");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Exit = new JMenuItem("Exit");
        JMenuItem Options = new JMenuItem("Options");
        JMenuItem flipBoard = new JMenuItem("Flip Board");
        JMenuItem About = new JMenuItem("About");

        Exit.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(NewGame);
        fileMenu.add(Load);
        fileMenu.add(Save);
        fileMenu.add(Exit);
        editMenu.add(Options);
        editMenu.add(flipBoard);
        helpMenu.add(About);

        outputText = new JTextArea(10,8);
        outputText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        JScrollPane jsp = new JScrollPane(outputText);
        jsp.setBounds(1000,400,500,400);
        panel.add(jsp);

        JButton dice = new JButton();
        dice.setBounds(970,850,20,20);
        panel.add(dice);
        dice.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(turnCounter % 4 == 1)
                        {
                            outputText.append("Player1 's turn\n");
                            p1.roll();
                            outputText.append("The toatl face value is " + p1.getStep()+"\n");
                        }
                        else if(turnCounter % 4 == 2)
                        {
                            outputText.append("Player2 's turn\n");
                            p2.roll();
                            outputText.append("The toatl face value is " + p2.getStep()+"\n");
                        }
                        else if(turnCounter % 4 == 3)
                        {
                            outputText.append("Player3 's turn\n");
                            p3.roll();
                            outputText.append("The toatl face value is " + p3.getStep()+"\n");
                        }
                        else
                        {
                            outputText.append("Player4 's turn\n");
                            p4.roll();
                            outputText.append("The toatl face value is " + p4.getStep()+"\n");

                        }
                        //turnCounter++;
                        return;
                    }
                }
        );

        JButton move = new JButton();
        move.setBounds(1020,850,20,20);
        panel.add(move);
        move.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int step;
                        Axis axis = new Axis();
                        if(turnCounter % 4 == 1)
                        {
                            step = p1.getStep();
                            axis.cal(row1,col1,step);
                            row1 = axis.calX();
                            col1 = axis.calY();
                            player1.setBounds(axis.calX(), axis.calY(),30,30);
                        }
                        else if(turnCounter % 4 == 2)
                        {
                            step = p2.getStep();
                            axis.cal(row2,col2,step);
                            row2 = axis.calX();
                            col2 = axis.calY();
                            player2.setBounds(axis.calX()+15, axis.calY(),30,30);
                        }
                        else if(turnCounter % 4 == 3)
                        {
                            step = p3.getStep();
                            axis.cal(row3,col3,step);
                            row3 = axis.calX();
                            col3 = axis.calY();
                            player3.setBounds(axis.calX(), axis.calY()+30,30,30);
                        }
                        else
                        {
                            step = p4.getStep() ;
                            axis.cal(row4,col4,step);
                            row4 = axis.calX();
                            col4 = axis.calY();
                            player4.setBounds(axis.calX()+30, axis.calY()+ 30,30,30);

                        }
                        turnCounter++;
                        return;
                    }
                }
        );

        initializeBoard();

        About.addActionListener(
                new ActionListener () {
                    public void actionPerformed(ActionEvent e) {
                        about = new About();
                        about.setVisible(true);
                    }
                }
        );

        /*s
        NewGame.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newGame();
                    }
                }
        );

        Options.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        options.setVisible(true);
                    }
                }
        );
        flipBoard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        flipBoard();
                    }
                }
        );
        Exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exitForm();
                    }
                }
        );
        Save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            saveGame();
                        }
                        catch (IOException ioe) {ioe.toString();}
                    }
                }
        );
        Load.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            loadGame();
                        }
                        catch (IOException ioe) {ioe.toString();}
                    }
                }
        );
        */



        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this);


        this.setLayout(null);
        this.setContentPane(panel);
        this.setJMenuBar(bar);
        this.setVisible(true);
        this.pack();

        play();
    }


    private void play(){
        game = new Game();
        //game.play();
        List<Player> players = game.getPlayers();
        p1 = players.get(0);
        p2 = players.get(1);
        p3 = players.get(2);
        p4 = players.get(3);
        this.turnCounter = 1;
        int step = 0;
        /*
        while (winner == false)
        {
            if(turnCounter % 4 == 1)
            {
                outputText.append("Player1 's turn\n");
                p1.roll();

                step = (p1.getStep() + 1) % 40;
                //int x, y = Axis.cal(step);
                //player1.setBounds(x, y,30,30);
            }
            else if(turnCounter % 4 == 2)
            {
                outputText.append("Player2 's turn\n");
                p2.roll();
                outputText.append("The toatl face value is " + p2.getStep()+"\n");
                step = (p2.getStep() + 1) % 40;
               // player2.setBounds(Axis.X[step], Axis.Y[step],30,30);
            }
            else if(turnCounter % 4 == 3)
            {
                outputText.append("Player3 's turn\n");
                p3.roll();
                outputText.append("The toatl face value is " + p3.getStep()+"\n");
                step = (p3.getStep() + 1) % 40;
                //player3.setBounds(Axis.X[step], Axis.Y[step],30,30);
            }
            else
            {
                outputText.append("Player4 's turn\n");
                p4.roll();
                outputText.append("The toatl face value is " + p4.getStep()+"\n");
                step = (p4.getStep() + 1) % 40;
                //player4.setBounds(Axis.X[step], Axis.Y[step],30,30);
            }
            turnCounter ++;

        }
*/
    }

    private void initializeBoard(){
        row1 = 855;
        col1 = 845;
        player1=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\BLACKK.png"));
        player1.setBounds(row1,col1,50,50);
        map.add(player1);
        row2 = 905;
        col2 = 845;
        player2=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\BLACKN.png"));
        player2.setBounds(row2,col2,50,50);
        map.add(player2);
        row3 = 855;
        col3 = 895;
        player3=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\BLACKK.png"));
        player3.setBounds(row3,col3,50,50);
        map.add(player3);
        row4 = 905;
        col4 = 895;
        player4=new JLabel(new ImageIcon("C:\\Users\\Dell\\Desktop\\Monopoly\\src\\images\\BLACKK.png"));
        player4.setBounds(row4,col4,50,50);
        map.add(player4);

    }

    public static void main(String[] args) {
        Promotion gameframe = new Promotion();

    }

    private void exitForm () {
        System.out.println("Goodbye Chicken");
        this.setVisible(false);
        dispose();
        System.exit(0);
    }

    @Override
    public void setIconImage(Image image) {
        super.setIconImage(image);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            exitForm();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public synchronized void mousePressed(MouseEvent e) {
        mouseRow = (e.getY()-50)/64;
        mouseCol = (e.getX()-10)/64;
        e.consume();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        this.setIconImage(icon);
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
