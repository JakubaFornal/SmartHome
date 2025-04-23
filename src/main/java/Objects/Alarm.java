package Objects;
import javax.swing.*;
import java.util.Vector;

public class Alarm {
    boolean active = false;
    int ActiveTimeAlarm = 22;
    int DeactiveTimeAlarm = 6;
    boolean interrupted = false;
    boolean alertMode = false;
    boolean notifyPolice = false;   // Other Settings
    boolean turnAllLights = false;
    boolean turnOnSyrene = false;
    boolean notifiedAlarm = false;
    private Vector<Light> lightVector;

    private JLabel statusLabel;         // Design
    private JButton alarmButton;
    comment comment;

    public Alarm(JLabel statusLabel, JButton alarmButton, comment comment) {
        this.statusLabel = statusLabel;
        this.alarmButton = alarmButton;
        this.comment = comment;
    }


    //Active on Time
    public void setActiveTimeAlarm(int hour) {
        ActiveTimeAlarm = hour;
    }
    public void setDeactiveTimeAlarm(int hour){DeactiveTimeAlarm = hour;}

    public void activateAlarm(int hour){
        if(hour == ActiveTimeAlarm && !active){
            active = true;
            comment.setComentCom1(hour + ":00 Alarm Activated automatically.");
            this.changeStatusv2();
            turnOffLights();
        }
    }

    //Deactive on time
    public void deactivateAlarm(int hour){
        if(hour == DeactiveTimeAlarm && active){
            active = false;
            comment.setComentCom1(hour + ":00 Alarm Deactivated automatically");
            this.changeStatusv2();

        }
    }

    // change status
    public void changeStatus(){
        if(active){
            active = false;
            comment.setComentCom1("Alarm deactivated");
            alarmButton.setText(this.getStatusButton());
            statusLabel.setText(this.getStatus());
        }
        else{
            active = true;
            comment.setComentCom1("Alarm activated");
            alarmButton.setText(this.getStatusButton());
            statusLabel.setText(this.getStatus());
            turnOffLights();
        }
        notifiedAlarm = false;
        interrupted = false;
    }

    public void changeStatusv2(){
            alarmButton.setText(this.getStatusButton());
            statusLabel.setText(this.getStatus());
    }

    public void setInterrupt(){
            interrupted = true;
            //System.out.println("kuba to sigma");
    }

    //Alarm interrupted
    public void interruptedCheck(){
        if(interrupted){
            System.out.println("interrupted");
        }
        if(active && interrupted && !notifiedAlarm){
            comment.setComentCom1("!!!!!! Alarm Alarm Alarm !!!!!!");

            if(turnAllLights && turnOnSyrene && notifyPolice){
                comment.setComentCom1("All Lights, Syrene and Police turn ON automatically");
            }
            else if(turnAllLights && turnOnSyrene){
                comment.setComentCom1("All Lights and Syrene turn ON automatically");
            }
            else if(turnAllLights && notifyPolice){
                comment.setComentCom1("All Lights and Police turn ON automatically");
            }
            else if(turnOnSyrene && notifyPolice){
                comment.setComentCom1("Syrene and Police turn ON automatically");
            }
            else if(notifyPolice){
               comment.setComentCom1("Police Notified automatically");
            }
            else if(turnOnSyrene){
                 comment.setComentCom1("Syrene turn ON automatically");
            }
            else if(turnAllLights){
                comment.setComentCom1("All Lights turn ON automatically");
            }
            else{
                comment.setComentCom1("Quiet mode ON");
            }

            notifiedAlarm = true;
        }
    }

    // Design
    public String getStatus(){
        return active ? "Active":"Deactive";
    }

    public String getStatusButton(){
        if(!active){
            return "Active Now";
        }
        else{
            return "Deactive Now";
        }
    }



        // Other settings:
    public void setNotifyPolice(){
        if(notifyPolice){
            notifyPolice = false;
            //comment.setComentCom1("Alarm Police Set Down");
        }
        else{
            notifyPolice = true;
            //comment.setComentCom1("Alarm Police Notified Set ON");
        }
    }

    public void setTurnAllLights(){
        if(turnAllLights){
            turnAllLights = false;
            //comment.setComentCom1("Turn lights Set Down");
        }
        else{
            turnAllLights = true;
            //comment.setComentCom1("Turn lights Notified Set ON");
        }
    }

    public void setTurnOnSyrene(){
        if(turnOnSyrene){
            turnOnSyrene = false;
            //comment.setComentCom1("Syrene Set Down");
        }
        else{
            turnOnSyrene = true;
            //comment.setComentCom1("Syrene Set ON");
        }
    }

    public void turnOffLights(){
        System.out.println("light: " + lightVector.size());
        if(lightVector.size() > 0){
            for(Light l : lightVector){
                l.turnOFF();
            }
            comment.setComentCom1("All Lights turn OFF");
        }

    }

    public void setLightsVector(Vector<Light> lights){
        this.lightVector = lights;
    }



}


