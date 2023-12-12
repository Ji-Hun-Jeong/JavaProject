public class AudioMgr
{
    private static AudioMgr m_inst;
    private AudioMgr(){}
    public static AudioMgr GetInst(){return m_inst;}
}