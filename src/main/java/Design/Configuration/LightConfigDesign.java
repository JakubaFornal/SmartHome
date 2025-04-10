package Design.Configuration;

import Design.LightDesign;
import Objects.Light;
import Objects.comment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static Design.Configuration.ConfigurationDesign.addHeader;
import static Design.Configuration.ConfigurationDesign.addInput;
import static Design.colors.colorVector;

public class LightConfigDesign {
    private JPanel mainPanel;
    private Vector<Light> lightVector;
    private LightDesign lightDesign;
    private comment com;
    private JTextField[] textFieldTable = new JTextField[3];

    public LightConfigDesign(JPanel mainPanel, comment com, Vector<Light>   lightVector, LightDesign lightDesign) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.lightVector = lightVector;
        this.lightDesign = lightDesign;
    }

    public void addContent() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(addHeader("Add Light"));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(addInput("Name", 5, 0, textFieldTable));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        mainPanel.add(addSaveButton());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        //mainPanel.add(customPanelFuntion);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        //mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        mainPanel.add(Box.createVerticalGlue());

    }


    private JPanel addSaveButton(){                                             // SAVE BUTTON //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(110, 35));
        saveButton.setFont(new Font("Arial", Font.BOLD, 19));
        saveButton.setBackground(colorVector.get(3));
        buttonPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textFieldTable[0].getText().isEmpty()){
                    Light light = new Light(textFieldTable[0].getText(), null, com);
                    lightVector.add(light);
                    com.setComentCom1("New Light added: " + lightVector.get(lightVector.size() - 1).getName());
                }

                lightDesign.refreshPanel();
            }
        });

        return buttonPanel;
    }

}
