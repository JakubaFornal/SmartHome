package Objects;
import javax.swing.*;

public class comment {
    private comment com2;
    private comment com3;
    JLabel labelComent = new JLabel();
    boolean content;

    public comment(JLabel label, comment com2, comment com3) {
        this.labelComent = label;
        this.com2 = com2;
        this.com3 = com3;
    }

    public void setComentCom1(String content) {
        if(this.content){
            String content1 = this.labelComent.getText();
            if(com2.content){
                String content2 = com2.labelComent.getText();
                com3.labelComent.setText(content2);
                com3.setContentBoolean();
                com2.labelComent.setText(content1);
            }
            else{
                com2.labelComent.setText(content1);
                com2.setContentBoolean();
            }
        }
        this.labelComent.setText(content);
        this.content = true;
    }

    public void setContentBoolean(){
        this.content = true;
    }


}
