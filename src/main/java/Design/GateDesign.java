package Design;

import Objects.Gate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

import static Design.colors.colorVector;


public class GateDesign {
    private JPanel panel;
    private Gate Gate1;
    private JLabel statusGate;
    private JButton openButton;

    public GateDesign(JPanel panel, JLabel statusGate, Gate Gate1, JButton openButton) {
        this.panel = panel;
        this.statusGate = statusGate;
        this.Gate1 = Gate1;
        this.openButton = openButton;
    }

    public void setStaus() {
        statusGate.setText(Gate1.getStateGateLabel());
        System.out.println(statusGate.getText());
    }



    public void addComponents(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Color newColor = Color.decode("#EBEBEB");
        panel.setBackground(newColor);


        // Heading Gate //
        JPanel gateHeading = new JPanel();
        gateHeading.setLayout(new FlowLayout(FlowLayout.CENTER));
        //gateHeading.setBackground(newColor);
        //gateHeading.setPreferredSize(new Dimension(400, 50));
        gateHeading.setBorder(new EmptyBorder(40, 10, 0, 10));

        JLabel gateHeadingLabel = new JLabel("Gate");
        gateHeadingLabel.setFont(new Font("Arial", Font.BOLD, 27));
        gateHeading.add(gateHeadingLabel);
        panel.add(gateHeading);


        // 1 Gate Panel  //
        JPanel gate_1P = new JPanel();
        //gate_1P.setBackground(newColor);
        gate_1P.setBorder(new EmptyBorder(20, 10, 30, 10));
        gate_1P.setLayout(new BoxLayout(gate_1P, BoxLayout.Y_AXIS));
        gate_1P.setPreferredSize(new Dimension(400, 200));
        panel.add(gate_1P);

        gate_1P.setAlignmentX(Component.CENTER_ALIGNMENT);



        // Status Bramy
        JPanel gate_1aP = new JPanel();
        gate_1aP.setLayout(new FlowLayout(FlowLayout.CENTER));
       // gate_1aP.setBackground(newColor);
        gate_1aP.setBorder(new EmptyBorder(30, 10, 5, 10));

        JLabel statusText = new JLabel("Status Bramy: ");
        gate_1aP.add(statusText);
        statusText.setFont(new Font("Arial", Font.PLAIN, 19));

        statusGate.setText(Gate1.getStateGateLabel());
        statusGate.setFont(new Font("Arial", Font.BOLD, 19));
        gate_1aP.add(statusGate);

        //GATA definition

        Gate1.setGateClosed(23);
        Gate1.openGate();
        //statusGate.setText(Gate1.gatState());
        gate_1P.add(gate_1aP);


        // OPEN GATE BUTTON / gate_1BP

        //button
        JPanel button1P = new JPanel();
        button1P.setLayout(new FlowLayout(FlowLayout.CENTER));
        openButton.setText(Gate1.getGateButton());
        openButton.setPreferredSize(new Dimension(130, 45));
        openButton.setFont(new Font("Arial", Font.BOLD, 18));
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setBackground(colorVector.get(3));
        button1P.add(openButton);

        gate_1P.add(button1P);


        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gate1.changeState();
                openButton.setText(Gate1.getGateButton());
                statusGate.setText(Gate1.getStateGateLabel());
            }
        });


        /// 2 Gate Panel ///
        JPanel gate_2P = new JPanel();
        gate_2P.setPreferredSize(new Dimension(400, 300));
        gate_2P.setBorder(new EmptyBorder(15, 10, 150, 10));
        gate_2P.setLayout(new BoxLayout(gate_2P, BoxLayout.Y_AXIS));
        //gate_2P.setPreferredSize(new Dimension(400, 300));
        //gate_2P.setBackground(newColor);

        JPanel gate_2_1P = new JPanel();

        gate_2_1P.setBorder(new EmptyBorder(20, 10, 10, 10));
                //gate_2_1P.setBackground(newColor);
        //gate_2_1P.setPreferredSize(new Dimension(400, 100));

        JLabel gate_2L = new JLabel("Automatyczny czas zamykania: ");
        gate_2L.setFont(new Font("Arial", Font.PLAIN, 19));
        gate_2_1P.add(gate_2L);

        JTextArea automaticCloseHour = new JTextArea(1, 2);
        automaticCloseHour.setMargin(new Insets(2, 10, 2, 2));
        automaticCloseHour.setFont(new Font("Arial", Font.BOLD, 17));
        gate_2_1P.add(automaticCloseHour);

        gate_2P.add(gate_2_1P);

        JPanel button2P = new JPanel();
        button2P.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveGate = new JButton("Save");
        saveGate.setPreferredSize(new Dimension(120, 45));
        saveGate.setFont(new Font("Arial", Font.BOLD, 18));
        //saveGate.setMargin(new Insets(0, 0, 0, 0));
        saveGate.setBackground(colorVector.get(3));
        saveGate.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveGate.setAlignmentY(Component.CENTER_ALIGNMENT);
        button2P.add(saveGate);
        gate_2P.add(button2P);

        panel.add(gate_2P);

        // GODZ. AUTO OFF Label

        saveGate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!automaticCloseHour.getText().isEmpty()){
                    String hourClose = automaticCloseHour.getText();
                    System.out.println(hourClose);
                    int hour = Integer.parseInt(hourClose);
                    Gate1.setGateClosed(hour);
                }
            }
        });
    };



}
