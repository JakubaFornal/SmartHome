package Design.Configuration;

import Design.GardenDesign;
import Objects.GardenSection;
import Objects.Light;
import Objects.WaterPump;
import Objects.Window;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static Design.Configuration.ConfigurationDesign.addHeader;
import static Design.Configuration.ConfigurationDesign.addInput;
import static Design.colors.colorVector;

public class GardenConfigDesign {
    private JPanel mainPanel;
    private comment com;
    private Vector<GardenSection> gardenSectionVector;
    private CardLayout cardLayout;
    private JTextField[] textFieldTable = new JTextField[10];
    private GardenDesign gardeDesign;
    private JComboBox comboBox;
    private JPanel comboPanel;


    public GardenConfigDesign(JPanel mainPanel, comment com, Vector<GardenSection> gardenSectionVector, GardenDesign gardenDesign) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.gardenSectionVector = gardenSectionVector;
        this.gardeDesign = gardenDesign;
    }

    public void addContent() {
         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
         cardLayout = new CardLayout();
         mainPanel.setLayout(cardLayout);

         JPanel pumpPanel = cardPumpDesign();
         JPanel sectionPanel = cardSectionDesign();

         mainPanel.add(pumpPanel, "card1");
         mainPanel.add(sectionPanel, "card2");

    }

    public JPanel cardPumpDesign(){
        JPanel pumpPanel = new JPanel();
        pumpPanel.setLayout(new BorderLayout());

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel comboLabel = new JLabel("Section: ");
        comboLabel.setFont(new Font("Arial", Font.BOLD, 17));
        comboPanel.add(comboLabel);

        comboBox = new JComboBox<>();
        getCombo();
        comboPanel.add(comboBox);

        contentPanel.add(addHeader("Add Pump"));
        contentPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        contentPanel.add(addInput("Name", 4, 0, textFieldTable));
        contentPanel.add(comboPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        contentPanel.add(addSaveButton());
        contentPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        contentPanel.add(Box.createVerticalGlue());

        // Main Panel
        pumpPanel.add(contentPanel, BorderLayout.CENTER);
        pumpPanel.add(navPanel(), BorderLayout.SOUTH);

        return pumpPanel;
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
                    WaterPump waterPump = new WaterPump(textFieldTable[0].getText());
                    String name = comboBox.getSelectedItem().toString();
                    GardenSection gardenSection = getGardenSection(name);
                    gardenSection.addWaterPump(waterPump);
                    com.setComentCom1("New Water Pump: " + waterPump.getName() + " added to " + gardenSection.getName());
                }
            }
        });

        return buttonPanel;
    }

    public void getCombo(){
        comboBox.removeAllItems();
        for (GardenSection s :  gardenSectionVector) {
            if(s.getName() != "AllSection"){
                comboBox.addItem(s.getName());
            }

        }
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public JPanel cardSectionDesign(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(addHeader("Add Section"));
        contentPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        contentPanel.add(addInput("Name", 4, 1, textFieldTable));
        contentPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        contentPanel.add(addSaveButtonSection());
        contentPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        contentPanel.add(Box.createVerticalGlue());

        // Main Panel
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(navPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel addSaveButtonSection(){                                             // SAVE BUTTON //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(110, 35));
        saveButton.setFont(new Font("Arial", Font.BOLD, 19));
        saveButton.setBackground(colorVector.get(3));
        buttonPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textFieldTable[1].getText().isEmpty()){
                    GardenSection gardenSection = new GardenSection(textFieldTable[1].getText(), null, com);
                    gardenSectionVector.add(gardenSectionVector.size() - 1 ,gardenSection);
                    com.setComentCom1("New Section: " + gardenSection.getName());
                    getCombo();
                }

                gardeDesign.refreshPanel();
            }
        });

        return buttonPanel;
    }


    public GardenSection getGardenSection(String name) {
        for(GardenSection s : gardenSectionVector) {
            if(s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    // GENERAL DESIGN
    public JPanel navPanel(){
        //**// NAVIGATION //**//
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton PumpButton= new JButton("Pump");
        JButton SectionButton = new JButton("Section");

        PumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "card1");
            }
        });

        SectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "card2");
            }
        });

        navPanel.add(PumpButton);
        navPanel.add(SectionButton);

        return navPanel;
    }

}
