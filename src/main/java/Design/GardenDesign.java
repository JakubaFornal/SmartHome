package Design;

import Objects.GardenSection;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GardenDesign {
    private JPanel mainPanel = new JPanel();
    private comment com;
    private Vector<GardenSection> gardenSectionsVector;


    public GardenDesign(JPanel mainPanel, comment com, Vector<GardenSection> gardenSections) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.gardenSectionsVector = gardenSections;
    }


    public void addContent() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //**// HEADER //**//
        mainPanel.add(addHeader());

        //**// BUTTON SECTION //**//
        mainPanel.add(addButtons());
        mainPanel.add(Box.createVerticalGlue());


        //mainPanel.add(Box.createRigidArea(new Dimension(10, 200)));


    }



    public JPanel addButtons(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 300));

        for (int i = 0; i < gardenSectionsVector.size(); i = i + 2) {
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


            // Button LEFT
            JButton button1 = new JButton(gardenSectionsVector.get(i).getName());
            button1.setFont(new Font("Arial", Font.BOLD, 17));
            button1.setPreferredSize(new Dimension(150, 60));
            button1.setBackground(Color.RED);
            gardenSectionsVector.get(i).setButton(button1);

            int index = i;
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gardenSectionsVector.get(index).changeStatus();
                }
            });

            /*
            button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        windowsVector.get(index).openScheduleManual(hour);
                        button1.setBackground(Color.ORANGE);
                        com.setComentCom1(windowsVector.get(index).getNazwa() + " ventialtion for " + windowsVector.get(index).getVentilationDuration() + " hours");
                    }
                }
            });

             */
            horizontalPanel.add(button1);

            // Button RIGHT
            int index2 = i + 1;
            if (i + 1 < gardenSectionsVector.size()) {
                JButton button2 = new JButton(gardenSectionsVector.get(i + 1).getName());
                button2.setFont(new Font("Arial", Font.BOLD, 17));
                button2.setPreferredSize(new Dimension(150, 60));
                button2.setBackground(Color.RED);
                gardenSectionsVector.get(i + 1).setButton(button2);
                horizontalPanel.add(button2);

                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gardenSectionsVector.get(index2 ).changeStatus();
                    }
                });

                /*
                button2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            windowsVector.get(index2).openScheduleManual(hour);
                            button2.setBackground(Color.ORANGE);
                            com.setComentCom1( windowsVector.get(index2).getNazwa() + " ventialtion for " +  windowsVector.get(index2).getVentilationDuration() + " hours");
                        }
                    }
                });

                 */
            }

            //System.out.println(windowTable.length);
            buttonPanel.add(horizontalPanel);
        }
        return buttonPanel;
    }

    private JPanel addHeader(){
        JPanel headerP = new JPanel();
        headerP.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headerL = new JLabel("Garden");
        headerL.setFont(new Font("Arial", Font.BOLD, 27));
        headerP.setBorder(new EmptyBorder(30, 10, 10, 10));
        headerP.add(headerL);

        return headerP;
    }

    public void refreshPanel() {
        mainPanel.removeAll(); // Clear existing buttons
        addContent(); // Rebuild the UI with updated windows
        mainPanel.revalidate(); // Recalculate layout
        mainPanel.repaint(); // Repaint to reflect changes
    }
}
