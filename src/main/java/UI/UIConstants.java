package main.java.UI;

import javax.swing.*;
import java.awt.*;

public class UIConstants {

    public static final String APP_NAME = "xx";

    public static final String APP_VERSION = "1.0";

    public static final Rectangle APP_BOUNDS = new Rectangle(200, 100, 900, 600);

    /**
     * Color
     */
    public static final Color MAIN_COLOR = Color.WHITE;

    public static final Color TOOL_BAR_COLOR = new Color(37, 174, 96);

    /**
     * Icon
     */
    public static final ImageIcon ICON_A = new ImageIcon(
            AppMain.class.getResource("/icon/a.png"));

    /**
     * Size
     */
    public static final int TOOL_BAR_WIDTH = 128;
    public static final Dimension LABEL_SIZE = new Dimension(78, 30);
    public static final  Dimension INPUT_FIELD_SIZE = new Dimension(200, 30);

}
