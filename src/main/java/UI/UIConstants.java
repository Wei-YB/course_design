package main.java.UI;

import javax.swing.*;
import java.awt.*;

public class UIConstants {

    public static final String APP_NAME = "xx";

    public static final String APP_VERSION = "1.0";

    /**
     * Color
     */
    public static final Color MAIN_COLOR = Color.WHITE;

    public static final Color TOOL_BAR_COLOR = new Color(37, 174, 96);

    /**
     * Icon
     */
    public static final ImageIcon ICON_DATABASE = new ImageIcon(
            AppMain.class.getResource("/icon/database.png"));

    public static final ImageIcon ICON_OPERATOR = new ImageIcon(
            AppMain.class.getResource("/icon/operator.png"));

    /**
     * Size
     */
    public static final int TOOL_BAR_WIDTH = 128;

    public static final Dimension LABEL_SIZE = new Dimension(78, 30);

    public static final int SCREEN_WIDTH = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int SCREEN_HEIGHT = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final Rectangle APP_BOUNDS = new Rectangle(
            (SCREEN_WIDTH - 1100) / 2, (SCREEN_HEIGHT - 700) / 2,
            1100, 700);

    /**
     * Text
     */
    public static final String[] TABLE_HEADS = {"id", "username", "password", "privilege"};


}
