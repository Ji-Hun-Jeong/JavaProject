import java.awt.Graphics;
import java.util.*;
class Gate
{
   // 계산이 한번 끝나면 모든 input들 -1로 만들기
   static int num=0;
   protected int m_iNum = num++;
   protected String m_strType = null;

   protected int m_arrInput[]=new int[2];
   protected int m_iIdx=0;

   protected Vec2 m_vPos = new Vec2();

   protected Vector<Integer> m_vecVertices=new Vector<Integer>();
   private OUTPUTGate m_outputGate=null;

   protected int Calculate(){return 0;}
   public void SetPos(int x,int y){m_vPos.x=x;m_vPos.y=y;}
   public void Render(Graphics g)
   {
        g.drawRect(m_vPos.x-20, m_vPos.y-20, 40, 40);
   }
   public Gate(String inType){m_strType=inType;}
   public boolean Ready(){return 2 == m_iIdx;}
   public int GetNum(){return m_iNum;}
   public void ShowInput()
   {
      System.out.println(m_arrInput[0]+" "+m_arrInput[1]);
   }
   public void Link(int vertexNum){m_vecVertices.add(vertexNum);}
   public OUTPUTGate GetOutputGate(){return m_outputGate;}
   public void LinkOutput(OUTPUTGate outputGate){m_outputGate=outputGate;}
   public void AddInput(int input){if(m_iIdx>=2)return; m_arrInput[m_iIdx++]=input;}
   public void Clear(){m_iIdx=0;}
   public Vector<Integer> GetIdxVec(){return m_vecVertices;}
}
class ANDGate extends Gate
{
    public ANDGate(){super("AND");} 
    public int Calculate()
    {
        return m_arrInput[0]&m_arrInput[1];
    }
}
class ORGate extends Gate
{
    public ORGate(){super("OR");} 
    public int Calculate()
    {
        return m_arrInput[0]|m_arrInput[1];
    }
}
class INPUTGate
{
    private int m_iNum=0;
    private Gate m_startGate=null;

    public void SetNum(int num){m_iNum=num;}
    public void SetStartGate(Gate startGate){m_startGate=startGate;}
    public Gate GetStartGate(){return m_startGate;}
}
class OUTPUTGate
{
    private int m_iResult=0;

    public void SetResult(int result){m_iResult=result;}
    public int GetResult(){return m_iResult;}
    public void ShowResult()
    {
        System.out.println(m_iResult);
    }
}
class Line
{
    public int leftX;
    public int leftY;
    public int rightX;
    public int rightY;
    public void Render(Graphics g)
    {
        g.drawLine(leftX,leftY,rightX,rightY);
    }   
}