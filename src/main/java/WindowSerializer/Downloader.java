package WindowSerializer;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Downloader
{
    private JInternalFrame owner;
    private final String ownerName;

    public Downloader(JInternalFrame owner, String name)
    {
        this.owner = owner;
        ownerName = name;
    }

    public void download(){
        Saver windowParameters = null;
        FileInputStream inputStream = null;
        String filename = System.getProperty("user.dir")+ownerName+".txt";
        if (new File(filename).exists()) {
            try {
                inputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                windowParameters = (Saver) objectInputStream.readObject();

                owner.setLocation(windowParameters.x, windowParameters.y);
                owner.setSize(windowParameters.width, windowParameters.height);
                owner.setIcon(windowParameters.isDeployed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
        {
            owner.setSize(500, 500);
            owner.setLocation(10, 10);
        }
    }
}