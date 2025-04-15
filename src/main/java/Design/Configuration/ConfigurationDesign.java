package Design.Configuration;

import Design.GardenDesign;
import Design.LightDesign;
import Design.WindowDesign;
import Objects.GardenSection;
import Objects.Light;
import Objects.Window;
import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import static Design.colors.colorVector;


public class ConfigurationDesign {
    private JPanel mainPanel;
    private Window[] windowTable;
    private Vector<Window> windowsVector;
    private JPanel MenuPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel[] cardTableP;
    private JButton[] buttonsTable;
    private JTextField[] textFieldTable;
    int currIndex = -1;
    private comment com;
    private WindowDesign windowDesign;
    private Window editingWindow;
    private JTextField closeTime;
    private JTextField durationTime;
    private JTextField openTimeArea;
    private LightDesign lightDesign;
    private Vector<Light> lightVector;
    private Vector<GardenSection> gardenSectionVector;
    private GardenDesign gardenDesign;

    public ConfigurationDesign( JPanel mainPanel, Vector<Window> windowsVector, comment com, WindowDesign windowDesign, Vector<Light> lightVector, LightDesign lightDesign,  Vector<GardenSection> gardenSectionVector, GardenDesign gardenDesign) {
        this.mainPanel = mainPanel;
        this.com = com;
        this.windowDesign = windowDesign;
        this.windowsVector = windowsVector;
        this.lightVector = lightVector;
        this.lightDesign = lightDesign;
        this.gardenSectionVector = gardenSectionVector;
        this.gardenDesign = gardenDesign;
    }

    public void  addComponent() {
        mainPanel.setLayout(new BorderLayout());

        //***// HEADER PANEL //***//
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setPreferredSize(new Dimension(100, 42));
        headerPanel.setBorder(new EmptyBorder(new Insets(7, 10, 7, 10)));

        JLabel headerLabel = new JLabel("Configuration");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 19));
        headerLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        headerPanel.add(headerLabel);
        headerPanel.setBackground(colorVector.get(3));
        mainPanel.add(headerPanel, BorderLayout.NORTH);


        //***// MENU PANEL //***//
        this.MenuPanel = new JPanel();
        MenuPanel.setLayout(new GridLayout(5, 1, 0, 5));
        MenuPanel.setPreferredSize(new Dimension(115, 400));


        this.buttonsTable = new JButton[10];         // to store the buttons from the function
       addItemButton("Window", 1, "src/main/java/image/alarm.png");
       addItemButton("Light", 2, "src/main/java/image/bul_blank.png");
       addItemButton("Garden", 5, "src/main/java/image/bul_blank.png");
       addItemButton("Users", 3, "src/main/java/image/bul_blank.png");
       addItemButton("More", 4, "src/main/java/image/bul_blank.png");
       buttonsTable[0].setBackground(colorVector.get(1));

        MenuPanel.setBorder(new EmptyBorder(new Insets(0, 0, 40, 0)));
        mainPanel.add(MenuPanel, BorderLayout.WEST);

        //***// CONTENT PANEL //***//
        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);
        this.cardTableP = new JPanel[10];                                // to store card from tabs
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        this.textFieldTable = new JTextField[10];                       // to
        addCard(1, "Window");
        addCard(2, "Light");
        addCard(5, "Garden");
        addCard(3, "Users");
        addCard(4, "More");


        //***// WINDOW PANEL //***//
        JPanel WindowPanel = cardTableP[0];
        WindowPanel.setLayout(new BoxLayout(WindowPanel, BoxLayout.Y_AXIS));

        WindowConfigDesign windowConfigDesign = new WindowConfigDesign(WindowPanel, windowsVector, com, windowDesign);
        windowConfigDesign.addContent();




        //***// LIGHT PANEL //***//
        JPanel LightPanel = cardTableP[1];

        LightConfigDesign lightConfigDesign = new LightConfigDesign(LightPanel, com, lightVector,  lightDesign);
        lightConfigDesign.addContent();

        //***// GARDEN PANEL //***//
        JPanel GardenPanel = cardTableP[4];

        GardenConfigDesign gardenConfigDesign = new GardenConfigDesign(GardenPanel, com, gardenSectionVector, gardenDesign);
        gardenConfigDesign.addContent();




        //***// MORE PANEL //***//
        JPanel MorePanel = cardTableP[3];

        MoreConfigDesign moreConfigDesign = new MoreConfigDesign(MorePanel, com,  lightVector, windowsVector, lightDesign, windowDesign);
        moreConfigDesign.addContent();



        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }




    //***// DESIGN //***//
    public static JPanel addHeader(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 21));
        panel.setBorder(new EmptyBorder(new Insets(30, 0, 10, 0)));
        panel.add(titleLabel);

       return panel;
    }

    public static JPanel addInput(String title, int length, int index, JTextField[] textFieldTable) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        panel.add(titleLabel);

        JTextField field = new JTextField(length);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldTable[index] = field;

        panel.add(field);
        return panel;
    }

    public void addCard(int index, String name){
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        cardTableP[index - 1] = card;
        contentPanel.add(card, "card" + index);
    }

    public void addItemButton(String name, int index, String sourceImg) {
        ImageIcon icon = new ImageIcon(sourceImg);
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);      // button + image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton item1B = new JButton(name, scaledIcon);
        item1B.setMargin(new Insets(0, 5, 0, 0));
        item1B.setHorizontalAlignment(SwingConstants.LEFT);
        item1B.setHorizontalTextPosition(SwingConstants.RIGHT);
        item1B.setIconTextGap(8);
        item1B.setBackground(colorVector.get(2));                           // default color of buttonItem
        item1B.setFont(new Font("Arial", Font.BOLD, 18));
        item1B.setMargin(new Insets(0, 20, 0, 0));
        item1B.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));

        if(index == 0){
            item1B.setBackground(colorVector.get(1));
        }

        buttonsTable[index - 1] = item1B;
        item1B.addActionListener(e -> {changeCard(index);});

        MenuPanel.add(item1B);
    }

    void changeCard(int index){
        cardLayout.show(contentPanel, "card" + index);

        JButton button = buttonsTable[index - 1];
        button.setBackground(colorVector.get(1));                   // color of temp open card - buttonItem

        if(currIndex>= 0) {
            JButton buttonPrev = buttonsTable[currIndex - 1];
            buttonPrev.setBackground(colorVector.get(2));            // default color of - buttonItem);
        }
        else{
            buttonsTable[0].setBackground(colorVector.get(2));
        }

        currIndex = index;
    }

}
