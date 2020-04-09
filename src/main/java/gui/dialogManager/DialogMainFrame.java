package gui.dialogManager;

import gui.MainApplicationFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;

public class DialogMainFrame extends WindowAdapter
{
    private MainApplicationFrame frame;

    public DialogMainFrame(MainApplicationFrame frame) { this.frame = frame; }

    public void pushClose(AWTEvent event) { close(); }

    private void close()
    {
        DialogManager dialog = new DialogManager(frame);
        dialog.setVisible(true);
        if (dialog.dialogIsClosed())
            System.exit(0);
    }
}
