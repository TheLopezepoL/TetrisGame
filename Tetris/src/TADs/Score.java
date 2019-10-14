package TADs;

public class Score {
    public int score;
    private int id;

    Score(){
        this.score = 0;
        this.id = 0;
    }

    Score(int score, int id){
        this.score = score;
        this.id = id;
    }

    public int getId(){
        return id;
    }

}
