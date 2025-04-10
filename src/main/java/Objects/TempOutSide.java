package Objects;
public class TempOutSide {
    double temp;

    public TempOutSide(){
        int min = 1;
        int max = 20;
        int range = max - min;
        double tmp1 = min + (Math.random() * range);
        temp = tmp1;

        System.out.println("temp OutSide: " + temp);
    }
}
