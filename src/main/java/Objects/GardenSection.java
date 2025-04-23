package Objects;

import Design.GardenDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GardenSection {
    private Vector<WaterPump> waterPumpsVector;
    private String name;
    private JButton button;
    private comment com;
    private int closeHour = -1;
    private int duration = 5;
    private GardenDesign gardenDesign;
    private int openTimeHour = -1;
    private Vector<GardenSection> SectionsVector;

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

    public void changeStatus(boolean comPrint) {
        StringBuilder sb = new StringBuilder();         // coment
        for(WaterPump waterPump : waterPumpsVector) {
            waterPump.changeStatus();
            //setButtonColor();
            sb.append(waterPump.getName() + ", ");
        }

        if(comPrint) {
            if(waterPumpsVector.getFirst().getStatus() == true) {
                sb.append("opned.");
            }
            else{
                sb.append("closed.");
            }

            String result = sb.toString();
            com.setComentCom1(result);
        }
        }



    public void setButtonColorReverse(){
        if(waterPumpsVector.size() != 0 && waterPumpsVector.getFirst().getStatus() == false) {
            button.setBackground(Color.GREEN);
        }
        else {
            button.setBackground(Color.RED);
        }
    }

    public void openScheduleManual(int hour, boolean printCom) {
        closeHour = hour + duration;
        StringBuilder sb = new StringBuilder();
        for(WaterPump waterPump : waterPumpsVector) {
            waterPump.setOpen();
            sb.append(waterPump.getName() + ", ");
        }
        if(printCom) {
            com.setComentCom1(sb.toString() + " opned for " + duration + " hours.");
        }
        if(name == "AllSection") {
            for(GardenSection gardenSection : SectionsVector) {
                gardenSection.setColorButton(Color.ORANGE);
                gardenSection.setCloseHour(closeHour);
            }
        }
            button.setBackground(Color.ORANGE);


        System.out.println("close time: " + closeHour + " duration: " + duration);
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void closeSection(){
        for(WaterPump waterPump : waterPumpsVector) {
            waterPump.setClose();
            //System.out.println(waterPump.getName() + ", " + waterPump.getStatus());
            //System.out.println("Szeforzaa");
            button.setBackground(Color.RED);
            closeHour = -1;
        }
    }
    public void openSection(){
        for(WaterPump waterPump : waterPumpsVector) {
            waterPump.setOpen();
        }

    }

    public boolean getFirstPumpStatus(){
        return waterPumpsVector.getFirst().getStatus();
    }

    public void setGardenDesign(GardenDesign gardenDesign) {
        this.gardenDesign = gardenDesign;
    }

    public boolean hasPump(){
        if(waterPumpsVector.size() > 0){
            return true;
        }
        return false;
    }

    public void setOpenTimeHour(int hour) {
        openTimeHour = hour;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addVector(Vector<WaterPump> vector) {
        waterPumpsVector.addAll(vector);
    }

    public Vector<WaterPump> getVector() {
        return waterPumpsVector;
    }

    public int getDuration() {
        return duration;
    }

    public int getOpenTimeHour() {
        return openTimeHour;
    }

    public void setColorButton(Color color){
        button.setBackground(color);
    }


    public void addGardenSectionVector(Vector<GardenSection> gardenSectionVector) {
       SectionsVector = gardenSectionVector;
    }

    public void setCloseHour(int closeHour) {
        this.closeHour = closeHour;
    }

    public JButton getButton() {
        return button;
    }
}
