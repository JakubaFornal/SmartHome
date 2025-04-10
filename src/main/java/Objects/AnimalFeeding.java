package Objects;

import javax.swing.*;

public class AnimalFeeding {
    boolean feededToday = false;
    int hourFeeding = 12;
    int portion = 100;
    private comment com;
    String name;
    JButton feedNowButton;
    JLabel statusLabel;

    public AnimalFeeding(String name, comment com, JButton feedNowButton, JLabel statusLabel) {
        this.name = name;
        this.com = com;
        this.feedNowButton = feedNowButton;
        this.statusLabel = statusLabel;
    }

    public void AnimalFeed() {
        if(!feededToday) {
            this.feededToday = true;
            com.setComentCom1(this.name + " has been just fed , portion: "  + portion + " g.");

        }

    }

    public void scheduleFeeding(int hour) {
        if(hour == hourFeeding && !feededToday) {
            this.feededToday = true;
            com.setComentCom1(this.name + " has been fed automatically, portion: "  + portion + " g.");
            statusLabel.setText("Fed");
            feedNowButton.setText("Already fed");
        }

    }

    public void setFeedingHour(int hour){
        this.hourFeeding = hour;
    }

    public void setPortion(int portion){
        this.portion = portion;
    }

    public void resetStaus(int hour){
        if(hour == 0){
            this.feededToday = false;
            statusLabel.setText("Not Feed");
            feedNowButton.setText("Feed Now");
            System.out.println(statusLabel.getText());
        }
    }

    // Design
    public String getStatus(){
        return feededToday ? "Fed" : "Not Feed";
    }



}
