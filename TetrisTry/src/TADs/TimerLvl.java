package TADs;

public class TimerLvl extends Thread {
    private boolean running;
    private boolean pause;
    Board board;

    TimerLvl(){}

    public void __init__(Board board){
        this.running = true;
        this.pause = false;
        this.board = board;
    }

    public void run(){
        while (running){
            if (board.getSec()%60 == 0){
                if ((board.getSec()/60)%2 == 0)
                    board.addLvl();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.addSec();
            //System.out.println(board.getSec());
            //System.out.println(board.getLvl());
            while (pause){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void changePause(){
        this.pause = !this.pause;
    }
}
