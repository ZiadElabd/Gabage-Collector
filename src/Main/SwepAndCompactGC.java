package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SwepAndCompactGC {

    private Utils tools=new Utils();
    private List<ObjNode> heap=new LinkedList<>();
    private List<ObjNode> roots=new LinkedList<>();
    private int index = 0 ;

    Reader reader = new Reader();

    public SwepAndCompactGC() throws IOException {
        tools.init();
        this.heap=tools.getHeap();
        this.roots=tools.getRoots();
    }

    private void dfs(ObjNode node) {
        node.status = true ;
        //System.out.print(node.obj_id + " ");

        List<ObjNode> allNeighbors = node.childs;
        if (allNeighbors == null)
            return;

        for (ObjNode neighbor : allNeighbors) {
            if (!neighbor.status)
                dfs(neighbor);
        }
    }

    public void markAndCompact()
    {
        for (ObjNode root : roots)
            dfs(root) ;

        for (ObjNode node : heap)
        {
            if(node.status)
            {
                int size = node.memory_end - node.memory_start;
                node.memory_start = index ;
                node.memory_end = index + size ;
                index = index + size + 1 ;
                System.out.println(node.obj_id + " " + node.memory_start + " " + node.memory_end);
            }
        }
    }



}