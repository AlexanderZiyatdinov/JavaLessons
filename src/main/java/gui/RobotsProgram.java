package gui;
import java.awt.*;
import javax.swing.*;

public class RobotsProgram
{
    public static void main(String[] args) {
      try
      {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

      SwingUtilities.invokeLater(() ->
      {
        MainApplicationFrame frame = new MainApplicationFrame();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }}
