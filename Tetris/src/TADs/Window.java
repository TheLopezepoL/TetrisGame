package TADs;

import javax.swing.JFrame;

public class Window{
    public static final int WIDTH = 445, HEIGHT = 629;

    private Board board;
    private Title title;
    private JFrame window;
    private BestScores bestScores;
    private Board savedBoard;

    public Window(){

        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        board = new Board();
        title = new Title(this);

        window.addKeyListener(board);
        window.addMouseMotionListener(title);
        window.addMouseListener(title);

        window.add(title);

        window.setVisible(true);
    }
    public void startTetris(){
        window.remove(title);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.startGame();
        window.revalidate();
    }

    public static void main(String[] args) {
        new Window();
    }

}
