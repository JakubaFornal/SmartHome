package Design;

import Objects.Window;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static Design.colors.colorVector;


public class WindowDesign {
    private JPanel mainPanel;
    private JLabel[][] labelTable;
    private JLabel  SetL1a2;
    private comment com;
    private int hour;
    private JComboBox<String> comboBox;
    private Window editingWindow;
    private JTextField closeTime;
    private JTextField durationTime;
    private JTextField openTime;
    private Vector<Window> windowsVector;
    private JPanel contentPanel;

    public WindowDesign(JPanel mainPanel, comment com, Vector<Window> windowsVector) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.windowsVector = windowsVector;
    }

    public void addComponent() {
        SwingUtilities.invokeLater(() -> {
            mainPanel.setLayout(new BorderLayout());

            /// HEADING ///
            JPanel headingP= new JPanel();
            //headingP.setBackground(colorVector.get(2));                                   // color of heading
            headingP.setLayout(new FlowLayout(FlowLayout.CENTER));

            JPanel heading1P = new JPanel();
            heading1P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel headingL = new JLabel("Window");
            headingL.setFont(new Font("Arial", Font.BOLD, 27));
            heading1P.add(headingL);
            headingP.add(heading1P);

            headingP.setBorder(new EmptyBorder(30, 10, 10, 10));

            mainPanel.add(headingP , BorderLayout.NORTH);


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


                //**// SETTINGS PANEL  //**//
            JPanel SetP = new JPanel();
            SetP.setLayout(new BorderLayout());


            /*/  Settings Panel 1 /*/
            JPanel SetP1 = new JPanel();
            SetP1.setLayout(new BoxLayout(SetP1, BoxLayout.Y_AXIS));

            // Settings P1 //
            JPanel SetP1a = new JPanel();
            SetP1a.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SetL1a = new JLabel("Are windows are closed: ");
            SetL1a.setFont(new Font("Arial", Font.PLAIN, 19));
            SetL1a2 = new JLabel();
            SetL1a2.setText("True");
            SetL1a2.setFont(new Font("Arial", Font.BOLD, 20));
            SetP1a.add(SetL1a);
            SetP1a.add(SetL1a2);
            SetP1.add(SetP1a);

            // Panel1b
            JPanel SetP1b = new JPanel();
            SetP1b.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton Set1B = new JButton("Close ALL");
            Set1B.setPreferredSize(new Dimension(130, 40));
            Set1B.setFont(new Font("Arial", Font.BOLD, 19));
            Set1B.setBackground(colorVector.get(3));
            Set1B.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeAllWindows();
                }
            });
            SetP1b.add(Set1B);
            SetP1.add(SetP1b);
            SetP.add(SetP1, BorderLayout.NORTH);


            //Panel 2
            JPanel Set2P = new JPanel();
            Set2P.setLayout(new BorderLayout());
            Set2P.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));

            /*/ Panel 2a /*/
            JPanel  Set2aP = new JPanel();
            Set2aP.setLayout(new BoxLayout(Set2aP, BoxLayout.Y_AXIS));

            JPanel Set2a1P = new JPanel();   //2a1
            Set2a1P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel Set2a1L = new JLabel("Select Window:");
            Set2a1L.setFont(new Font("Arial", Font.BOLD, 19));
            Set2a1P.add(Set2a1L, BorderLayout.NORTH);

            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            comboBox = new JComboBox<>();
            for (Window w :  windowsVector) {
                comboBox.addItem(w.getNazwa());
            }
            comboBox.setFont(new Font("Arial", Font.PLAIN, 16));

            if(windowsVector.size() > 0){
                editingWindow = windowsVector.get(0);
            }
            else
                editingWindow = new Window(null, null,null);

            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nazwa = comboBox.getSelectedItem().toString();
                    editingWindow = getEditingWindow(nazwa);
                    com.setComentCom1(editingWindow.getNazwa());
                    setTextAreaValues();
                }
            });
            comboPanel.add(comboBox);

            Set2aP.add(Box.createVerticalGlue());
            Set2aP.add(Set2a1P);
            Set2aP.add(Box.createRigidArea(new Dimension(10, 1)));
            Set2aP.add(comboPanel);
            Set2aP.add(Box.createVerticalGlue());

            Set2P.add(Set2aP, BorderLayout.NORTH);


            /*/ Panel 2b /*/
            JPanel Set2bP = new JPanel();
            Set2bP.setLayout(new BoxLayout(Set2bP, BoxLayout.Y_AXIS));

            JPanel Set2b1P = new JPanel();   // 2b1
            Set2b1P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel Set2b1L = new JLabel("Auto time ventialtion:");
            Set2b1L.setFont(new Font("Arial", Font.BOLD, 19));
            Set2b1P.add(Set2b1L);

            JPanel Set2b2P = new JPanel();    // 2b2
            Set2b2P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel  Set2b2L = new JLabel("Open Time: ");
            Set2b2L.setFont(new Font("Arial", Font.PLAIN, 17));
            openTime = new JTextField("null", 3);
            openTime.setFont(new Font("Arial", Font.PLAIN, 16));
            openTime.setBorder(null);
            openTime.setHorizontalAlignment(JTextField.CENTER);
            Set2b2P.add(Set2b2L);
            Set2b2P.add(openTime);


            JPanel Set2b3P  = new JPanel();        // 2b3
            Set2b3P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SetL2b3 = new JLabel("Duration: ");
            SetL2b3.setFont(new Font("Arial", Font.PLAIN, 17));
            durationTime = new JTextField("null", 3);
            durationTime.setFont(new Font("Arial", Font.PLAIN, 16));
            durationTime.setBorder(null);
            durationTime.setHorizontalAlignment(JTextField.CENTER);
            Set2b3P.add(SetL2b3);
            Set2b3P.add(durationTime);

            Set2bP.add(Box.createVerticalGlue());
            Set2bP.add(Box.createRigidArea(new Dimension(10, 10)));
            Set2bP.add(Set2b1P);
            Set2bP.add(Box.createRigidArea(new Dimension(10, 0)));
            Set2bP.add(Set2b2P);
            Set2bP.add(Box.createRigidArea(new Dimension(10, 0)));
            Set2bP.add(Set2b3P);
            Set2bP.add(Box.createVerticalGlue());
            Set2P.add(Set2bP, BorderLayout.CENTER);


            /*/ Panel 2c /*/
            JPanel Set2cP = new JPanel();
            Set2cP.setLayout(new BoxLayout(Set2cP, BoxLayout.Y_AXIS));

            JPanel Set2c1P = new JPanel();   // 2c1
            Set2c1P .setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel Set2c1L  = new JLabel("Other settings:");
            Set2c1L.setFont(new Font("Arial", Font.BOLD, 19));
            Set2c1P.add(Set2c1L);

            JPanel Set2c2P = new JPanel();    // 2c2
            Set2c2P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel Set2c2L = new JLabel("Auto Close Time: ");
            Set2c2L.setFont(new Font("Arial", Font.PLAIN, 17));
            closeTime = new JTextField("null", 3);
            closeTime.setFont(new Font("Arial", Font.PLAIN, 16));
            closeTime.setBorder(null);
            closeTime.setHorizontalAlignment(JTextField.CENTER);
            Set2c2P.add(Set2c2L);
            Set2c2P.add(closeTime);


            JPanel Set2c3P = new JPanel();        // 2c3
            Set2c3P.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton saveButton = new JButton("Save");
            saveButton.setPreferredSize(new Dimension(120, 40));
            saveButton.setFont(new Font("Arial", Font.BOLD, 19));
            saveButton.setBackground(colorVector.get(3));

            saveButton.addActionListener(new ActionListener() {

                String notSet = "null";
                public void actionPerformed(ActionEvent e) {
                    if(!closeTime.getText().isEmpty() && !closeTime.getText().equals(notSet)){
                        editingWindow.setCloseHour(Integer.parseInt(closeTime.getText()));
                        com.setComentCom1(editingWindow.getNazwa() + " close " + editingWindow.getCloseHour());
                    }

                    if(!durationTime.getText().isEmpty()) {
                        editingWindow.setVentilationDuration(Integer.parseInt(durationTime.getText()));
                        com.setComentCom1(editingWindow.getNazwa() + " duration  " + editingWindow.getVentilationDuration());
                    }


                    // ten if się nie wykonuje XD
                    if(!openTime.getText().isEmpty() && !openTime.getText().equals(notSet)) {
                        editingWindow.setOpenHour(Integer.parseInt(openTime.getText()));
                        com.setComentCom1(editingWindow.getNazwa() + " open " + editingWindow.getOpenHour());
                    }
                }
            });

            Set2c3P.add(saveButton);


            Set2cP.add(Box.createVerticalGlue());
            Set2cP.add(Box.createRigidArea(new Dimension(10, 10)));
            Set2cP.add(Set2c1P);
            Set2cP.add(Box.createRigidArea(new Dimension(10, 3)));
            Set2cP.add(Set2c2P);
            Set2cP.add(Box.createRigidArea(new Dimension(10, 25)));
            Set2cP.add(Set2c3P);
            Set2cP.add(Box.createRigidArea(new Dimension(10, 20)));
            Set2cP.add(Box.createVerticalGlue());

            Set2P.add(Set2cP, BorderLayout.SOUTH);
            SetP.add(Set2P, BorderLayout.SOUTH);


            viewPanel.add(SetP , "2");
            mainPanel.add(viewPanel, BorderLayout.CENTER);


            // Menu INNER //
            ImageIcon arrow_right = new ImageIcon("src/main/java/image/arrow.png");
            ImageIcon arrow_left = new ImageIcon("src/main/java/image/arrow_left.png");

            JPanel menuPanel = new JPanel();
            JButton panel1 = new JButton(arrow_left);
            JButton panel2 = new JButton(arrow_right);

            panel1.setBorderPainted(false);
            panel1.setContentAreaFilled(false);
            panel2.setBorderPainted(false);
            panel2.setContentAreaFilled(false);


            panel1.addActionListener(e -> cardLayout.show(viewPanel, "1"));
            panel2.addActionListener(e -> cardLayout.show(viewPanel, "2"));

            menuPanel.add(panel1);
            menuPanel.add(panel2);


            mainPanel.add(menuPanel, BorderLayout.SOUTH);
            setTextAreaValues();    // setting for first index in combo after lunching
        });
    }

    public void addButtons(){

        for (int i = 0; i < windowsVector.size(); i = i + 2) {
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));


            // Button LEFT
            JButton button1 = new JButton(windowsVector.get(i).getNazwa());
            button1.setFont(new Font("Arial", Font.BOLD, 17));
            button1.setPreferredSize(new Dimension(150, 60));
            button1.setBackground(Color.RED);
            windowsVector.get(i).setJButton(button1);

            int index = i;
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    windowsVector.get(index).changeStatus();
                    System.out.println(windowsVector.get(index).getButtonText());
                }
            });

            button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        windowsVector.get(index).openScheduleManual(hour);
                        button1.setBackground(Color.ORANGE);
                        com.setComentCom1(windowsVector.get(index).getNazwa() + " ventialtion for " + windowsVector.get(index).getVentilationDuration() + " hours");
                    }
                }
            });
            horizontalPanel.add(button1);

            // Button RIGHT
            int index2 = i + 1;
            if (i + 1 < windowsVector.size()) {
                JButton button2 = new JButton(windowsVector.get(i + 1).getNazwa());
                button2.setFont(new Font("Arial", Font.BOLD, 17));
                button2.setPreferredSize(new Dimension(150, 60));
                button2.setBackground(Color.RED);
                horizontalPanel.add(button2);
                windowsVector.get(i + 1).setJButton(button2);

                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        windowsVector.get(index2 ).changeStatus();
                        System.out.println(windowsVector.get(index2).getButtonText());
                    }
                });

                button2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            windowsVector.get(index2).openScheduleManual(hour);
                            button2.setBackground(Color.ORANGE);
                            com.setComentCom1( windowsVector.get(index2).getNazwa() + " ventialtion for " +  windowsVector.get(index2).getVentilationDuration() + " hours");
                        }
                    }
                });
            }

            //System.out.println(windowTable.length);
            contentPanel.add(horizontalPanel);
        }
    }

    public boolean allWindowClosedCheck() {
        for (int i = 0; i < windowsVector.size(); i++) {
            if (windowsVector.get(i).isOpenBoolean() == true) {
                return false;
            }
        }
        return true;
    }

    ;

    public void updateHour(int hour) {
        this.hour = hour;
    }


    public void updateAllWindowsStatus() {
        if (allWindowClosedCheck()) {
            SetL1a2.setText("True");
            //System.out.println("Close ALL");
        } else {
            SetL1a2.setText("False");
            //System.out.println("Open ALL");
        }
    }

    ;

    public void closeAllWindows() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < windowsVector.size(); i++) {
            if (windowsVector.get(i).isOpenBoolean()) {
                windowsVector.get(i).close();
                windowsVector.get(i).changeButtonColor();
                sb.append(windowsVector.get(i).getNazwa() + ", ");
            }
        }
        sb.append(" win closed.");
        String content = sb.toString();
        com.setComentCom1(content);
    }

    ;

    public Window getEditingWindow(String nazwa) {
        for (Window w : windowsVector) {
            if (w.getNazwa().equals(nazwa)) {
                return w;
            }
        }
        return null;
    }

    ;

    public void setTextAreaValues(){
        durationTime.setText(String.valueOf(editingWindow.getVentilationDuration()));
        if(editingWindow.getOpenHour() == -1){
            openTime.setText("null");
        }
        else{
            openTime.setText(String.valueOf(editingWindow.getOpenHour()));
        }
        if(editingWindow.getCloseHour() == -1){
            closeTime.setText("null");
        }
        else{
            closeTime.setText(String.valueOf(editingWindow.getCloseHour()));
        }
    }

    public void refreshPanel() {
        mainPanel.removeAll(); // Clear existing buttons
        addComponent(); // Rebuild the UI with updated windows
        mainPanel.revalidate(); // Recalculate layout
        mainPanel.repaint(); // Repaint to reflect changes
    }





};





