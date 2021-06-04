package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SwepAndCompactGC {

    private Utils tools=new Utils();
    private List<ObjNode> heap=new LinkedList<>();
    private List<ObjNode> roots=new LinkedList<>();
    private List<ObjNode> newHeap = new LinkedList<>();
    private int index = 0 ;
    Reader reader = new Reader();

    public SwepAndCompactGC(String heap_path,String pointers_path,String roots_path) throws IOException {
        tools.init(heap_path,pointers_path,roots_path);
        this.heap=tools.getHeap();
        this.roots=tools.getRoots();
    }

    private void dfs(ObjNode node) {
        node.status = true ;
        List<ObjNode> allNeighbors = node.childs;
        if (allNeighbors == null)
            return;
        for (ObjNode neighbor : allNeighbors) {
            if (!neighbor.status)
                dfs(neighbor);
        }
    }

    public void markAndCompact(String newHeapFile)
    {
        for (ObjNode root : roots)
            dfs(root);
        for (ObjNode node : heap)
        {
            if(node.status)
            {
                int size = node.memory_end - node.memory_start;
                node.memory_start = index ;
                node.memory_end = index + size ;
                index = index + size + 1 ;
                newHeap.add(node);
            }
        }
        new Reader().write_heap(newHeap,newHeapFile);
    }


    public static void main(String[] args){
        try {
            if(args.length == 0){
                SwepAndCompactGC gc = new SwepAndCompactGC("heap.csv","pointers.csv","roots.txt");
                gc.markAndCompact("default.csv");
            }else if (args.length == 4){
                SwepAndCompactGC gc = new SwepAndCompactGC(args[0],args[1],args[2]);
                gc.markAndCompact(args[3]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }





}