package Design;

import Objects.AnimalFeeding;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Design.colors.colorVector;

public class AnimalDesign {
    private JPanel mainPanel;
    private JButton feedNowButton;
    private AnimalFeeding dog;
    private JLabel animalStatus;

    public AnimalDesign(JPanel mainPanel, AnimalFeeding Feeding1, JLabel animalStatus, JButton feedButton) {
        this.mainPanel = mainPanel;
        this.dog = Feeding1;
        this.animalStatus = animalStatus;
        this.feedNowButton = feedButton;
    }


    public void addComponents() {
        // Colors
        Color newColor = Color.decode("#EBEBEB");
        mainPanel.setLayout(new BorderLayout());


        // Heading //
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headingLabel = new JLabel("Animal Feeding");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 27));
        headingPanel.add(headingLabel);
        //headingPanel.setBackground(newColor);
        //headingPanel.setPreferredSize(new Dimension(400, 50));
        headingPanel.setBorder(new EmptyBorder(40, 10, 0, 10));

        mainPanel.add(headingPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        //p1.setPreferredSize(new Dimension(300, 200));
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setBorder(new EmptyBorder(55, 5, 5, 5));

        // p1_1
        JPanel p1_1 = new JPanel();
        p1_1.setLayout(new BoxLayout(p1_1, BoxLayout.X_AXIS));

        JLabel statusTextP1 = new JLabel("Czy nakarmione: ");
        statusTextP1.setFont(new Font("Arial", Font.PLAIN, 18));
        statusTextP1.setAlignmentY(Component.CENTER_ALIGNMENT);

            // animaStatus def
        animalStatus.setText(dog.getStatus());
        animalStatus.setFont(new Font("Arial", Font.BOLD, 18));
        animalStatus.setAlignmentY(Component.CENTER_ALIGNMENT);

        p1_1.add(statusTextP1);
        p1_1.add(Box.createRigidArea(new Dimension(10, 10)));
        p1_1.add(animalStatus);

            // feedNowbutton def
        feedNowButton.setPreferredSize(new Dimension(150, 45));
        feedNowButton.setFont(new Font("Arial", Font.BOLD, 18));
        feedNowButton.setBackground(colorVector.get(3));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(feedNowButton);

        p1.add(Box.createVerticalBox());
        p1.add(p1_1);
        p1.add(Box.createRigidArea(new Dimension(10, 20)));
        p1.add(buttonPanel);
        p1.add(Box.createVerticalGlue());

        feedNowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dog.AnimalFeed();
                animalStatus.setText("Fed");
                feedNowButton.setText("Alredy fed");
            }
        });
        contentPanel.add(p1, BorderLayout.NORTH);

        ///  Panel 2 ///
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.setBorder(new EmptyBorder(50, 5, 5, 5));


            // p2a
        JPanel p2a = new JPanel();
        p2a.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel portionLabel = new JLabel("New Portion: ");
        portionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        portionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea portionField = new JTextArea(1, 2);
        portionField.setFont(new Font("Arial", Font.BOLD, 17));
        portionField.setMargin(new Insets(2, 10, 2, 2));
        portionField.setAlignmentX(Component.CENTER_ALIGNMENT);

        p2a.add(portionLabel);
        p2a.add(portionField);

            // p2b
        JPanel p2b = new JPanel();
        p2b.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel automaicFeedTimeLabel = new JLabel("Automatic Feeding Time: ");
        automaicFeedTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        automaicFeedTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea automaicFeedTimeField = new JTextArea(1, 2);
        automaicFeedTimeField.setFont(new Font("Arial", Font.BOLD, 17));
        automaicFeedTimeField.setMargin(new Insets(2, 10, 2, 2));
        automaicFeedTimeField.setAlignmentX(Component.CENTER_ALIGNMENT);

        p2b.add(automaicFeedTimeLabel);
        p2b.add(automaicFeedTimeField);



            // p2c
        JPanel p2c = new JPanel();
        p2c.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton saveButton = new JButton("Save changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));
        saveButton.setPreferredSize(new Dimension(150, 45));
        saveButton.setBackground(colorVector.get(3));

        p2c.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!automaicFeedTimeField.getText().isEmpty()){
                    String hourAutomaicFeeding = automaicFeedTimeField.getText();
                    int hour = Integer.parseInt(hourAutomaicFeeding);
                    dog.setFeedingHour(hour);
                }


                if(!portionField.getText().isEmpty()){
                    String portion = portionField.getText();
                    int portionInt = Integer.parseInt(portion);
                    dog.setPortion(portionInt);
                }
            }
        });
        p2.add(Box.createVerticalGlue());
        p2.add(p2a);
        p2.add(Box.createRigidArea(new Dimension(10, 10)));
        p2.add(p2b);
        p2.add(Box.createRigidArea(new Dimension(10, 10)));
        p2.add(p2c);
        p2.add(Box.createVerticalGlue());




        contentPanel.add(p2, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

    }
}
