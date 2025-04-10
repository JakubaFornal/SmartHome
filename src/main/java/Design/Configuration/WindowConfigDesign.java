package Design.Configuration;

import Design.WindowDesign;
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

public class WindowConfigDesign {
    private JPanel mainPanel;
    private JTextField[] textFieldTable = new JTextField[5];
    private Vector<Window> windowsVector;
    private comment com;

    private Window[] windowTable;

    private JPanel MenuPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel[] cardTableP;
    private JButton[] buttonsTable;

    int currIndex = -1;

    private WindowDesign windowDesign;
    private Window editingWindow;
    private JTextField closeTime;
    private JTextField durationTime;
    private JTextField openTimeArea;

    public WindowConfigDesign(JPanel mainPanel, Vector<Window> windowsVector, comment com, WindowDesign windowDesign) {
        this.mainPanel = mainPanel;
        this.windowsVector = windowsVector;
        this.com = com;
        this.windowDesign = windowDesign;
    }

    public void addContent() {
        addCard1Compontens();
    }


    public void addCard1Compontens(){
        // OPTION PANEL //
        JRadioButton option1 = new JRadioButton("Default settings");
        JRadioButton option2 = new JRadioButton("Custom settings");
        option1.setFont(new Font("Arial", Font.BOLD, 17)); // Custom font
        option2.setFont(new Font("Arial", Font.BOLD, 17)); // Custom font

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        optionPanel.add(option1);
        optionPanel.add(option2);
        option1.setSelected(true);


        // CUSTOM SETTINGS PANEL //
        JPanel customPanelFuntion = addCustomSettings();            // panel from other function
        customPanelFuntion.setVisible(false);                       // by default

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customPanelFuntion.isVisible()) {
                    customPanelFuntion.setVisible(false);
                    WindowConfigDesign.this.mainPanel.revalidate();
                    WindowConfigDesign.this.mainPanel.repaint();
                }
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customPanelFuntion.isVisible()) {
                    customPanelFuntion.setVisible(true);
                    WindowConfigDesign.this.mainPanel.revalidate();
                    WindowConfigDesign.this.mainPanel.repaint();
                }
            }
        });


        // SAVE BUTTON //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(110, 35));
        saveButton.setFont(new Font("Arial", Font.BOLD, 19));
        saveButton.setBackground(colorVector.get(3));
        saveButton(saveButton);
        buttonPanel.add(saveButton);


        this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(addHeader("Add Window"));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(addInput("Name", 5, 0, textFieldTable));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        mainPanel.add(optionPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(customPanelFuntion);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        mainPanel.add(Box.createVerticalGlue());
    }

    public void saveButton(JButton saveButton) {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldTable[0].getText();
                if(!name.isEmpty()) {
                    Objects.Window window = new Window(name, null, com);


                    windowsVector.add(window);
                    com.setComentCom1("New Window added: " + windowsVector.get(windowsVector.size() - 1).getNazwa());
                    windowDesign.refreshPanel();

                    // Custom settings
                    if (!durationTime.getText().isEmpty()) {
                        //com.setComentCom1(durationTime.getText());
                        window.setVentilationDuration(Integer.parseInt(durationTime.getText()));
                        com.setComentCom1("szef: " + window.getVentilationDuration());
                    }

                    if (!closeTime.getText().isEmpty()) {
                        window.setCloseHour(Integer.parseInt(closeTime.getText()));
                        com.setComentCom1("close: " + window.getCloseHour());
                    }

                    if (!openTimeArea.getText().isEmpty()) {
                        window.setOpenHour(Integer.parseInt(openTimeArea.getText()));
                        com.setComentCom1("open: " + window.getOpenHour());
                    }
                }
            }
        });
    }

    public JPanel addCustomSettings() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // PANEL 1 //
        JPanel P1 = new JPanel();
        P1.setLayout(new BoxLayout(P1, BoxLayout.Y_AXIS));

        JPanel  P1_1 = new JPanel();   // Panel 1_1
        P1_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel SLabel2b1 = new JLabel("Auto time ventialtion:");
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
        JLabel P1_3L = new JLabel("Duration: ");
        P1_3L.setFont(new Font("Arial", Font.PLAIN, 17));
        durationTime = new JTextField(2);
        durationTime.setFont(new Font("Arial", Font.PLAIN, 16));
        durationTime.setHorizontalAlignment(SwingConstants.CENTER);
        P1_3.add(P1_3L);
        P1_3.add(durationTime);

        P1.add(Box.createVerticalGlue());
        P1.add(P1_1);
        P1.add(Box.createRigidArea(new Dimension(10, 5)));
        P1.add(P1_2);
        P1.add(Box.createRigidArea(new Dimension(10, 0)));
        P1.add(P1_3);
        P1.add(Box.createRigidArea(new Dimension(10, 15)));
        P1.add(Box.createVerticalGlue());

        panel.add(P1);

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
        JLabel P2_2L = new JLabel("Auto Close Time: ");
        P2_2L.setFont(new Font("Arial", Font.PLAIN, 17));
        closeTime = new JTextField(2);
        closeTime.setFont(new Font("Arial", Font.PLAIN, 16));
        closeTime.setHorizontalAlignment(SwingConstants.CENTER);
        P2_2.add(P2_2L);
        P2_2.add(closeTime);


        P2.add(Box.createVerticalGlue());
        P2.add(P2_1);
        P2.add(Box.createRigidArea(new Dimension(10, 5)));
        P2.add(P2_2);
        P2.add(Box.createRigidArea(new Dimension(10, 5)));
        P2.add(Box.createVerticalGlue());

        panel.add(P2);

        return panel;
    }

}
