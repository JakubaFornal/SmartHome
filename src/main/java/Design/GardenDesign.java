package Design;

import Objects.GardenSection;
import Objects.WaterPump;
import Objects.Window;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import static Design.colors.colorVector;

public class GardenDesign {
    private JPanel mainPanel = new JPanel();
    private comment com;
    private Vector<GardenSection> gardenSectionsVector;
    private int hour;
    private JButton button;
    boolean allClosed = true;
    private JTextField openTimeArea;
    private JTextField durationTime;
    private JTextField closeTime;
    private JComboBox comboBox;
    private GardenSection editingSection;

    public GardenDesign(JPanel mainPanel, comment com, Vector<GardenSection> gardenSections) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.gardenSectionsVector = gardenSections;
    }


    public void addContent() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        allSection();

        //**// HEADER //**//
        mainPanel.add(addHeader(), BorderLayout.NORTH);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));


        //**// Card Panel //**//
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 50)));


        //**// Card1 //**//
        JPanel card1 = new JPanel();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
        card1.add(addButtons());
        card1.add(addBigButton());
        card1.add(Box.createVerticalGlue());
        cardPanel.add(card1, "card1");

        //**// Card2 //**//
        JPanel card2 = new JPanel();
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        card2.add(addCustomSettings());
        cardPanel.add(card2, "card2");


        //**// Menu //**//
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton menu1Button = new JButton("Section");
        JButton menu2Button = new JButton("Settings");

        menu.add(menu1Button);
        menu.add(menu2Button);

        menu1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "card1");
            }
        });

        menu2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "card2");
            }
        });
        mainPanel.add(menu, BorderLayout.SOUTH);
    }

    //**// DESIGN CARD2 //**//
    public JPanel addCustomSettings() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

           // All Section Vector

        if(gardenSectionsVector.size() > 0) {
            editingSection = gardenSectionsVector.get(0);
        }

        // Panel 0 //
        JPanel panel0 = new JPanel();
        panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));

        JPanel panel0_H = new JPanel();
        panel0_H.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel panel0_L = new JLabel("Select Section:");
        panel0_L.setFont(new Font("Arial", Font.BOLD, 19));
        panel0_H.add(panel0_L);
        panel0.add(panel0_H);

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        comboBox = new JComboBox<>();
        for (GardenSection gardenSection : gardenSectionsVector) {
            comboBox.addItem(gardenSection.getName());
        }
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboPanel.add(comboBox);
        panel0.add(comboPanel);


        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = (String) comboBox.getSelectedItem();
                if(name.equals("All Sectins")) {
                    editingSection = gardenSectionsVector.get(0);
                }
                else{
                    editingSection = getGardenSection(name);
                    System.out.println(editingSection.getName());
                }

            }
        });


        // PANEL 1 //
        JPanel P1 = new JPanel();
        P1.setLayout(new BoxLayout(P1, BoxLayout.Y_AXIS));

        JPanel  P1_1 = new JPanel();   // Panel 1_1
        P1_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel SLabel2b1 = new JLabel("Manual schedule:");
        SLabel2b1.setFont(new Font("Arial", Font.BOLD, 18));
        P1_1.add(SLabel2b1);


        JPanel P1_2 = new JPanel();    // Panel 1_2
        P1_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel P1_1L = new JLabel("Open Time: ");
        P1_1L.setFont(new Font("Arial", Font.PLAIN, 17));
        openTimeArea = new JTextField(2);
        openTimeArea.setFont(new Font("Arial", Font.PLAIN, 16));
        openTimeArea.setHorizontalAlignment(SwingConstants.CENTER);
        P1_2.add(P1_1L);
        P1_2.add(openTimeArea);




        JPanel P1_3 = new JPanel();        // Panel 1_3
        P1_3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel P1_3L = new JLabel("Duration Time: ");
        P1_3L.setFont(new Font("Arial", Font.PLAIN, 17));
        durationTime = new JTextField(2);
        durationTime.setFont(new Font("Arial", Font.PLAIN, 16));
        durationTime.setHorizontalAlignment(SwingConstants.CENTER);
        P1_3.add(P1_3L);
        P1_3.add(durationTime);

        P1.add(Box.createVerticalGlue());
        P1.add(Box.createRigidArea(new Dimension(0, 20)));
        P1.add(P1_1);
        P1.add(Box.createRigidArea(new Dimension(10, 0)));
        P1.add(P1_2);
        P1.add(Box.createRigidArea(new Dimension(10, 0)));
        P1.add(P1_3);
        P1.add(Box.createRigidArea(new Dimension(10, 20)));
        P1.add(Box.createVerticalGlue());



        /*

        // PANEL 2 //
        JPanel P2 = new JPanel();
        P2.setLayout(new BoxLayout(P2, BoxLayout.Y_AXIS));

        JPanel P2_1 = new JPanel();   // panel 2_1
        P2_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel P2_1L = new JLabel("Other settings:");
        P2_1L.setFont(new Font("Arial", Font.BOLD, 18));
        P2_1.add(P2_1L);
        P2.add(P2_1);


        JPanel  P2_2 = new JPanel();    // panel 2_2
        P2_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(Box.createVerticalGlue());JLabel P2_2L = new JLabel("Auto Close Time: ");
        P2_2L.setFont(new Font("Arial", Font.PLAIN, 17));
        durationTime = new JTextField(2);
        durationTime.setFont(new Font("Arial", Font.PLAIN, 16));
        durationTime.setHorizontalAlignment(SwingConstants.CENTER);
        P2_2.add(P2_2L);
        P2_2.add(durationTime);


        P2.add(Box.createVerticalGlue());
        P2.add(P2_1);
        P2.add(Box.createRigidArea(new Dimension(10, 5)));
        P2.add(P2_2);
        P2.add(Box.createRigidArea(new Dimension(10, 5)));
        P2.add(Box.createVerticalGlue());

        panel.add(P2);
        */

        JPanel saveButtonPanel = new JPanel();                                      // save Button
        saveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(120, 40));
        saveButton.setFont(new Font("Arial", Font.BOLD, 19));
        saveButton.setBackground(colorVector.get(3));
        saveButtonPanel.add(saveButton);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(panel0);
        panel.add(P1);
        panel.add(saveButtonPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(Box.createVerticalGlue());


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Custom settings
                if (!durationTime.getText().isEmpty()) {
                        editingSection.setDuration(Integer.parseInt(durationTime.getText()));
                        System.out.println(editingSection.getName() + " set duration: " + editingSection.getDuration());
                }

                if (!openTimeArea.getText().isEmpty()) {
                        editingSection.setOpenTimeHour(Integer.parseInt(openTimeArea.getText()));
                        System.out.println(editingSection.getName() + " set close: " + editingSection.getOpenTimeHour());
                }
            }
        });

        return panel;
    }



    //**// DESIGN CARD1 ///**//
    public JPanel addBigButton(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        button = new JButton("Open all Sections");
        button.setBackground(colorVector.get(1));
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setPreferredSize(new Dimension(320, 60));

        Timer[] actionTimer1 = new Timer[1]; // Mutable reference for single-click delay

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {                                           //double click Big Button
                    if (actionTimer1[0] != null && actionTimer1[0].isRunning()) {
                        actionTimer1[0].stop();
                    }
                    button.setBackground(colorVector.get(3));
                    // Double-click action runs immediately
                    for(GardenSection gardenSection : gardenSectionsVector) {
                        gardenSection.openScheduleManual(hour, false);
                    }
                    com.setComentCom1("All Sections Open for set Time.");
                    button.setText("Close All Sections");
                }
                else if (e.getClickCount() == 1) {                                      // single click Big Button
                    for(GardenSection gardenSection : gardenSectionsVector) {
                        gardenSection.setButtonColorReverse();
                        if(allClosed) {
                            button.setBackground(colorVector.get(1));
                            button.setText("Open All Sections");
                        }
                        else{
                            button.setBackground(colorVector.get(3));
                            button.setText("Close All Sections");
                        }
                    }

                    // Delay the function (not the click)
                    actionTimer1[0] = new Timer(250, evt -> {
                        if(allClosed){
                            for (GardenSection gardenSection : gardenSectionsVector) {
                                gardenSection.openSection();
                                button.setBackground(colorVector.get(3));
                            }
                            //System.out.println("Kuba: close all");
                        }
                        else{
                            for (GardenSection gardenSection : gardenSectionsVector) {
                                gardenSection.closeSection();
                                button.setBackground(colorVector.get(1));
                            }
                        }
                    });

                    if(gardenSectionsVector.getFirst().getFirstPumpStatus()){
                        com.setComentCom1("All Sections Closed");
                    }
                    else{
                        com.setComentCom1("All Sections Opned");
                    }

                    actionTimer1[0].setRepeats(false);
                    actionTimer1[0].start();
                }
            }
        });
        buttonPanel.add(button);

        return buttonPanel;
    }

    public JPanel addButtons(){                                                             //card1

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 300));

        for (int i = 0; i < gardenSectionsVector.size() - 1; i = i + 2) {
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


            // Button LEFT
            JButton button1 = new JButton(gardenSectionsVector.get(i).getName());
            button1.setFont(new Font("Arial", Font.BOLD, 18));
            button1.setPreferredSize(new Dimension(150, 60));
            button1.setBackground(Color.RED);
            gardenSectionsVector.get(i).setButton(button1);

            int index = i;
            Timer[] actionTimer1 = new Timer[1]; // Mutable reference for single-click delay

            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        // Cancel pending single-click if it's waiting
                        if (actionTimer1[0] != null && actionTimer1[0].isRunning()) {
                            actionTimer1[0].stop();
                        }

                        // Double-click action runs immediately
                        gardenSectionsVector.get(index).openScheduleManual(hour, true);
                        button1.setBackground(Color.ORANGE);
                    }
                    else if (e.getClickCount() == 1) {
                        // Delay the function (not the click)
                        actionTimer1[0] = new Timer(250, evt -> {
                            gardenSectionsVector.get(index).changeStatus(true);
                            System.out.println(gardenSectionsVector.get(index).getName());
                        });
                        gardenSectionsVector.get(index).setButtonColorReverse();
                        actionTimer1[0].setRepeats(false);
                        actionTimer1[0].start();
                    }
                }
            });


            horizontalPanel.add(button1);

            // Button RIGHT
            int index2 = i + 1;
            if (i + 1 < gardenSectionsVector.size() && !gardenSectionsVector.get(i + 1).getName().equals("AllSection")) {
                JButton button2 = new JButton(gardenSectionsVector.get(i + 1).getName());
                button2.setFont(new Font("Arial", Font.BOLD, 17));
                button2.setPreferredSize(new Dimension(150, 60));
                button2.setBackground(Color.RED);
                gardenSectionsVector.get(i + 1).setButton(button2);
                horizontalPanel.add(button2);



                Timer[] actionTimer = new Timer[1]; // Mutable reference

                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            // Cancel pending single-click function if it's waiting
                            if (actionTimer[0] != null && actionTimer[0].isRunning()) {
                                actionTimer[0].stop();
                            }

                            // Run double-click function immediately
                            gardenSectionsVector.get(index2).openScheduleManual(hour, true);
                            button2.setBackground(Color.ORANGE);

                        } else if (e.getClickCount() == 1) {
                            // Delay the function logic, not the UI
                            actionTimer[0] = new Timer(250, evt -> {
                                gardenSectionsVector.get(index2).changeStatus(true);
                            });
                            gardenSectionsVector.get(index2).setButtonColorReverse();
                            actionTimer[0].setRepeats(false);
                            actionTimer[0].start();
                        }
                    }
                });

            }

            //System.out.println(windowTable.length);
            buttonPanel.add(horizontalPanel);
        }
        return buttonPanel;
    }



    // Header //
    private JPanel addHeader(){
        JPanel headerP = new JPanel();
        headerP.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headerL = new JLabel("Garden");
        headerL.setFont(new Font("Arial", Font.BOLD, 27));
        headerP.setBorder(new EmptyBorder(30, 10, 10, 10));
        headerP.add(headerL);

        return headerP;
    }


    // OTHER FUNCTIONS //
    public void refreshPanel() {
        mainPanel.removeAll(); // Clear existing buttons
        addContent(); // Rebuild the UI with updated windows
        mainPanel.revalidate(); // Recalculate layout
        mainPanel.repaint(); // Repaint to reflect changes
    }

    public void updateHour(int hour) {
        this.hour = hour;
    }

    public void checkSectionCloseTime(int hour) {
        for(GardenSection gardenSection : gardenSectionsVector) {
            if(hour == gardenSection.getCloseHour())
                gardenSection.closeSection();
        }
    }

    public void checkAllSectionsStatus(){
        boolean allSectionsClosed = true;
        for(GardenSection gardenSection : gardenSectionsVector) {
            if(gardenSection.hasPump() && gardenSection.getFirstPumpStatus())
                allSectionsClosed = false;
        }
        if(!allSectionsClosed){
            button.setText("Close All Sections");
            button.setBackground(colorVector.get(3));
        }
        else{
            button.setText("Open All Sections");
            button.setBackground(colorVector.get(1));

        }
        allClosed = allSectionsClosed;

    }


    public GardenSection getGardenSection(String name) {
        for(GardenSection s : gardenSectionsVector) {
            if(s.getName().equals(name))
                return s;
        }
        return null;
    }

    public void allSection(){
        JButton button = new JButton();
        GardenSection allSection = new GardenSection("AllSection", button, com);
        for(GardenSection s : gardenSectionsVector) {
            allSection.addVector(s.getVector());
        }
        gardenSectionsVector.add(gardenSectionsVector.size(), allSection);

        allSection.addGardenSectionVector(gardenSectionsVector);
    }

    public void checkOpenTime(int hour) {                                               // check open time of Section in TetsHome.java
        for(GardenSection gardenSection : gardenSectionsVector) {
            if(gardenSection.getOpenTimeHour() == hour){
                gardenSection.openScheduleManual(hour, false);
            }
        }
    }


}
