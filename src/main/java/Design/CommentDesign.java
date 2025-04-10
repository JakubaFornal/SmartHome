package Design;

import Objects.comment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.stream.events.Comment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentDesign {
    private  JDialog mainDialog;
    private comment com1;

    public CommentDesign(JDialog mainDialog) {
        this.mainDialog = mainDialog;
    }

    public void addComments() {
        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BorderLayout());
        commentPanel.setPreferredSize(new Dimension(400, 80));

        commentPanel.setBorder(new EmptyBorder(15, 20, 15, 10));

        //sec com
        JLabel secondCommentLabel = new JLabel();
        comment com2 = new comment(secondCommentLabel, null, null);
        secondCommentLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        commentPanel.add(secondCommentLabel, BorderLayout.CENTER);

        //third com
        JLabel thirdCommentLabel = new JLabel();
        comment com3 = new comment(thirdCommentLabel, null, null);
        thirdCommentLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        commentPanel.add(thirdCommentLabel, BorderLayout.NORTH);

        //first com
        JLabel firstCommentLabel = new JLabel("First Comment");
        this.com1 = new comment(firstCommentLabel, com2, com3);
        firstCommentLabel.setFont(new Font("Arial", Font.BOLD, 18));

        commentPanel.add(firstCommentLabel, BorderLayout.SOUTH);


        commentPanel.setBackground(Color.WHITE);
        mainDialog.setLayout(new BorderLayout());
        mainDialog.add(commentPanel, BorderLayout.CENTER);

    }

    public comment getComment() {
        return com1;
    }
}
