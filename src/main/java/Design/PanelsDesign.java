package Design;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelsDesign {
    JPanel mainPanel;
    JPanel homePanel;
    CardLayout cardLayout;
    JPanel buttonP;

    public PanelsDesign(JPanel mainPanel, CardLayout cardLayout, JPanel homePanel) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.homePanel = homePanel;
    }

    public void addContent() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //**// HEADER //**//
        JPanel headerP = new JPanel();
        headerP.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headerL = new JLabel("More Panels");
        headerL.setFont(new Font("Arial", Font.BOLD, 27));
        headerP.setBorder(new EmptyBorder(30, 10, 10, 10));
        headerP.add(headerL);
        mainPanel.add(headerP);

        //**// BUTTONS //**//
        buttonP = new JPanel();
        buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.Y_AXIS));
        JScrollPane scrollP = new JScrollPane(buttonP);
        scrollP.setBorder(null);

              // GATE
        addButton("src/main/java/animal_panel.png", "4");       //ANIMAL FEEDING
        addButton("src/main/java/gate_panel.png", "2");
        addButton("src/main/java/garden_panel.png", "5");
        addButton("src/main/java/heating_panel.png", "5");
        addButton("src/main/java/blinds_panel.png", "5");
        addButton("src/main/java/devices_panel.png", "5");
        mainPanel.add(scrollP);

    }

    public void addButton(String imgPath, String index) {
        JPanel Panel = new JPanel();
        Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(imgPath);
        button.setIcon(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> cardLayout.show(homePanel, index));

        Panel.add(button);
        buttonP.add(Panel);

    }
}
