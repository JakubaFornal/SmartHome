package Objects;
public class TemperatureSensorInside {
    double tmpOut;
    double tmpInside = 20;

    public TemperatureSensorInside(){


    }
    TemperatureSensorInside(TemperatureSensorOutside tmp){
        tmpOut = tmp.temp;
        if(tmpOut<15){
            tmpInside -=2;
            System.out.println("Aktualna temperatura wwnatrz spowodowana spadkiem temp. na zewnatrz: "+tmpInside);
        }
    }

    public static class TempOutSide {
        double temp;

        TempOutSide(){
            int min = 1;
            int max = 20;
            int range = max - min;
            double tmp1 = min + (Math.random() * range);
            temp = tmp1;

            System.out.println("temp OutSide: " + temp);
        }
    }
}
