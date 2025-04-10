package Objects;
public class TempInSide {
    double tempOut;
    double tempIn = 20;

    public TempInSide(TempOutSide tmp) {
        tempOut = tmp.temp;

        if(tempOut < 10)
            tempIn -= 3;
        else if (tempOut < 15)
            tempIn -= 2;
        else if (tempOut < 17)
            tempIn -= 1;

        System.out.println("Temp in: " + tempIn);
    }



}
