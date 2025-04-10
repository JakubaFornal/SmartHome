package Design;

import Objects.Alarm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Design.colors.colorVector;

public class AlarmDesign {
    private JPanel mainPanel;
    private JLabel alarmStatus;
    private Alarm alarm;
    private JButton alarmButton;

    public AlarmDesign(Alarm alarm, JPanel mainPanel, JButton alarmButton, JLabel statusLabel) {
        this.alarm = alarm;
        this.mainPanel = mainPanel;
        this.alarmButton = alarmButton;
        this.alarmStatus = statusLabel;
    }

    public void addComponents() {
        Color newColor = Color.decode("#EBEBEB");
        mainPanel.setLayout(new BorderLayout());


        // Heading //
        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("Alarm");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 27));
        headingPanel.add(headingLabel);
        //headingPanel.setBackground(newColor);
        headingPanel.setBorder(new EmptyBorder(40, 10, 0, 10));

        mainPanel.add(headingPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        //p1.setPreferredSize(new Dimension(300, 200));
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setBorder(new EmptyBorder(40, 5, 5, 5));

        // p1_1
        JPanel p1_1 = new JPanel();
        p1_1.setLayout(new BoxLayout(p1_1, BoxLayout.X_AXIS));

        JLabel statusTextP1 = new JLabel("Alarm Status: ");
        statusTextP1.setFont(new Font("Arial", Font.PLAIN, 18));
        statusTextP1.setAlignmentY(Component.CENTER_ALIGNMENT);

        // status def
        alarmStatus.setText(alarm.getStatus());
        alarmStatus.setFont(new Font("Arial", Font.BOLD, 18));
        alarmStatus.setAlignmentY(Component.CENTER_ALIGNMENT);

        p1_1.add(statusTextP1);
        p1_1.add(Box.createRigidArea(new Dimension(10, 10)));
        p1_1.add(alarmStatus);

        // button def
        alarmButton.setText(alarm.getStatusButton());
        alarmButton.setPreferredSize(new Dimension(150, 45));
        alarmButton.setFont(new Font("Arial", Font.BOLD, 18));
        alarmButton.setMargin(new Insets(0, 0, 0, 0));
        alarmButton.setBackground(colorVector.get(3));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(alarmButton);

        alarmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alarm.changeStatus();
            }
        });

        p1.add(Box.createVerticalBox());
        p1.add(p1_1);
        p1.add(Box.createRigidArea(new Dimension(10, 10)));
        p1.add(buttonPanel);
        p1.add(Box.createVerticalGlue());
        contentPanel.add(p1, BorderLayout.NORTH);

        // p2
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.setPreferredSize(new Dimension(300, 200));

            // p2_1
        JPanel p2_1 = new JPanel();
        p2_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2_1.setMaximumSize(new Dimension(300, 50));

        JLabel label_P2_1 = new JLabel("Auto Active time: ");
        label_P2_1.setFont(new Font("Arial", Font.PLAIN, 17));
        JTextArea textField_P2_1 = new JTextArea(1, 2);
        textField_P2_1.setFont(new Font("Arial", Font.BOLD, 17));
        textField_P2_1.setMargin(new Insets(2, 10, 2, 2));

        p2_1.add(label_P2_1);
        p2_1.add(textField_P2_1);


            // p2_2
        JPanel p2_2 = new JPanel();
        p2_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2_2.setMaximumSize(new Dimension(300, 50));

        JLabel label_P2_2 = new JLabel("Auto Deactive time: ");
        label_P2_2.setFont(new Font("Arial", Font.PLAIN, 18));
        JTextArea textField_P2_2 = new JTextArea(1, 2);
        textField_P2_2.setFont(new Font("Arial", Font.BOLD, 17));
        textField_P2_2.setMargin(new Insets(2, 10, 2, 2));

        p2_2.add(label_P2_2);
        p2_2.add(textField_P2_2);

            // p2_2
        JPanel p2_3 = new JPanel();
        p2_3.setMaximumSize(new Dimension(300, 40));
        p2_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2_1.setMaximumSize(new Dimension(300, 40));

        JButton button_p2 = new JButton("Save Changes");
        button_p2.setFont(new Font("Arial", Font.BOLD, 18));
        button_p2.setPreferredSize(new Dimension(150, 45));
        button_p2.setMargin(new Insets(0, 0, 0, 0));
        button_p2.setBackground(colorVector.get(3));

        button_p2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textField_P2_2.getText().isEmpty()){
                    alarm.setDeactiveTimeAlarm(Integer.parseInt(textField_P2_2.getText()));
                }
                if(!textField_P2_1.getText().isEmpty()){
                    alarm.setActiveTimeAlarm(Integer.parseInt(textField_P2_1.getText()));
                }
            }
        });
        p2_3.add(button_p2);

        p2.add(Box.createVerticalGlue());
        p2.add(p2_1);
        p2.add(Box.createRigidArea(new Dimension(10, 5)));
        p2.add(p2_2);
        p2.add(Box.createRigidArea(new Dimension(10, 6)));
        p2.add(p2_3);
        p2.add(Box.createVerticalGlue());


        contentPanel.add(p2, BorderLayout.CENTER);

        // p3
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(300, 200));
        p3.setBorder(new EmptyBorder(5, 5, 40, 5));
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

            // p3_1
            JPanel p3_1 = new JPanel();
            p3_1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label_P3 = new JLabel("Other settings: ");
            label_P3.setFont(new Font("Arial", Font.BOLD, 18));
            p3_1.add(label_P3);

            // p3_2
            JPanel p3_2 = new JPanel();
            p3_2.setLayout(new FlowLayout(FlowLayout.CENTER));
            JCheckBox checkBox1 = new JCheckBox("Notify Police");
            checkBox1.setFont(new Font("Arial", Font.PLAIN, 18));
            checkBox1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    alarm.setNotifyPolice();
                }
            });
            p3_2.add(checkBox1);

            // p3_3
            JPanel p3_3 = new JPanel();
            p3_3.setLayout(new FlowLayout(FlowLayout.CENTER));
            JCheckBox checkBox2 = new JCheckBox("Turn all the Lights");
            checkBox2.setFont(new Font("Arial", Font.PLAIN, 18));
            checkBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alarm.setTurnAllLights();
            }
            });
            p3_3.add(checkBox2);

            // p3_4
            JPanel p3_4 = new JPanel();
            p3_4.setLayout(new FlowLayout(FlowLayout.CENTER));
            JCheckBox checkBox3 = new JCheckBox("Turn on Syrene");
            checkBox3.setFont(new Font("Arial", Font.PLAIN, 18));
            checkBox3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    alarm.setTurnOnSyrene();
                }
            });
            p3_4.add(checkBox3);

        p3.add(Box.createVerticalGlue());
        p3.add(p3_1);
        p3.add(p3_2);
        p3.add(p3_3);
        p3.add(p3_4);
        p3.add(Box.createRigidArea(new Dimension(10, 50)));
        p3.add(Box.createVerticalGlue());

        contentPanel.add(p3, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);


    }
}
