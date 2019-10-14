package TADs;

public class BestScores {
    private Score[] scores = {new Score(), new Score(), new Score(), new Score(), new Score(), new Score(), new Score(),
            new Score(), new Score(), new Score(), new Score()};
    public int nxtId = 0;

    BestScores(){}

    public void addConsecutivo(){
        this.nxtId++;
    }

    public void addScore(Score score){
        this.scores[10] = score;
        quickSort();
    }

    public void print(){
        for (Score score:scores)
            System.out.println("Score: "+score.score+"\tName: "+score.getId());
    }

    public void quickSort(){
        quickSort(0, 10);
    }

    public void quickSort(int lo, int ho){
        int t, l=lo, h=ho, mid;
        if(ho>lo) {
            mid=scores[(lo+ho)/2].score; // saca el pivote
            while(l<h){ // condicion de parada, cuando el j>i, o l>h
                // coloca l en el próximo mayor a mid en la izquierda
                while((l<ho)&&(scores[l].score<mid))  ++l;

                // coloca h en el próximo menor a mid en la derecha
                while((h>lo)&&(scores[h].score>mid))  --h;

                if(l<=h) {
                    // cambia l por h y los pasa al siguiente y anterior
                    t = scores[l].score;
                    scores[l] = scores[h];
                    scores[h].score = t;
                    ++l;
                    --h;
                }
            }

            // llamadas recursivas, el mismo arreglo pero con límites distintos
            if(lo<h) quickSort(lo,h);

            if(l<ho) quickSort(l,ho);

        }
    }



}
