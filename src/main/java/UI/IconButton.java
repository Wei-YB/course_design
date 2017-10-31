package main.java.UI;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {

//    private ImageIcon iconActivated, iconUnactivated;
    private String tips;

    public IconButton(ImageIcon icon, String text, String tips) {
        super(icon);

//        this.iconActivated = iconActivated;
//        this.iconUnactivated = iconUnactivated;

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(5, 0, 5, 0));
        this.setText(text);
        this.setFont(new Font("font", Font.PLAIN, 17));
        this.setForeground(new Color(55, 61, 45));

//        this.setRolloverIcon(iconActivated);
//        this.setPressedIcon(iconActivated);
//        this.setDisabledIcon(iconUnactivated);

        if(!tips.equals("")) {
            this.setToolTipText(tips);
        }
    }
}
