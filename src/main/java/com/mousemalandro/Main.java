package com.mousemalandro;
import javax.swing.*;
import java.awt.*;


class buttonMaker extends JButton {

    int button_width = 130;
    int button_height = 95;


    public buttonMaker(String text, Color cor, int x, int y) {
        super(text);
        this.setBounds(x, y, button_width, button_height);
        this.setBackground(cor);
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setFocusable(false);
        this.repaint();
    }
}


public class Main {


    private static boolean isRunning = false;
    private static buttonMaker startButton;
    private static buttonMaker stopButton;
    private static Thread mouseThread;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth = screenSize.width - 50;
    private static int screenHeight = screenSize.height - 50;



    public static void main(String[] args){

        //Creating the Screen(Size, Config, Title)
        JFrame window = new JFrame("Mouse Malandro");

        int width = 400;
        int height = 400;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(null);


        //Creating the Panels, Labels and Image
        String bgPhoto = "mickey.png";
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, width, height);
        ImageIcon image = new ImageIcon(Main.class.getResource("/mickey.png"));
        JLabel mickey = new JLabel(image);
        mickey.setBounds(25, 0,image.getIconWidth(), image.getIconHeight());
        panel.add(mickey);


        //Creating the Buttons (Red: 0x800000 / Green: 0x556b2f / Blue: 0x0e1b29)
        int y_cor = 252;
        startButton = new buttonMaker("START", new Color(0x556b2f), 25, y_cor);
        stopButton = new buttonMaker("STOP", new Color(0x800000), 245, y_cor);

        //Adding Functionality
        startButton.addActionListener(e -> move_mouse());
        stopButton.addActionListener(e -> stop_mouse());

        //LastDreamer Website Label
        JLabel site = new JLabel("LASTDREAMER.COM");
        site.setForeground(Color.BLACK);
        site.setBackground(Color.WHITE);
        site.setBounds(140, 380, 200, 20);
        panel.add(site);

        window.add(panel);
        panel.add(startButton);
        panel.add(stopButton);
        window.setVisible(true);
    }

    static void move_mouse(){
        if (isRunning) return;
        isRunning = true;

        startButton.setBackground(new Color(0x0e1b29));
        startButton.setText("RUNNING");

        mouseThread = new Thread(() -> {
            try {
                Robot robot = new Robot();
                while (isRunning) {
                    // Mova o mouse aleatoriamente dentro da tela
                    int x = (int) (Math.random() * screenWidth);
                    int y = (int) (Math.random() * screenHeight);
                    robot.mouseMove(x, y);

                    Thread.sleep(4500); // 4.5 seconds wait
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        mouseThread.start();
    }


    private static void stop_mouse(){

        isRunning = false;

        startButton.setBackground(new Color(0x556b2f));
        startButton.setText("START");

    }


}