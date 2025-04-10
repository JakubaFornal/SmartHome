package Objects;

public class Heating {
    double tmpOut;
    double tmpIn;
    int power = 0;

    public Heating(TempOutSide tmpOut, TempInSide tmpIn) {
        this.tmpIn = tmpIn.tempIn;
        this.tmpOut = tmpOut.temp;
        System.out.println("Moc Grzejnika przed zmianą "+power);
    }

    public void changeHeating(){
        if(tmpIn < 20){
            power += 2;
            System.out.println("Z powodu spadku tempeatury wewnętrz zwiększono moc ogrzewania do: " + power);
        }
        else{
            System.out.println("Temperatura na zewnątrz nie jest zbyt wysooka, nie zwiększono mocy ogrzewania.");
        }
    }


    public void incresePower(int pwr){
        power += pwr;
        System.out.println("Moc grzejnika po zmianie "+power);
    }

    void decreasePower(int pwr){
        power -= pwr;
        System.out.println("Moc grzejnika po zmianie "+power);
    }

}
