package sample;
import java.util.*;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Tetris extends Application{
    public static final int VELOCITY = 35;
    public static final int SIZE = 35;
    public static int XMAX = SIZE*10;
    public static int YMAX = SIZE*20;
    public static int [][] MATRIX = new int[XMAX/SIZE][YMAX/SIZE];
    public static int score = 0;
    public static int top = 0;

    private static TTimer timer;
    private static Form first;
    private static Form second = Controller.makeForm();
    private static Form last = Controller.makeForm();
    private static Pane pane = new Pane();
    private static Scene scene = new Scene(pane, XMAX + (SIZE*6), YMAX);
    private static boolean gamming = true;
    private static int lines = 0;
    private static int lvl = 1;

    @Override
    public void start(Stage stage) throws Exception {
        /*String uri = new File("C:\\Users\\thelo\\Desktop\\Java\\POO\\TetrisGame\\Tetris\\MusicCyberpunk2077.mp3").toURI().toString();
        MediaPlayer song = new MediaPlayer(new Media(uri));
        song.setCycleCount(MediaPlayer.INDEFINITE);
        song.play();*/

        for (int[] a:MATRIX)
            Arrays.fill(a, 0);

        Line sep = new Line(XMAX, 0, XMAX+1, YMAX);
        Line sep2 = new Line(XMAX, (SIZE*7), (XMAX + (SIZE*6)), (SIZE*7));
        Line sep3 = new Line(XMAX, (SIZE*13), (XMAX + (SIZE*6)), (SIZE*13));

        Text timeTxt = new Text("Time: ");
        Text lvlTxt =  new Text("Lvl: ");
        Text scoreTxt = new Text("Score: ");
        Text linesTxt = new Text("Lines: ");

        timeTxt.setX(XMAX + 10);
        timeTxt.setY(SIZE * 14);
        timeTxt.setStyle("-fx-font: 20 arials");

        lvlTxt.setX(XMAX + 10);
        lvlTxt.setY(SIZE * 16);
        lvlTxt.setStyle("-fx-font: 20 arials");
        lvlTxt.setFill(Color.RED);

        scoreTxt.setX(XMAX + 10);
        scoreTxt.setY(SIZE * 18);
        scoreTxt.setStyle("-fx-font: 20 arials");

        linesTxt.setX(XMAX + 10);
        linesTxt.setY(SIZE * 20);
        linesTxt.setStyle("-fx-font: 20 arials");

        pane.getChildren().addAll(sep, sep2, sep3, timeTxt, lvlTxt, scoreTxt, linesTxt);

        Form append = second;
        pane.getChildren().addAll(append.a, append.b, append.c, append.d);
        move(append);

        first = append;
        second = last;
        System.out.println(first.getType());

        last = Controller.makeForm();
        Controller.placeFormOut2(last);
        Controller.placeFormOut1(second);
        pane.getChildren().addAll(second.a, second.b, second.c, second.d,
                                  last.a, last.b, last.c, last.d);


        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (first.a.getY() == 0 || first.a.getY() == 0 || first.a.getY() == 0 || first.a.getY() == 0)
                            top++;
                        else
                            top = 0;
                        if (top == 2){  // Game Over
                            Text gameover = new Text("GAME OVER");
                            gameover.setFill(Color.RED);
                            gameover.setStyle("-fx-font: 70 arials;");
                            gameover.setX(10);
                            gameover.setY(250);
                            pane.getChildren().add(gameover);
                            gamming = false;
                        }
                        if (top == 15)
                            System.exit(0);
                        if (gamming){
                            moveDown(first);
                            //timeTxt.setText("Time: " + timer.getTime());
                            //lvlTxt.setText("Level: "+ timer.getLvl());
                            scoreTxt.setText("Score: " + score);
                            linesTxt.setText("Lines: " + lines);
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    public void move(Form form){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case RIGHT:
                        Controller.moveRight(form);
                        break;
                    case DOWN:
                        moveDown(form);
                        break;
                    case LEFT:
                        Controller.moveLeft(form);
                        break;
                    case UP:
                        turnRight(form);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void turnRight(Form form){
        int angle = form.rotation;
        Rectangle a = form.a, b = form.b, c = form.c, d = form.d;
        switch (form.getType()){
            case 2:
                if (angle == 1 && check(a, 1, -1) && check(c, -1, -1) && check(d, -2, -2)) {
                    MoveUp(form.d);
                    MoveUp(form.d);

                    MoveUp(form.c);
                    MoveRight(form.c);

                    MoveDown(form.a);
                    MoveLeft(form.a);

                    form.rotate();
                    break;
                }
                if (angle == 2 && check(a, -1, -1) && check(c, -1, 1) && check(d, -2, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);

                    MoveLeft(form.b);

                    MoveDown(form.c);

                    MoveRight(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(a, -1, 1) && check(c, 1, 1) && check(d, 2, 2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);

                    MoveDown(form.c);
                    MoveLeft(form.c);

                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(a, 1, 1) && check(c, 1, -1) && check(d, 2, -2)) {
                    MoveDown(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);

                    MoveRight(form.b);

                    MoveUp(form.c);

                    MoveLeft(form.d);
                    form.rotate();
                    break;
                }
                break;
            case 3:
                if (angle == 1 && check(a, 1, -1) && check(c, 1, 1) && check(b, 2, 2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.rotate();
                    break;
                }
                if (angle == 2 && check(a, -1, -1) && check(b, 2, -2) && check(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(a, -1, 1) && check(c, -1, -1) && check(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(a, 1, 1) && check(b, -2, 2) && check(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.rotate();
                    break;
                }
                break;
            case 4:
                break;
            case 5:
                if (angle == 1 && check(a, -1, -1) && check(c, -1, 1) && check(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 2 && check(a, 1, 1) && check(c, 1, -1) && check(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(a, -1, -1) && check(c, -1, 1) && check(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(a, 1, 1) && check(c, 1, -1) && check(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.rotate();
                    break;
                }
                break;
            case 7:
                if (angle == 1 && check(a, 1, 1) && check(d, -1, -1) && check(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.rotate();
                    break;
                }
                if (angle == 2 && check(a, 1, -1) && check(d, -1, 1) && check(c, 1, 1)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(a, -1, -1) && check(d, 1, 1) && check(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(a, -1, 1) && check(d, 1, -1) && check(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.rotate();
                    break;
                }
                break;
            case 6:
                if (angle == 1 && check(b, 1, 1) && check(c, -1, 1) && check(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 2 && check(b, -1, -1) && check(c, 1, -1) && check(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(b, 1, 1) && check(c, -1, 1) && check(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(b, -1, -1) && check(c, 1, -1) && check(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.rotate();
                    break;
                }
                break;
            case 1:
                if (angle == 1 && check(a, 2, 2) && check(b, 1, 1) && check(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 2 && check(a, -2, -2) && check(b, -1, -1) && check(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 3 && check(a, 2, 2) && check(b, 1, 1) && check(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.rotate();
                    break;
                }
                if (angle == 4 && check(a, -2, -2) && check(b, -1, -1) && check(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.rotate();
                    break;
                }
                break;
        }
    }

    private void removeRows(Pane pane){
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newRects = new ArrayList<Node>();
        int full = 0;
        int cantlines = 0;

        for (int i=0; i<MATRIX[0].length; i++){
            for (int j=0; j<MATRIX.length; j++){
                if (MATRIX[j][i] == 1)
                    full++;
            }
            if (full == MATRIX.length)
                lines.add(i+lines.size());
            full = 0;
        }
        if (lines.size() > 0) {
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                Tetris.score++;
                Tetris.lines++;
                cantlines++;
                if (cantlines==3)
                    Tetris.score++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MATRIX[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newRects.add(node);
                }

                for (Node node : newRects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MATRIX[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newRects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MATRIX[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
        }
    }


    private void MoveDown(Rectangle rect) {
        if (rect.getY() + SIZE < YMAX)
            rect.setY(rect.getY() + SIZE);

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + SIZE <= XMAX - SIZE)
            rect.setX(rect.getX() + SIZE);
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - SIZE >= 0)
            rect.setX(rect.getX() - SIZE);
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - SIZE > 0)
            rect.setY(rect.getY() - SIZE);
    }

    private void moveDown(Form form) {
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MATRIX[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MATRIX[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MATRIX[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MATRIX[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            removeRows(pane);

            Form append = second;
            second = last;
            last = Controller.makeForm();
            Controller.placeFormGame(append);
            first = append;
            pane.getChildren().addAll(append.a, append.b, append.c, append.d);
            Controller.placeFormOut1(second);
            Controller.placeFormOut2(last);
            pane.getChildren().addAll(second.a, second.b, second.c, second.d, last.a, last.b, last.c, last.d);
            move(append);
        }
        if (form.a.getY() + VELOCITY < YMAX && form.b.getY() + VELOCITY < YMAX && form.c.getY() + VELOCITY < YMAX
                && form.d.getY() + VELOCITY < YMAX) {
            int movea = MATRIX[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
            int moveb = MATRIX[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
            int movec = MATRIX[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
            int moved = MATRIX[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + VELOCITY);
                form.b.setY(form.b.getY() + VELOCITY);
                form.c.setY(form.c.getY() + VELOCITY);
                form.d.setY(form.d.getY() + VELOCITY);
            }
        }
    }

    private boolean moveA(Form form) {
        return (MATRIX[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MATRIX[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MATRIX[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MATRIX[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
    }

    private boolean check(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * SIZE <= XMAX - SIZE;
        if (x < 0)
            xb = rect.getX() + x * SIZE >= 0;
        if (y >= 0)
            yb = rect.getY() - y * SIZE > 0;
        if (y < 0)
            yb = rect.getY() + y * SIZE < YMAX;
        return xb && yb && MATRIX[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }

    public static void main(String[] args) { launch(args); }
}

