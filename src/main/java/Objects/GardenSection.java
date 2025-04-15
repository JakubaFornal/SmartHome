package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GardenSection {
    private Vector<WaterPump> waterPumpsVector;
    private String name;
    private JButton button;
    private comment com;

    public GardenSection(String name, JButton button, comment com) {
        this.name = name;
        waterPumpsVector = new Vector<>();
        this.button = button;
        this.com = com;
    }

    public void addWaterPump(WaterPump waterPump) {
        waterPumpsVector.add(waterPump);
        waterPump.setSection(this);
    }

    public String getName() {
        return name;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void changeStatus() {
        StringBuilder sb = new StringBuilder();         // coment
        for(WaterPump waterPump : waterPumpsVector) {
            waterPump.changeStatus();
            setButtonColor();
            sb.append(waterPump.getName() + ", ");
        }
        if(waterPumpsVector.getFirst().getStatus() == true) {
            sb.append("opned.");
        }
        else{
            sb.append("closed.");
        }

        String result = sb.toString();
        com.setComentCom1(result);
    }

    public void setButtonColor(){
        if(waterPumpsVector.getFirst().getStatus() == true) {
            button.setBackground(Color.GREEN);
        }
        else {
            button.setBackground(Color.RED);
        }
    }


}
