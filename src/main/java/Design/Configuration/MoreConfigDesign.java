package Design.Configuration;

import Design.GardenDesign;
import Design.LightDesign;
import Design.WindowDesign;
import Objects.*;
import Objects.Window;

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
    private Vector<GardenSection> SectionsVector;
    private Vector<WaterPump> waterPumpsVector;
    private GardenDesign gardenDesign;

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
        this.checkBoxes = new JCheckBox[5];
        JPanel box1 = new JPanel();
        box1.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxes[0] = new JCheckBox("Import Windows");
        box1.add(checkBoxes[0]);

        JPanel box2 = new JPanel();
        box2.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxes[1] = new JCheckBox("Import Lights");
        box2.add(checkBoxes[1]);

        JPanel box3 = new JPanel();
        box3.setLayout(new FlowLayout(FlowLayout.CENTER));
        checkBoxes[2] = new JCheckBox("Import Sections and Pumps");
        box3.add(checkBoxes[2]);

        checkBoxes[0].setFont(new Font("Arial", Font.BOLD, 17));
        checkBoxes[1].setFont(new Font("Arial", Font.BOLD, 17));
        checkBoxes[2].setFont(new Font("Arial", Font.BOLD, 17));

        checkBoxPanel.add(box1);
        checkBoxPanel.add(box2);
        checkBoxPanel.add(box3);
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

                if (checkBoxes[2].isSelected()) {
                    importGarden();
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

    public void importGarden(){
        if(SectionsVector != null){
            SectionsVector.clear();
        }

        String[] section = {
                "Front Yard 1",
                "Front Yard 2",
                "East Garden",
                "Bathroom",
                "Back Yard",
                "Back Yard",
        };

        for(int i = 0; i < section.length; i++){
            GardenSection section_temp = new GardenSection(section[i], null, com);
            SectionsVector.add(section_temp);
        }
        gardenDesign.refreshPanel();


        com.setComentCom1("Garden Imported");
    }



    public void getGardenVectors(Vector<GardenSection> gardenSections, GardenDesign gardenDesign){
        this.SectionsVector = gardenSections;
        this.gardenDesign = gardenDesign;
    }

}


