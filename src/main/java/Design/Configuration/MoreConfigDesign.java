package Design.Configuration;

import Design.LightDesign;
import Design.WindowDesign;
import Objects.Light;
import Objects.Window;
import Objects.comment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static Design.Configuration.ConfigurationDesign.addHeader;
import static Design.Configuration.ConfigurationDesign.addInput;
import static Design.colors.colorVector;

public class MoreConfigDesign {
    private JPanel mainPanel;
    private comment com;
    private Vector<Light> lightVector;
    private Vector<Window> windowVector;
    private  JCheckBox[] checkBoxes;
    private WindowDesign windowDesign;
    private LightDesign lightDesign;

    public MoreConfigDesign(JPanel mainPanel, comment com, Vector<Light> lightVector, Vector<Window> windowVector, LightDesign lightDesign, WindowDesign windowDesign) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.lightVector = lightVector;
        this.windowVector = windowVector;
        this.lightDesign = lightDesign;
        this.windowDesign = windowDesign;
    }

    public void addContent(){
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ConfigurationDesign.addHeader("Import Default Objects");

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(ConfigurationDesign.addHeader("Import Default Objects"));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(addCheckBox());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        mainPanel.add(addSaveButton());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 60)));
        mainPanel.add(Box.createVerticalGlue());
    }

    private JPanel addCheckBox(){
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        this.checkBoxes = new JCheckBox[2];
        JPanel box1 = new JPanel();
        box1.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxes[0] = new JCheckBox("Import Windows");
        box1.add(checkBoxes[0]);

        JPanel box2 = new JPanel();
        box2.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxes[1] = new JCheckBox("Import Lights");
        box2.add(checkBoxes[1]);

        checkBoxes[0].setFont(new Font("Arial", Font.BOLD, 17));
        checkBoxes[1].setFont(new Font("Arial", Font.BOLD, 17));

        checkBoxPanel.add(box1);
        checkBoxPanel.add(box2);
        return checkBoxPanel;
    }

    private JPanel addSaveButton() {                                             // SAVE BUTTON //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Import");
        saveButton.setPreferredSize(new Dimension(110, 35));
        saveButton.setFont(new Font("Arial", Font.BOLD, 19));
        saveButton.setBackground(colorVector.get(3));
        buttonPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBoxes[0].isSelected()) {
                    importWindow();
                }

                if (checkBoxes[1].isSelected()) {
                    importLight();
                }
            }
        });
        return buttonPanel;
    }

    public void importWindow(){
        windowVector.clear();
        String[] rooms = {
                "Living Room",
                "Bedroom",
                "Szef room",
                "Kitchen",
                "Hall",
                "Bathroom",
                "Dining Room",
                "Guest Room",
                "Office",
                "Laundry Room",
                "Garage",
                "Basement",
        };

        for(int i = 0; i < rooms.length; i++){
            Window window = new Window(rooms[i], null,com);
            windowVector.add(window);
        }
        windowDesign.refreshPanel();

        com.setComentCom1("Windows Imported");
    }

    public void importLight(){
       lightVector.clear();
        String[] rooms = {
                "Living Room",
                "Bedroom",
                "Kitchen",
                "Bathroom",
                "Dining Room",
                "Guest Room",
                "Office",
                "Laundry Room",
                "Garage",
                "Basement",
                "Szef room",
                "Szefooozaa"
        };

        for(int i = 0; i < rooms.length; i++){
            Light light = new Light(rooms[i], null,com);
            lightVector.add(light);
        }
        lightDesign.refreshPanel();

        com.setComentCom1("Lights Imported");
    }

}


