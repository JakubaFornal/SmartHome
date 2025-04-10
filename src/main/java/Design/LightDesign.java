package Design;

import Objects.Light;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static Design.colors.colorVector;

public class LightDesign {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private Vector<Light> lightVector;
    private comment com;

    public LightDesign(JPanel mainPanel, Vector<Light> lightVector, comment com) {
        this.mainPanel = mainPanel;
        this.lightVector = lightVector;
        this.com = com;
    }

    public void addComponents() {
        mainPanel.setLayout(new BorderLayout());
        //**// HEADER //**//
        JPanel headerP = new JPanel();
        headerP.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headerL = new JLabel("Lights");
        headerL.setFont(new Font("Arial", Font.BOLD, 27));
        headerP.setBorder(new EmptyBorder(30, 10, 10, 10));
        headerP.add(headerL);
        mainPanel.add(headerP, BorderLayout.NORTH);



        // CARD LAYOUT ///
        CardLayout cardLayout = new CardLayout();
        JPanel viewPanel = new JPanel(cardLayout);
        //viewPanel.setBackground(colorVector.get(4));            // color of content panel

        // WINDOW PANEL ///
        JPanel windowsPanel = new JPanel();
        //windowsPanel.setBackground(colorVector.get(4));          // color of window Panel

        contentPanel = new JPanel();
        //contentPanel.setBackground(colorVector.get(4));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        addButtons();

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        //scrollPane.setBackground(colorVector.get(1));
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(400, 410));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.addMouseWheelListener(new MouseAdapter() {                           // scroll function
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() + e.getWheelRotation() * 10);
            }
        });

        windowsPanel.add(scrollPane);
        viewPanel.add(windowsPanel, "1");

        mainPanel.add(viewPanel, BorderLayout.CENTER);

    }

    public void addButtons(){

        for (int i = 0; i < lightVector.size(); i = i + 2) {
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


            // Button LEFT
            JButton button1 = new JButton(lightVector.get(i).getName());
            button1.setFont(new Font("Arial", Font.BOLD, 17));
            button1.setPreferredSize(new Dimension(150, 60));
            button1.setBackground(Color.RED);
            lightVector.get(i).setJButton(button1);

            int index = i;
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lightVector.get(index).changeStatus();
                    //System.out.println(lightVector.get(index).getButtonText());
                }
            });

            button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        //lightVector.get(index).openScheduleManual(hour);
                        //button1.setBackground(Color.ORANGE);
                        //com.setComentCom1(windowsVector.get(index).getNazwa() + " ventialtion for " + windowsVector.get(index).getVentilationDuration() + " hours");
                    }
                }
            });
            horizontalPanel.add(button1);

            // Button RIGHT
            int index2 = i + 1;
            if (i + 1 < lightVector.size()) {
                JButton button2 = new JButton(lightVector.get(i + 1).getName());
                button2.setFont(new Font("Arial", Font.BOLD, 17));
                button2.setPreferredSize(new Dimension(150, 60));
                button2.setBackground(Color.RED);
                horizontalPanel.add(button2);
                lightVector.get(i + 1).setJButton(button2);

                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        lightVector.get(index2 ).changeStatus();
                        //System.out.println(lightVector.get(index2).getBut());
                    }
                });

                button2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                           // windowsVector.get(index2).openScheduleManual(hour);
                           // button2.setBackground(Color.ORANGE);
                            //com.setComentCom1( windowsVector.get(index2).getNazwa() + " ventialtion for " +  windowsVector.get(index2).getVentilationDuration() + " hours");
                        }
                    }
                });
            }

            //System.out.println(windowTable.length);
            contentPanel.add(horizontalPanel);
        }

    }

    public void refreshPanel() {
        mainPanel.removeAll(); // Clear existing buttons
        addComponents(); // Rebuild the UI with updated windows
        mainPanel.revalidate(); // Recalculate layout
        mainPanel.repaint(); // Repaint to reflect changes
    }

}
