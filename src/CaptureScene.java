import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;

public class CaptureScene
{
    protected int x = 0;
    protected int y = 0;
    protected int width = 600;
    protected int height = 600;

    CaptureScene() { }

    public void Capture()
    {
        try
        {
            Robot robot = new Robot();
            Rectangle area = new Rectangle(x, y, width, height);
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            ImageIO.write(bufferedImage, "jpg", new File("./Images/CapturedScene.jpg"));
        }
        catch(AWTException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
