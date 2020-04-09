package gui.dialogManager;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class DialogFrame extends InternalFrameAdapter
{
    private JFrame frame;
    private JInternalFrame internalFrame;

    public DialogFrame(JFrame frame, JInternalFrame internalFrame)
    {
        this.frame = frame;
        this.internalFrame = internalFrame;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent internalFrameEvent) {
        DialogManager dialog = new DialogManager(frame);
        dialog.setVisible(true);
        JInternalFrame frame = internalFrameEvent.getInternalFrame();

        if (dialog.dialogIsClosed())
            frame.dispose();
    }
}
