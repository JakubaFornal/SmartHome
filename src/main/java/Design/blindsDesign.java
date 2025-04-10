package Design;

import Objects.Window;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class blindsDesign {
    private JPanel mainPanel;
    private JLabel[][] labelTable;
    private JLabel SLabel1b = new JLabel();
    private comment com;
    private int hour;
    private JComboBox<String> comboBox;
    private Window editingWindow;
    private JList<String> list;
    DefaultListModel<String> listModel;
    private JTextField closeTime;
    private JTextField durationTime;
    private JTextField openTime;
    private Vector<Window> windowsVector;
    private JPanel contentPanel;
    private JPanel lastHorisontalPanel;
    private JPanel contentPanelBlinds;
    private JButton chageTab;

    public blindsDesign(JPanel mainPanel, comment com, Vector<Window> windowsVector, JButton chageTab) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.windowsVector = windowsVector;
        this.chageTab = chageTab;
    }

    public void addComponent() {
        SwingUtilities.invokeLater(() -> {
            Color newColor = Color.decode("#EBEBEB");
            mainPanel.setLayout(new BorderLayout());

            CardLayout cardLayoutHeader = new CardLayout();
            JPanel headerP = new JPanel(cardLayoutHeader);

            // Heading // -- Window case
            JPanel headingPanelWindow= new JPanel();
            headingPanelWindow.setLayout(new BoxLayout(headingPanelWindow, BoxLayout.Y_AXIS));

            JPanel H1Panel = new JPanel();
            H1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel headingLabel = new JLabel("Blinds");
            headingLabel.setFont(new Font("Arial", Font.BOLD, 27));
            H1Panel.add(headingLabel);
            headingPanelWindow.add(H1Panel);

            JPanel H2Panel = new JPanel();
            chageTab.setText("Blinds");
            chageTab.setFont(new Font("Arial", Font.BOLD, 13));
            H2Panel.add(chageTab);
            headingPanelWindow.add(H2Panel);

            headingPanelWindow.setBorder(new EmptyBorder(40, 10, 0, 10));

            headerP.add(headingPanelWindow, "1");
            mainPanel.add(headerP, BorderLayout.NORTH);


            // Card Layout //
            JPanel viewPanel = new JPanel();
            CardLayout cardLayout = new CardLayout();
            viewPanel.setLayout(cardLayout);

            // Windows panel //
            JPanel windowsPanel = new JPanel();
            //windowsPanel.add(new JLabel("Windows Panel"));


            contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));


            //int counter = 0;
           //addButtons();

            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setBorder(null);
            scrollPane.setPreferredSize(new Dimension(400, 410));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setWheelScrollingEnabled(true);
            scrollPane.addMouseWheelListener(new MouseAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    JScrollBar bar = scrollPane.getVerticalScrollBar();
                    bar.setValue(bar.getValue() + e.getWheelRotation() * 10);
                }
            });


            windowsPanel.add(scrollPane);

            viewPanel.add(windowsPanel, "1");



            //**//  Settings Panel //**//
            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new BorderLayout());


            /*/  Panel1 /*/
            JPanel SPanel1 = new JPanel();
            SPanel1.setLayout(new BoxLayout(SPanel1, BoxLayout.Y_AXIS));

            // Panel1a
            JPanel SPanel1a = new JPanel();
            SPanel1a.setLayout(new FlowLayout(FlowLayout.CENTER));

            JLabel SLabel1a = new JLabel("Are windows are closed: ");
            SLabel1a.setFont(new Font("Arial", Font.PLAIN, 19));
            SLabel1b.setText("True");
            SLabel1b.setFont(new Font("Arial", Font.BOLD, 20));

            SPanel1a.add(SLabel1a);
            SPanel1a.add(SLabel1b);
            SPanel1.add(SPanel1a);

            // Panel1b
            JPanel SPanel1b = new JPanel();
            SPanel1b.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton SButton2 = new JButton("Close ALL");
            SButton2.setFont(new Font("Arial", Font.BOLD, 19));
            SButton2.setPreferredSize(new Dimension(130, 40));
            SButton2.setBackground(colors.colorVector.get(0));
            SButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeAllWindows();
                }
            });
            SPanel1b.add(SButton2);
            SPanel1.add(SPanel1b);

            settingsPanel.add(SPanel1, BorderLayout.NORTH);


            //Panel 2
            JPanel SPanel2 = new JPanel();
            SPanel2.setLayout(new BorderLayout());
            SPanel2.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));

            /*/ Panel 2a /*/
            JPanel SPanel2a = new JPanel();
            SPanel2a.setLayout(new BoxLayout(SPanel2a, BoxLayout.Y_AXIS));

            JPanel SPanel2a1 = new JPanel();   //2a1
            SPanel2a1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2a1 = new JLabel("Select Window:");
            SLabel2a1.setFont(new Font("Arial", Font.BOLD, 19));
            SPanel2a1.add(SLabel2a1, BorderLayout.NORTH);

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

            SPanel2a.add(Box.createVerticalGlue());
            SPanel2a.add(SPanel2a1);
            SPanel2a.add(Box.createRigidArea(new Dimension(10, 1)));
            SPanel2a.add(comboPanel);
            SPanel2a.add(Box.createVerticalGlue());

            SPanel2.add(SPanel2a, BorderLayout.NORTH);



            /*/ Panel 2b /*/
            JPanel SPanel2b = new JPanel();
            SPanel2b.setLayout(new BoxLayout(SPanel2b, BoxLayout.Y_AXIS));

            JPanel SPanel2b1 = new JPanel();   // 2b1
            SPanel2b1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2b1 = new JLabel("Auto time ventialtion:");
            SLabel2b1.setFont(new Font("Arial", Font.BOLD, 19));
            SPanel2b1.add(SLabel2b1);


            JPanel SPanel2b2 = new JPanel();    // 2b2
            SPanel2b2.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2b2 = new JLabel("Open Time: ");
            SLabel2b2.setFont(new Font("Arial", Font.PLAIN, 17));
            openTime = new JTextField("null", 3);
            openTime.setFont(new Font("Arial", Font.PLAIN, 16));
            openTime.setBorder(null);
            openTime.setHorizontalAlignment(JTextField.CENTER);
            SPanel2b2.add(SLabel2b2);
            SPanel2b2.add(openTime);


            JPanel SPanel2b3 = new JPanel();        // 2b3
            SPanel2b3.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2b3 = new JLabel("Duration: ");
            SLabel2b3.setFont(new Font("Arial", Font.PLAIN, 17));
            durationTime = new JTextField("null", 3);
            durationTime.setFont(new Font("Arial", Font.PLAIN, 16));
            durationTime.setBorder(null);
            durationTime.setHorizontalAlignment(JTextField.CENTER);
            SPanel2b3.add(SLabel2b3);
            SPanel2b3.add(durationTime);

            SPanel2b.add(Box.createVerticalGlue());
            SPanel2b.add(Box.createRigidArea(new Dimension(10, 10)));
            SPanel2b.add(SPanel2b1);
            SPanel2b.add(Box.createRigidArea(new Dimension(10, 0)));
            SPanel2b.add(SPanel2b2);
            SPanel2b.add(Box.createRigidArea(new Dimension(10, 0)));
            SPanel2b.add(SPanel2b3);
            SPanel2b.add(Box.createVerticalGlue());

            SPanel2.add(SPanel2b, BorderLayout.CENTER);


            /*/ Panel 2c /*/
            JPanel SPanel2c = new JPanel();
            SPanel2c.setLayout(new BoxLayout(SPanel2c, BoxLayout.Y_AXIS));

            JPanel SPanel2c1 = new JPanel();   // 2c1
            SPanel2b1.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2c1 = new JLabel("Other settings:");
            SLabel2c1.setFont(new Font("Arial", Font.BOLD, 19));
            SPanel2c1.add(SLabel2c1);


            JPanel SPanel2c2 = new JPanel();    // 2c2
            SPanel2c2.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel SLabel2c2 = new JLabel("Auto Close Time: ");
            SLabel2c2.setFont(new Font("Arial", Font.PLAIN, 17));
            closeTime = new JTextField("null", 3);
            closeTime.setFont(new Font("Arial", Font.PLAIN, 16));
            closeTime.setBorder(null);
            closeTime.setHorizontalAlignment(JTextField.CENTER);
            SPanel2c2.add(SLabel2c2);
            SPanel2c2.add(closeTime);


            JPanel SPanel2c3 = new JPanel();        // 2c3
            SPanel2c3.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton saveChangesButton = new JButton("Save Changes");
            saveChangesButton.setFont(new Font("Arial", Font.BOLD, 18));
            saveChangesButton.setPreferredSize(new Dimension(155, 40));
            saveChangesButton.setBackground(colors.colorVector.get(0));

            saveChangesButton.addActionListener(new ActionListener() {

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

            SPanel2c3.add(saveChangesButton);


            SPanel2c.add(Box.createVerticalGlue());
            SPanel2c.add(Box.createRigidArea(new Dimension(10, 10)));
            SPanel2c.add(SPanel2c1);
            SPanel2c.add(Box.createRigidArea(new Dimension(10, 2)));
            SPanel2c.add(SPanel2c2);
            SPanel2c.add(Box.createRigidArea(new Dimension(10, 20)));
            SPanel2c.add(SPanel2c3);
            SPanel2c.add(Box.createRigidArea(new Dimension(10, 20)));
            SPanel2c.add(Box.createVerticalGlue());

            SPanel2.add(SPanel2c, BorderLayout.SOUTH);



            settingsPanel.add(SPanel2, BorderLayout.CENTER);

            viewPanel.add(settingsPanel, "2");
            mainPanel.add(viewPanel, BorderLayout.CENTER);


            // Menu INNER //
            JPanel menuPanel = new JPanel();
            JButton panel1 = new JButton("Windows");
            JButton panel2 = new JButton("Settings");

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
            SLabel1b.setText("True");
            //System.out.println("Close ALL");
        } else {
            SLabel1b.setText("False");
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





