import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GuessTheMysteryNumbers {
    private static final int MAX_GUESSES = 10;
    private static int count = MAX_GUESSES;

    private JFrame homeFrame;
    private JFrame gameFrame;
    private JFrame resultFrame;

    private JButton mystery1;
    private JButton mystery2;
    private JButton mystery3;
    private JButton mystery4;

    private JButton playButton;
    private JButton answerButton;
    private JLabel backLabel;
    private JButton backButton;

    private DefaultTableModel tableModel;
    private List<Integer> answer;
    private List<List<Integer>> guessHistory;

    public GuessTheMysteryNumbers() {
        createHomeFrame();
    }

    // Create logo untuk di Home page
    private JPanel createMysteryIcon() {
        mystery1 = new JButton("?");
        mystery1.setBackground(new Color(138, 62, 72));
        mystery1.setForeground(Color.WHITE);
        mystery1.setFont(mystery1.getFont().deriveFont(Font.BOLD, 40));
        mystery1.setEnabled(false);
        mystery2 = new JButton("?");
        mystery2.setBackground(new Color(138, 62, 72));
        mystery2.setForeground(Color.WHITE);
        mystery2.setFont(mystery2.getFont().deriveFont(Font.BOLD, 40));
        mystery2.setEnabled(false);
        mystery3 = new JButton("?");
        mystery3.setBackground(new Color(138, 62, 72));
        mystery3.setForeground(Color.WHITE);
        mystery3.setFont(mystery2.getFont().deriveFont(Font.BOLD, 40));
        mystery3.setEnabled(false);
        mystery4 = new JButton("?");
        mystery4.setBackground(new Color(138, 62, 72));
        mystery4.setForeground(Color.WHITE);
        mystery4.setFont(mystery4.getFont().deriveFont(Font.BOLD, 40));
        mystery4.setEnabled(false);  

        JPanel iconsPanel = new JPanel();
        iconsPanel.setLayout(new GridLayout(1,4,5,0));

        iconsPanel.add(mystery1);
        iconsPanel.add(mystery2);
        iconsPanel.add(mystery3);
        iconsPanel.add(mystery4);
        iconsPanel.setBackground(new Color(35,35,35,255));

        return iconsPanel;
    }

    // Home page
    private void createHomeFrame() {
        homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(500, 400);
        homeFrame.getContentPane().setBackground(new Color(35,35,35,255));
        homeFrame.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Guess the Mystery Numbers");
        titleLabel.setForeground(new Color(94, 151, 180));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 5));

        playButton = new JButton("Play a new game");
        playButton.setBackground(new Color(139,78,174,255));
        playButton.setForeground(Color.WHITE);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.dispose();
                createGameFrame();
                startGame();
            }
        });

        JButton rulesButton = new JButton("Guidebook");
        rulesButton.setBackground(new Color(139,78,174,255));
        rulesButton.setForeground(Color.WHITE);
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGuidebook();
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(rulesButton);
        buttonPanel.setBackground(new Color(35,35,35,255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        homeFrame.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.ipady = 20;
        gbc.insets = new Insets(40, 0, 40, 0);
        homeFrame.add(createMysteryIcon(), gbc);

        gbc.gridy = 2;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        homeFrame.add(buttonPanel, gbc);

        homeFrame.setVisible(true);
    }

    // Menu: Guidebook
    private void showGuidebook() {
        JFrame rulesFrame = new JFrame("Guidebook");
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setSize(500, 400);
        rulesFrame.getContentPane().setBackground(new Color(35,35,35,255));
        rulesFrame.setLayout(new GridBagLayout());
    
        JLabel rulesLabel = new JLabel("<html><center><h1>How to Play?</h1></center><br><br>" +
                "1. The Mystery Numbers must be consist of 4 unique digit numbers (1-9).<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;For example: <span style=\"color: green;\">1634 ✔ 7259 ✔</span> <span style=\"color: red;\">0182 ✘ 4444 ✘</span><br>" +
                "2. You have <b>10 chances</b> to guess the Mystery Numbers.<br>" +
                "3. Follow the clues.<br><br>" +
                "<b>Have Fun!</b></html>");
        rulesLabel.setForeground(Color.WHITE);
        rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(139,78,174,255));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesFrame.dispose();
            }
        });
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 20, 10);
        rulesFrame.add(rulesLabel, gbc);
    
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 10, 20, 10);
        rulesFrame.add(okButton, gbc);
    
        rulesFrame.setVisible(true);
    }    

    // Menu: Play a new game
    private void createGameFrame() {
        gameFrame = new JFrame("Play");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 400); // Mengatur ukuran frame menjadi 500x400
    
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(35, 35, 35, 255));
        contentPanel.setLayout(new GridBagLayout());
    
        tableModel = new DefaultTableModel(new Object[]{"Your Guess", "Correct Numbers", "Correct Positions"}, 10) {
            // Override method isCellEditable untuk mencegah pengeditan sel tabel
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengembalikan false untuk mencegah pengeditan sel
            }
        };
        JTable guessesTable = new JTable(tableModel);
        for (int i = 0; i < guessesTable.getColumnCount(); i++) {
            guessesTable.getColumnModel().getColumn(i).setResizable(false);
            guessesTable.getColumnModel().getColumn(i).setCellEditor(null);
        }
    
        JScrollPane scrollPane = new JScrollPane(guessesTable); // Menggunakan JScrollPane untuk mengatur fungsi scroll
        scrollPane.setPreferredSize(new Dimension(380, 180)); // Mengatur ukuran JScrollPane menjadi 380x220

        JLabel chanceLabel = new JLabel("You have " + count + " chances left...");
        chanceLabel.setForeground(Color.WHITE);
        chanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(35, 35, 35, 255));
        inputPanel.setLayout(new GridBagLayout());
    
        JTextField inputField = new JTextField(10);
        answerButton = new JButton("Answer");
        answerButton.setBackground(new Color(139,78,174,255)); //purple
        answerButton.setForeground(Color.WHITE);
        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(inputField.getText());
                inputField.setText("");
                if (count == 1) chanceLabel.setText("You have " + count + " chance left...");
                else chanceLabel.setText("You have " + count + " chances left...");
            }
        });
    
        backLabel = new JLabel("< Back");
        backLabel.setForeground(Color.WHITE);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                gameFrame.dispose();
                homeFrame.setVisible(true);
                restartGame();
            }
        });
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPanel.add(backLabel, gbc); // Button "Back" ditempatkan di posisi paling atas kiri
    
        gbc.gridx = 1;
        inputPanel.add(inputField, gbc);
    
        gbc.gridx = 2;
        inputPanel.add(answerButton, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPanel.add(scrollPane, gbc);

        gbc.gridy = 2;
        contentPanel.add(chanceLabel, gbc);
    
        gbc.gridy = 3;
        contentPanel.add(inputPanel, gbc);
    
        gameFrame.add(contentPanel);
    
        gameFrame.setVisible(true);
    }
    
    // Result page
    private void createResultFrame(String result) {
        resultFrame = new JFrame("Result");
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setSize(500, 400);
        resultFrame.getContentPane().setBackground(new Color(35,35,35,255));
        resultFrame.setLayout(new GridBagLayout());
    
        JLabel resultLabel = new JLabel(result);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 24));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (result.equals("YOU WON!")){
            resultLabel.setForeground(new Color(98,211,129,255));
        } else {
            resultLabel.setForeground(new Color(250,2,84,255));
        }
    
        JLabel answerLabel = new JLabel("The mystery number was: " + answerToString());
        answerLabel.setForeground(Color.WHITE);
        answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        backButton = new JButton("Back to Home");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(139,78,174,255));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();
                createHomeFrame();
                restartGame();
            }
        });
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        resultFrame.add(resultLabel, gbc);
    
        gbc.gridy = 1;
        resultFrame.add(answerLabel, gbc);
    
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        resultFrame.add(backButton, gbc);
    
        resultFrame.setVisible(true);
    }    

    // Method to start a new Game
    private void startGame() {
        playButton.setEnabled(false);
        answerButton.setEnabled(true);
        guessHistory = new ArrayList<>();
        answer = generateAnswer();
        tableModel.setRowCount(0);

        // Spill the Mystery Numbers
        System.out.println(answer);
    }

    // Method to generate the mystery number
    private List<Integer> generateAnswer() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> answer = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(numbers.size());
            answer.add(numbers.get(index));
            numbers.remove(index);
        }

        return answer;
    }

    // Method to check the answer
    private void checkAnswer(String inputText) {
        if (inputText.length() != 4 || !inputText.matches("\\d+")) {
            JOptionPane.showMessageDialog(gameFrame, "Input must be a 4 digit number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Integer> guess = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int digit = Character.getNumericValue(inputText.charAt(i));
            guess.add(digit);
    
            if (digit == 0) {
                JOptionPane.showMessageDialog(gameFrame, "Each digit must be an integer between 1 and 9", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    
        if (guess.size() != guess.stream().distinct().count()) {
            JOptionPane.showMessageDialog(gameFrame, "Each digit must be unique", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int correctNumbers = 0;
        int correctPositions = 0;

        for (int i = 0; i < 4; i++) {
            if (guess.get(i).equals(answer.get(i))) correctPositions++;
            if (answer.contains(guess.get(i))) correctNumbers++;
        }

        count--;
        guessHistory.add(guess);
        tableModel.addRow(new Object[]{inputText, correctNumbers, correctPositions});

        if (correctPositions == 4) {
            gameFrame.dispose();
            createResultFrame("YOU WON!");
        } else if (guessHistory.size() == MAX_GUESSES) {
            gameFrame.dispose();
            createResultFrame("YOU LOSE!");
        }
    }

    // Method to restart
    private void restartGame() {
        count = MAX_GUESSES;
        playButton.setEnabled(true);
        backLabel.setEnabled(false);
        tableModel.setRowCount(0);
    }

    // Method to convert the answer to string
    private String answerToString() {
        StringBuilder sb = new StringBuilder();
        for (Integer digit : answer) {
            sb.append(digit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheMysteryNumbers();
            }
        });
    }
}