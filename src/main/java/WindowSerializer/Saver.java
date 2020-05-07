package WindowSerializer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saver implements Serializable {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean isDeployed;

    public Saver(int x, int y, int width, int height, boolean isDeployed)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isDeployed = isDeployed;
    }

    public void save(String name)
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+ name+".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}