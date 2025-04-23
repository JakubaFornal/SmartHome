import Design.*;
import Design.Configuration.ConfigurationDesign;
import Design.Configuration.MoreConfigDesign;
import Objects.*;
import Objects.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

import java.util.Vector;
import static Design.colors.colorVector; // importing the color from class Colors


public class TestHome {
    public static Vector<Window> windowsVector;
    public static Vector<GardenSection> gardenSections;
    public static Vector<Light> lightVector;
    public static ConfigurationDesign configurationDesign;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Light Theme
        } catch (Exception e) {
            e.printStackTrace();
        }

        // New
        Time VTime = new Time();

        JFrame frame = new JFrame("Smart Home");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /// //**// TOP PANEL  //**// ///
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        ///  /// TOP BAR /// ///
        JPanel topBarPanel = new JPanel();                                          // Top Bar
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.setPreferredSize(new Dimension(400, 25));
        topBarPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        topBarPanel.setBackground(colors.colorVector.get(1));

            /// MENU ///
        JPanel menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setBackground(colors.colorVector.get(3));

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem break_inItem = new JMenuItem("Break-in");
        JMenuItem commentItem = new JMenuItem("Comments");
        JMenuItem configItem = new JMenuItem("Configuration");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(break_inItem);
        fileMenu.addSeparator();
        fileMenu.add(commentItem);
        fileMenu.addSeparator();
        fileMenu.add(configItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        menuBarPanel.add(menuBar, FlowLayout.LEFT);
        topBarPanel.add(menuBarPanel, BorderLayout.WEST);

            // NAME TOP BAR ///
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        namePanel.setBorder(new EmptyBorder(4, 0, 0, 0));
        JLabel name = new JLabel("Future House");
        name.setFont(new Font("Arial", Font.BOLD, 16));
        namePanel.setBackground(colors.colorVector.get(1));
        namePanel.add(name);
        topBarPanel.add(namePanel, BorderLayout.CENTER);


            // HOUR TOP BAR ///
        JLabel topTime = new JLabel();
        topTime.setFont(new Font("Arial", Font.BOLD, 15));
        topBarPanel.add(topTime, BorderLayout.EAST);
        topPanel.add(topBarPanel, BorderLayout.NORTH);

        /// /// COMMENT PANEL  /// ///
        JDialog commentDialog = new JDialog(frame, "Comment", false);
        commentDialog.setSize(400, 150);
        CommentDesign commentDesign = new CommentDesign(commentDialog);
        commentDesign.addComments();
        comment com1 = commentDesign.getComment();

        commentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commentDialog.setVisible(true);
            }
        });
        frame.add(topPanel, BorderLayout.NORTH);

        /// //**// MAIN PANEL //**// ///
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        frame.add(mainPanel);

            ///  WINDOW ///
        JPanel card1 = new JPanel();
        windowsVector = new Vector<>();

        /*
        for(int i = 0; i < 14; i++){
            windowsVector.add(new Window("Window"+i, null, com1));
        }

         */

        WindowDesign windowDesign = new WindowDesign(card1, com1, windowsVector);
        windowDesign.addComponent();

        mainPanel.add(card1, "1");


            /// BRAMA ///
        JPanel card2 = new JPanel();

        JLabel statusGate = new JLabel();
        JButton openButton = new JButton();

        Gate Gate1 = new Gate(statusGate, com1, openButton);
        GateDesign gateDesign = new GateDesign(card2, statusGate, Gate1, openButton);
        gateDesign.addComponents();

        mainPanel.add(card2, "2");


            /// Alarm ///
        JPanel card3 = new JPanel();

        JButton alarmButton = new JButton();
        JLabel alarmLabel = new JLabel();

        Alarm Alarm1 = new Alarm(alarmLabel, alarmButton, com1);

            break_inItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Alarm1.setInterrupt();
                }
            });

        AlarmDesign alarmDesign = new AlarmDesign(Alarm1, card3, alarmButton, alarmLabel);
        alarmDesign.addComponents();
        mainPanel.add(card3, "3");


            /// Karmienie ///
        JPanel card4 = new JPanel();


        JLabel animalStatus = new JLabel();
        JButton feedButton = new JButton("Feed Now");

        AnimalFeeding dog = new AnimalFeeding("Blue", com1, feedButton, animalStatus);

        JPanel animalPanel = new JPanel();
        card4.add(animalPanel);


        AnimalDesign animalFeeding = new AnimalDesign(animalPanel, dog, animalStatus,  feedButton);
        animalFeeding.addComponents();

        mainPanel.add(card4, "4");


        ///  HOME PANEL ///
        JPanel card5 = new JPanel();


        mainPanel.add(card5, "5");

        ///  MORE PANEL ///
        JPanel card6 = new JPanel();
        PanelsDesign panlesDesign = new PanelsDesign(card6, cardLayout, mainPanel );
        panlesDesign.addContent();

        mainPanel.add(card6, "6");


        /// LIGHT PANEL ///
        JPanel card7 = new JPanel();
        lightVector = new Vector<>();
        Alarm1.setLightsVector(lightVector);
        /*
        for(int i = 0; i < 14; i++){
            Light light = new Light("Room" + i, null, com1);
            lightVector.add(light);
        }
        */


        LightDesign lightDesign = new LightDesign(card7, lightVector, com1);
        lightDesign.addComponents();

        mainPanel.add(card7, "7");


        /// GARDEN PANEL ///
        JPanel card8 = new JPanel();

        gardenSections = new Vector<>();


        for(int i = 0; i < 0; i++){
            GardenSection section = new GardenSection("Garden" + i, null, com1);
            gardenSections.add(section);
            WaterPump pump1 = new WaterPump("pump1_" + i);
            WaterPump pump2 = new WaterPump("pump2_" + i);

            section.addWaterPump(pump1);
            section.addWaterPump(pump2);
        }

        GardenDesign gardenDesign = new GardenDesign(card8, com1, gardenSections);
        gardenDesign.addContent();


        for(GardenSection section : gardenSections){
            section.setGardenDesign(gardenDesign);
        }

        //configurationDesign.setMoreConfig(gardenSections, gardenDesign);            // move it below

        mainPanel.add(card8, "8");


        ///  CONFIGURATION PANEL ///
        JDialog configurationDialog = new JDialog(frame, "Configuration", true);
        configurationDialog.setSize(520, 400);
        JPanel configurationPanel = new JPanel();
        configurationDialog.add(configurationPanel);

        configurationDesign = new ConfigurationDesign(configurationPanel, windowsVector, com1, windowDesign, lightVector, lightDesign, gardenSections, gardenDesign);
        configurationDesign.addComponent();

        configItem.addActionListener(new ActionListener() {         // item menu
            public void actionPerformed(ActionEvent e) {
                configurationDialog.setVisible(true);
            }
        });



        /// //**// MENU BOTTOM //**// ///
        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        bottomPanel.setBackground(colorVector.get(2));

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        JButton button6 = new JButton();

        addBottomMenuImage(button1, "src/main/java/image/window.png");
        addBottomMenuImage(button2, "src/main/java/image/gate_small.png");
        addBottomMenuImage(button3, "src/main/java/image/alarm.png");
        addBottomMenuImage(button4, "src/main/java/image/bul_blank.png");
        addBottomMenuImage(button5, "src/main/java/image/home_big1.png");
        addBottomMenuImage(button6 , "src/main/java/image/more.png");


        button1.addActionListener(e -> cardLayout.show(mainPanel, "1"));
        button2.addActionListener(e -> cardLayout.show(mainPanel, "2"));
        button3.addActionListener(e -> cardLayout.show(mainPanel, "3"));
        button4.addActionListener(e -> cardLayout.show(mainPanel, "7"));
        button5.addActionListener(e -> cardLayout.show(mainPanel, "5"));
        button6.addActionListener(e -> cardLayout.show(mainPanel, "6"));

        bottomPanel.add(button1);
        bottomPanel.add(button4);
        bottomPanel.add(button5);
        bottomPanel.add(button3);
        bottomPanel.add(button6);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);


        while(true) {

                int hour = VTime.getHour();                     // Time
                System.out.println(hour);
                VTime.setHour(VTime.getHour() + 1);
                String Hour = String.valueOf(hour);
                topTime.setText(Hour + ":00");

                Gate1.automaticCloseGate(hour);                // Gate

                dog.scheduleFeeding(hour);
                dog.resetStaus(hour);

                Alarm1.activateAlarm(hour);                 // Alarm
                Alarm1.deactivateAlarm(hour);
                Alarm1.interruptedCheck();              // Alarm Burglar !!

                windowDesign.updateHour(hour);              // Windows
                windowDesign.updateAllWindowsStatus();
                checkWindowCloseTime(hour);
                checkWindowOpenTime(hour);

                gardenDesign.updateHour(hour);              // Garden
                gardenDesign.checkSectionCloseTime(hour);
                gardenDesign.checkAllSectionsStatus();
                gardenDesign.checkOpenTime(hour);
                //System.out.println(gardenDesign.getClosedStatus());
                //System.out.println(gardenSections.getFirst().getVector().getFirst().getStatus());

            if(hour == 10){
                for(GardenSection section : gardenSections){
                    System.out.println(section.getName() + " " + section.getButton().getText());
                }
            }

            lightVector.add(new Light("kuba", new JButton(), com1));

                try {
                    Thread.sleep(1000);             // Delay settings
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(hour == 24)
                    VTime.setHour(0);
        }
    }

    /// DESIGN ///
    public static void addBottomMenuImage(JButton button, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        button.setIcon(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        if(imagePath == "src/main/java/image/more.png"){
            icon.setImage(icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        }
    }


    ///  FUNCTIONAL ///
    public static void checkWindowCloseTime(int hour) {
        for(int i = 0; i < windowsVector.size(); i++) {
            windowsVector.get(i).closeSchedule(hour);
        }
    }

    public static void checkWindowOpenTime(int hour) {
        for(int i = 0; i < windowsVector.size(); i++) {
            windowsVector.get(i).openSchedule(hour);
        }
    }



}
