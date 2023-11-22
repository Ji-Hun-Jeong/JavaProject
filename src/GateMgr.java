import java.util.*;
public class GateMgr
{
   private Scanner m_scanner=new Scanner(System.in);
   private static GateMgr m_Inst=new GateMgr();
   private GateMgr(){}
   public static GateMgr GetInst(){return m_Inst;}

   private int m_iNumOfInput=0;
   private Vector<INPUTGate> m_vecInputGates=new Vector<INPUTGate>();
   private Vector<OUTPUTGate> m_vecOutputGates=new Vector<OUTPUTGate>();
   private Vector<Gate> m_vecGates=new Vector<Gate>();

   public Vector<Gate> GetGateVector(){return m_vecGates;}
   public void Init()
   {
      
   }
   public void Update()
   {
      System.out.print("Input값 3개 입력 : ");
      int num[]=new int[3];
      num[0]=m_scanner.nextInt();
      num[1]=m_scanner.nextInt();
      num[2]=m_scanner.nextInt();

      Queue<Integer> q = new LinkedList<Integer>();
      for(int i=0;i<m_vecInputGates.size();++i)
      {
         Gate startGate = m_vecInputGates.get(i).GetStartGate();
         startGate.AddInput(num[i]);
         if(startGate.Ready())
            q.add(startGate.GetNum());
      }

      while(!q.isEmpty())
      {
         int idx = q.poll();
         Gate gate = m_vecGates.get(idx);
         if(!gate.Ready())
         {
            q.add(idx);
            continue;
         }         
         int result = gate.Calculate();
         OUTPUTGate outputGate=m_vecGates.get(idx).GetOutputGate();
         if(outputGate!=null)
         {
            outputGate.SetResult(result);
         }
         else
         {
            for(int i=0;i<gate.GetIdxVec().size();++i)
            {
               Gate adjGate = m_vecGates.get(gate.GetIdxVec().get(i));
               adjGate.AddInput(result);
               q.add(adjGate.GetNum());
            }
         }  
      }
      for(OUTPUTGate outputGate:m_vecOutputGates)
      {
         System.out.println(outputGate.GetResult());
      }
      for(int i=0;i<m_vecGates.size();++i)
      {
         m_vecGates.get(i).Clear();
      }
   }
}