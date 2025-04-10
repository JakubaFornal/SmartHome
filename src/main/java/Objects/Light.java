package Objects;

import javax.swing.*;
import java.awt.*;

public class Light {
    private String name;
    //private String room;
    private boolean status;
    private JButton statusButton;
    private comment com;

    public Light(String name, JButton statusButton, comment com) {
        this.name = name;
        this.statusButton = statusButton;
        this.com = com;
    }

    public String getName() {
        return name;
    }

    public void setJButton(JButton statusButton) {
        this.statusButton = statusButton;
    }

    public String getStatus() {
        return status == true ? "Open" : "Closed";
    }

    public String getStatusShort() {
        return status == true ? "ON" : "OFF";
    }

    public void changeButtonColor(){
        if(status){
            statusButton.setBackground(Color.green);
        }
        else{
            statusButton.setBackground(Color.red);
        }
    }

    // buttons
    public void changeStatus(){
        status  = !status;
        statusButton.setText(getName());
        com.setComentCom1("Light " + getName() + " is " + getStatusShort());
        changeButtonColor();
    }

}
