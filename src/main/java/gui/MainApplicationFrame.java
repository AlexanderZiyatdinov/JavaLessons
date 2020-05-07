package gui;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.InternalFrameListener;

import gui.dialogManager.DialogFrame;
import gui.dialogManager.DialogMainFrame;


import log.Logger;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public MainApplicationFrame() {
        int inset = 10;


        int sizeWidth = screenSize.width;
        int sizeHeight = screenSize.height;
        setBounds(inset, inset,
            sizeWidth  - inset*2,
            sizeHeight - inset*2);
        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameWindow.addInternalFrameListener(new DialogFrame(this, gameWindow));
        gameWindow.setSize(sizeWidth,  sizeHeight);
        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
    }

    private GameWindow addGameWidnow()
    {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(500,500);
        gameWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        gameWindow.addInternalFrameListener(new DialogFrame(this, gameWindow));
        return gameWindow;


    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300,800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        logWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        logWindow.addInternalFrameListener(new DialogFrame(this, logWindow));
        Logger.debug("Протокол работает");
        return logWindow;
    }
    
    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void setSizes(JInternalFrame frame, int x, int y, int width, int height)
    {
    frame.setLocation(x, y);
    frame.setSize(width,  height);
    frame.setResizable(true);
    }

    private JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu lookAndFeelMenu = addMenuItem("Режим отображения", "Управление режимом отображения приложения", KeyEvent.VK_V);

        addPopupMenuItem("Системная схема",
                (event) -> {setLookAndFeel( UIManager.getSystemLookAndFeelClassName());this.invalidate(); },
                lookAndFeelMenu, KeyEvent.VK_S);

        addPopupMenuItem("Универсальная схема",
                (event) -> {setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());this.invalidate(); },
                lookAndFeelMenu, KeyEvent.VK_S);

        JMenu testMenu = addMenuItem("Тесты","Тестовые команды", KeyEvent.VK_T);
        addPopupMenuItem("Сообщение в лог",
                (event) -> Logger.debug("Новая строка"), testMenu, KeyEvent.VK_S);

        JMenu programMenu = addMenuItem("Программа", "О программе", KeyEvent.VK_T);
        addPopupMenuItem("Выход",
                (event) ->
                {
                    DialogMainFrame dialog = new DialogMainFrame(this);
                    dialog.pushClose(event);
                }, programMenu, KeyEvent.VK_S );

        menuBar.add(programMenu);
        menuBar.add(lookAndFeelMenu);
        menuBar.add(testMenu);
        return menuBar;
    }

    private JMenu addMenuItem(String text, String description, int keyEvent)
    {
        JMenu menuItem = new JMenu(text);
        menuItem.setMnemonic(keyEvent);
        menuItem.getAccessibleContext().setAccessibleDescription(description);
        return menuItem;
    }

    private void addPopupMenuItem(String text, ActionListener event, JMenu menuBar, int keyEvent)
    {
        JMenuItem menuItem = new JMenuItem(text, keyEvent);
        menuItem.addActionListener(event);
        menuBar.add(menuItem);
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e)
        {

        }
    }
}
