package Objects;

public class TemperatureSensorOutside {
    double temp;

    public TemperatureSensorOutside(){
        int max = 17;
        int min = 5;
        int range = max - min + 1;

        double tmp1 = (double) (Math.random() * range) + min;
        temp = tmp1;

        System.out.printf("Aktualna temp na zewnątrz to: "+"%.1f%n",temp);
    }

    public static class TempInSide {
        double tempOut;
        double tempIn = 20;

        TempInSide(TempOutSide tmp) {
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
}
