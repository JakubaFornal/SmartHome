package Objects;

import javax.swing.*;
import java.awt.*;

public class Window {
    String nazwa;
    boolean open = false;
    int openHour = -1;
    int tempClose = -1;
    int closeHour = -1;
    boolean blinds;
    private JButton statusButton;
    private comment com;
    int ventilationDuration = 6;

    public Window(String nazwa, JButton statusButton, comment com) {
        this.nazwa = nazwa;
        this.statusButton = statusButton;
        this.com = com;
    }

    public void close() {
        this.open = false;
    }

    //opening on schedule
    public void setOpenHour(int openHour) {
        this.openHour = openHour;
    }

    public int getOpenHour() {
        return openHour;
    }

    public void openSchedule(int hour) {            // ventilation on time open
        if(hour == this.openHour){
            this.open = true;
            //System.out.println(this.nazwa + " jest " + this.open);
            this.tempClose = (hour + ventilationDuration) % 24;
            System.out.println("tempClose: " + tempClose);
            statusButton.setBackground(Color.GREEN);
            com.setComentCom1(nazwa + " open auto for " + ventilationDuration + " hours.");
            statusButton.setBackground(Color.ORANGE);
        }
    }

    public void openScheduleManual(int hour) {      // ventilation manual open
            this.open = true;
            //System.out.println(this.nazwa + " jest " + this.open);
            this.tempClose = (hour + ventilationDuration) % 24;
            statusButton.setBackground(Color.GREEN);

    }

    public void closeSchedule(int hour) {           // close on time
        if(hour == this.tempClose || hour == this.closeHour){
            this.open = false;
            statusButton.setBackground(Color.RED);
            System.out.println(statusButton.getText() + " closed schedule");
        }
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(int hour){
        this.closeHour = hour;
    }       //set Hour close on Time

    public void closeOnTime(int hour) {           //closing on certain time
        if(hour == this.closeHour && this.open == true){
            this.open = false;
            //System.out.println(this.nazwa + " jest " + this.open);
        }
    }




    // Swing //
    public String getNazwa(){
        return nazwa;
    }

    public String getStatus(){
        return open ? "ON" : "OFF";
    }


    public boolean isOpenBoolean(){
        return open;
    }


        // buttons
    public void changeStatus(){
        open = !open;
        statusButton.setText(buttonText());
        com.setComentCom1("Window " + getNazwa() + " is " + getStatus());
        changeButtonColor();
    }

    public void changeButtonColor(){
        if(open){
            statusButton.setBackground(Color.green);
        }
        else{
            statusButton.setBackground(Color.red);
        }
    }

    public String buttonText(){
        return getNazwa();
    }

    public String getButtonText(){return statusButton.getText();}

    public void setJButton (JButton Button){
        this.statusButton = Button;
    }



    // Ventialation
    public void setVentilationDuration(int duration){
        this.ventilationDuration = duration;
    }

    public int getVentilationDuration(){
        return ventilationDuration;
    }





}
