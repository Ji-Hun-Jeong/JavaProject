import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class ImageMgr 
{
    private BufferedImage ImageToBufferedImage(Image image)
    {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
        return bufferedImage;
    }
    private static Image MakeColorTransparent(BufferedImage im, final Color color)
    {
        ImageFilter filter = new RGBImageFilter()
        {
            public int markerRGB = color.getRGB() | 0XFF000000;
            public final int filterRGB(int x,int y,int rgb)
            {
                if ((rgb | 0XFF000000) == markerRGB)
                    return 0X00FFFFFF & rgb;
                else
                    return rgb;
            }
        };
        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    public Image MakeTranparentImage(Image inImage)
    {
        int color = 0Xffffff;
        BufferedImage bufferedImage = ImageToBufferedImage(inImage);
        Image image2 = MakeColorTransparent(bufferedImage, new Color(color));
        return image2;
    }
    private static ImageMgr m_inst = new ImageMgr();
    private ImageMgr(){}
    public static ImageMgr GetInst(){return m_inst;}
}