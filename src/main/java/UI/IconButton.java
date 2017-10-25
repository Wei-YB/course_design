package main.java.UI;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {

    private ImageIcon iconActivated, iconUnactivated;
    private String tips;

    public IconButton(ImageIcon iconNormal, ImageIcon iconActivated, ImageIcon iconUnactivated,
                      String text, String tips) {
        super(text, iconNormal);

        this.iconActivated = iconActivated;
        this.iconUnactivated = iconUnactivated;

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));

        this.setRolloverIcon(iconActivated);
        this.setPressedIcon(iconActivated);
        this.setDisabledIcon(iconUnactivated);

        if(!tips.equals("")) {
            this.setToolTipText(tips);
        }
    }
}
