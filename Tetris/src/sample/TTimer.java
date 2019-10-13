package sample;

public class TTimer implements Runnable{
    public int lvl;
    private int seconds;
    private int minutes;

    TTimer(){}

    public void __init__(){
        this.seconds = 0;
        this.minutes = 0;
        this.lvl = 0;
    }

    public String getTime(){
        return minutes + ":" + seconds;
    }

    public String getLvl(){
        return this.lvl + "";
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        seconds++;
         if (seconds > 59){
             seconds = 0;
             minutes++;
         }
         if (seconds == 0 && minutes%2 == 0)
             lvl++;

    }
}
