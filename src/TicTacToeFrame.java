import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private final JButton quitButton = new JButton("Quit");
    private TicTacToe game;

    public TicTacToeFrame() {
        game = new TicTacToe();
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 84);
        Dimension buttonSize = new Dimension(100, 100);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setPreferredSize(buttonSize); // Setting the preferred size
                buttons[row][col].setFont(buttonFont);
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].addActionListener(e -> handleButtonClick(finalRow, finalCol));
                boardPanel.add(buttons[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton, BorderLayout.SOUTH);

        pack();
        setSize(600, 600);
        setVisible(true);
    }


    private void handleButtonClick(int row, int col) {
        if (!game.isValidMove(row, col)) {
            JOptionPane.showMessageDialog(this, "This space is already taken. Please choose another space.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
            return;
        }

        buttons[row][col].setText(game.getCurrentPlayer());
        
        game.makeMove(row, col);

        if (game.isGameOver()) {
            String message = game.getWinner().equals("Tie") ? "It's a tie!" : "Player " + game.getWinner() + " wins!";
            JOptionPane.showMessageDialog(this, message);
            int response = JOptionPane.showConfirmDialog(this, "Do you want to play another game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }



    private void resetGame() {
        game = new TicTacToe();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}
