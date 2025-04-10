package Objects;
import javax.swing.*;


public class Gate {
    boolean gateOpen = false;
    int closeGateTime = 22;
    int hour;
    public JLabel label;
    private comment com;
    private JButton openButton;

    public Gate(JLabel stausGateLabel, comment com, JButton openButton) {
        this.label = stausGateLabel;
        this.com = com;
        this.openButton = openButton;
    }

    public boolean openGate() {
        return gateOpen;
    }

    public void changeState() {
        if(gateOpen) {
            com.setComentCom1("Gate Closed.");
        }
        else {
            com.setComentCom1("Gate Open.");
        }
        this.gateOpen = !gateOpen;
        this.label.setText("Gate Open: " + gateOpen);
    }



    public String getGateButton() {
        return gateOpen ? "Close Gate" : "Open Gate";
    }

    public String getStateGateLabel() {
        return gateOpen ? "Open" : "Close";
    }

    //Closing gate on click
    public void setGateClose() {
        this.gateOpen = false;
    }

    // Automatic Close Time
    public void setGateClosed(int hour) {
        this.closeGateTime = hour;
    }

    public int getCloseTime() {
        return closeGateTime;
    }

    public void changeLabel() {
        label.setText(gateOpen ? "Open" : "Closed");
    }

    public String getLabel() {
        return label.getText();
    }

    public void automaticCloseGate(int hour) {
        if(hour == closeGateTime && gateOpen) {
            this.gateOpen = false;
            label.setText("Closed");
            openButton.setText("Open Gate");
            com.setComentCom1( hour + ":00 Gate closed automaticaly.");
        }
    }
}
