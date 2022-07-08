import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    JFrame frame;
    Color color = new Color(89, 3, 3, 252);

    public Menu() {
        frame = new JFrame();
        frame.setBounds(800, 100, 480, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setScreen();
        frame.setVisible(true);
    }

    private void setScreen() {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new GridLayout(6, 1));
        frame.add(panel);

        addChessIcon(panel);
        panel.add(new JLabel());
        addSinglePlayBt(panel);
        addTwoPlayBt(panel);
        addSavedGameBt(panel);
    }

    private void addChessIcon(JPanel panel) {
        ImageIcon chess = new ImageIcon(getClass().getResource("imageIcon/chess.jpg"));
        JLabel label = new JLabel(chess);
        panel.add(label);
    }

    private void addSinglePlayBt(JPanel panel){
        ImageIcon singlePlay = new ImageIcon(getClass().getResource("imageIcon/singlePlay.jpg"));
        JPanel p = new JPanel();
        p.setBackground(color);

        JButton button = new JButton("", singlePlay);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startGameSinglePlayer();
            }
        });

        button.setPreferredSize(new Dimension(400, 82));
        p.add(button);
        panel.add(p);
    }

    private void startGameSinglePlayer(){
        String[] options = {"Black", "white"};

        int x = JOptionPane.showOptionDialog(null, "Chose your Color Please",
                "Chose Color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        try {
            new Board(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        frame.dispose();
    }

    private void addTwoPlayBt(JPanel panel){
        ImageIcon twoPlay = new ImageIcon(getClass().getResource("imageIcon/twoPlay.jpg"));
        JPanel p = new JPanel();
        p.setBackground(color);

        JButton button = new JButton("", twoPlay);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(actionEvent.getActionCommand().equals("")){
                    startGameTwoPlayer();
                }
            }
        });

        button.setPreferredSize(new Dimension(400, 90));
        p.add(button);
        panel.add(p);
    }

    private void startGameTwoPlayer(){
        frame.dispose();
        try {
            new Board();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.dispose();
    }

    private void addSavedGameBt(JPanel panel){
        ImageIcon singlePlay = new ImageIcon(getClass().getResource("imageIcon/saveGame.jpg"));
        JPanel p = new JPanel();
        p.setBackground(color);

        JButton button = new JButton("", singlePlay);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startSavedGame();
            }
        });

        button.setPreferredSize(new Dimension(400, 82));
        p.add(button);
        panel.add(p);
    }

    private void startSavedGame(){
        int[][] board = new int[8][8];
        Scanner scanner;
        try {
            scanner = new Scanner(new File("save.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!scanner.hasNextInt()){
            JOptionPane.showMessageDialog(null, "there is no save game");
            return;
        }

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                board[i][j] = scanner.nextInt();

        int p = scanner.nextInt();

        if(p == 10){
            JOptionPane.showMessageDialog(null, "there is no save game");
            return;
        }

        try {
            new Board(board, scanner.nextBoolean(), p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.dispose();
    }
}
