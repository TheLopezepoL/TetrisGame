package sample;
import javafx.scene.shape.*;

public class Controller {
    private static final int VELOCITY = Tetris.VELOCITY;
    private static final int SIZE = Tetris.SIZE;
    private static int XMAX = Tetris.XMAX;
    private static int YMAX = Tetris.YMAX;
    private static int [][] MATRIX = Tetris.MATRIX;

    public static void moveRight (Form form){
        if (form.a.getX() + VELOCITY <= XMAX - SIZE && form.b.getX() + VELOCITY <= XMAX - SIZE &&
                form.c.getX() + VELOCITY <= XMAX - SIZE && form.d.getX() + VELOCITY <= XMAX - SIZE) {
            int movea = MATRIX[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveb = MATRIX[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int movec = MATRIX[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moved = MATRIX[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() + VELOCITY);
                form.b.setX(form.b.getX() + VELOCITY);
                form.c.setX(form.c.getX() + VELOCITY);
                form.d.setX(form.d.getX() + VELOCITY);
            }
        }
    }

    public static void moveLeft (Form form){
        if (form.a.getX() - VELOCITY >= 0 && form.b.getX() - VELOCITY >= 0 &&
                form.c.getX() - VELOCITY >= 0 && form.d.getX() - VELOCITY >= 0) {
            int movea = MATRIX[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveb = MATRIX[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int movec = MATRIX[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moved = MATRIX[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - VELOCITY);
                form.b.setX(form.b.getX() - VELOCITY);
                form.c.setX(form.c.getX() - VELOCITY);
                form.d.setX(form.d.getX() - VELOCITY);
            }
        }
    }

    public static Form makeForm(){
        short type = (short) (Math.random() * 9);
        Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1),
                c = new Rectangle(SIZE-1, SIZE-1), d = new Rectangle(SIZE-1, SIZE-1);

        switch (type){
            case 1:  // I
                a.setX((XMAX / 2) - (SIZE * 2));
                b.setX((XMAX / 2) - SIZE);
                c.setX(XMAX / 2);
                d.setX((XMAX / 2) + SIZE);
                break;
            case 2:  // J
                a.setX(SIZE * 5);
                b.setX(SIZE * 4);
                c.setX(SIZE * 3);
                d.setX(SIZE * 3);
                d.setY(SIZE);
                break;
            case 3:  // L
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                break;
            case 4:  // O
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2 - SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                break;
            case 5:  // S
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                break;
            case 6:  // Z
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2 + SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 + (SIZE * 2));
                d.setY(SIZE);
                break;
            case 7:  // T
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                break;
            default:  // Si no funciona el random
                return makeForm();
        }
        return new Form(a, b, c, d, type);
    }

    public static void placeFormOut1(Form form){
        Rectangle a = form.a, b = form.b, c = form.c, d = form.d;
        switch (form.getType()){
            case 1:  // I
                a.setX(XMAX+SIZE);
                a.setY(SIZE * 5);
                b.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 5);
                c.setX(XMAX + (SIZE*3));
                a.setY(SIZE * 5);
                d.setX(XMAX + (SIZE*4));
                a.setY(SIZE * 5);
                break;
            case 2:  // J
                a.setX(XMAX + (SIZE*3));
                a.setY(SIZE * 4);
                b.setX(XMAX + (SIZE*3));
                b.setY(SIZE * 5);
                c.setX(XMAX + (SIZE*3));
                c.setY(SIZE * 6);
                d.setX(XMAX + (SIZE*2));
                d.setY(SIZE * 6);
                break;
            case 3:  // L
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 4);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 5);
                c.setX(XMAX + (SIZE*2));
                c.setY(SIZE * 6);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 6);
                break;
            case 4:  // O
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 4);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 5);
                c.setX(XMAX + (SIZE*3));
                c.setY(SIZE * 4);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 5);
                break;
            case 5:  // S
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 4);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 5);
                c.setX(XMAX + SIZE);
                c.setY(SIZE * 5);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 4);
                break;
            case 6:  // Z
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 4);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 5);
                c.setX(XMAX + SIZE);
                c.setY(SIZE * 4);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 5);
                break;
            case 7:  // T
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 5);
                b.setX(XMAX + (SIZE*3));
                b.setY(SIZE * 5);
                c.setX(XMAX + (SIZE*4));
                c.setY(SIZE * 5);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 4);
                break;
            default:
                break;
        }
    }

    public static void placeFormOut2(Form form){ // (9, 10, 11, 12) - (3, 4, 5, 6)
        Rectangle a = form.a, b = form.b, c = form.c, d = form.d;
        switch (form.getType()){
            case 1:  // I
                a.setX(XMAX+SIZE);
                a.setY(SIZE * 11);
                b.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 11);
                c.setX(XMAX + (SIZE*3));
                a.setY(SIZE * 11);
                d.setX(XMAX + (SIZE*4));
                a.setY(SIZE * 11);
                break;
            case 2:  // J
                a.setX(XMAX + (SIZE*3));
                a.setY(SIZE * 10);
                b.setX(XMAX + (SIZE*3));
                b.setY(SIZE * 11);
                c.setX(XMAX + (SIZE*3));
                c.setY(SIZE * 12);
                d.setX(XMAX + (SIZE*2));
                d.setY(SIZE * 12);
                break;
            case 3:  // L
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 10);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 11);
                c.setX(XMAX + (SIZE*2));
                c.setY(SIZE * 12);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 12);
                break;
            case 4:  // O
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 10);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 11);
                c.setX(XMAX + (SIZE*3));
                c.setY(SIZE * 10);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 11);
                break;
            case 5:  // S
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 10);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 11);
                c.setX(XMAX + SIZE);
                c.setY(SIZE * 11);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 10);
                break;
            case 6:  // Z
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 10);
                b.setX(XMAX + (SIZE*2));
                b.setY(SIZE * 11);
                c.setX(XMAX + SIZE);
                c.setY(SIZE * 10);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 11);
                break;
            case 7:  // T
                a.setX(XMAX + (SIZE*2));
                a.setY(SIZE * 11);
                b.setX(XMAX + (SIZE*3));
                b.setY(SIZE * 11);
                c.setX(XMAX + (SIZE*4));
                c.setY(SIZE * 11);
                d.setX(XMAX + (SIZE*3));
                d.setY(SIZE * 12);
                break;
            default:
                break;
        }
    }

    public static void placeFormGame(Form form){
        Rectangle a = form.a, b = form.b, c = form.c, d = form.d;
        switch (form.getType()){
            case 1:  // I
                a.setX((XMAX / 2) - (SIZE * 2));
                b.setX((XMAX / 2) - SIZE);
                c.setX(XMAX / 2);
                d.setX((XMAX / 2) + SIZE);
                break;
            case 2:  // J
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                break;
            case 3:  // L
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                break;
            case 4:  // O
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2 - SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                break;
            case 5:  // S
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                break;
            case 6:  // Z
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2 + SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 + (SIZE * 2));
                d.setY(SIZE);
                break;
            case 7:  // T
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                b.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                break;
            default:
                break;
        }
    }
}
