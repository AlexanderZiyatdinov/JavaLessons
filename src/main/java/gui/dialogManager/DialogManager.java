package gui.dialogManager;

import javax.swing.*;
import java.awt.*;

public class DialogManager extends JDialog
{
    private boolean closed = false;

    public DialogManager(JFrame frame)
    {
        super(frame,"Подтверждение выхода", true);

        JButton agreeButton = new JButton("Да");
        agreeButton.addActionListener(event ->
        {
            setVisible(false);
            closed = true;
        });

        JButton disagreeButton = new JButton("Отмена");
        disagreeButton.addActionListener(actionEvent -> setVisible(false));

        JPanel panel = new JPanel();
        panel.add(agreeButton);
        panel.add(disagreeButton);
        
        add(panel, BorderLayout.SOUTH);
        JLabel label = new JLabel("Вы уверены,что хотите закрыть окно?", SwingConstants.CENTER);
        add(label);
        setSize(500, 250);
        setLocation(400, 400);
        setResizable(true);
    }

    public boolean dialogIsClosed() { return closed; }
}
