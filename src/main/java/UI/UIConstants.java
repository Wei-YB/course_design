package main.java.UI;

import javax.swing.*;
import java.awt.*;

public class UIConstants {

    public static final String APP_NAME = "舰船电力负荷分析系统 ";

    public static final String APP_VERSION = "V1.0.0";

    /**
     * Color
     */
    public static final Color MAIN_COLOR = Color.WHITE;

    public static final Color SUB_COLOR = new Color(126, 126, 126);

    public static final Color SSUB_COLOR = new Color(166, 166, 166);

    public static final Color TOOL_BAR_COLOR = new Color(11, 209, 189);

    public static final Color TOOL_BAR_ACTIVATED_COLOR = new Color(14, 139, 124);

    /**
     * Icon
     */
    //Panels
    public static final ImageIcon ICON_DATABASE = new ImageIcon(
            AppMain.class.getResource("/icon/panel/database.png"));

    public static final ImageIcon ICON_OPERATOR = new ImageIcon(
            AppMain.class.getResource("/icon/panel/operator.png"));

    public static final ImageIcon ICON_LOGGER = new ImageIcon(
            AppMain.class.getResource("/icon/panel/logger.png"));

    public static final ImageIcon ICON_ABOUT = new ImageIcon(
            AppMain.class.getResource("/icon/panel/about.png"));

    //Others
    public static final ImageIcon ICON_PRINT = new ImageIcon(
            AppMain.class.getResource("/icon/print.png"));

    public static final ImageIcon ICON_OPERATOR_ADD = new ImageIcon(
            AppMain.class.getResource("/icon/add.png"));

    public static final ImageIcon ICON_OPERATOR_EDIT = new ImageIcon(
            AppMain.class.getResource("/icon/edit.png"));

    public static final ImageIcon ICON_LOGIN_USER = new ImageIcon(
            AppMain.class.getResource("/icon/user.png"));

    public static final ImageIcon ICON_LOGIN_PASSWORD = new ImageIcon(
            AppMain.class.getResource("/icon/password.png"));

    public static final ImageIcon ICON_LOGIN_LOGIN = new ImageIcon(
            AppMain.class.getResource("/icon/login.png"));

    public static final ImageIcon ICON_RESULT = new ImageIcon(
            AppMain.class.getResource("/icon/panel/result.png"));
    /**
     * Size
     */
    public static final int TOOL_BAR_WIDTH = 140;

    public static final Dimension LABEL_SIZE = new Dimension(78, 30);

    public static final int SCREEN_WIDTH = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int SCREEN_HEIGHT = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final Rectangle APP_BOUNDS = new Rectangle(
            (SCREEN_WIDTH - 1150) / 2, (SCREEN_HEIGHT - 820) / 2,
            1150, 820);

    /**
     * Text
     */
    public static final String[] TABLE_HEADS = {"id", "username", "password", "privilege"};


}
