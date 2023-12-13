import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class AudioMgr
{
    private TreeMap<String,AudioInputStream> m_mapAudios = new TreeMap<String,AudioInputStream>();
    private boolean m_bIsPlaying = false;
    public void Init()
    {
        
    }
    public void Play(String inRelativePath)
    {

        String finalPath = FileMgr.GetInst().GetAbsolutePath()+inRelativePath;     
        try
        {
            File audioFile = new File(finalPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(new LineListener() 
            {
                public void update(LineEvent e) 
                {
                    if (e.getType() == LineEvent.Type.STOP) 
                    {
                        m_bIsPlaying=false;
                    }
                }
            });
            clip.open(audioStream);
            clip.start();
            m_bIsPlaying=true;
        }
        catch(LineUnavailableException e){e.printStackTrace();}
        catch(UnsupportedAudioFileException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
    }
    private static AudioMgr m_inst = new AudioMgr();
    private AudioMgr(){}
    public static AudioMgr GetInst(){return m_inst;}
}