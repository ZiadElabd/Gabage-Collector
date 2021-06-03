package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class copyGC {
    private Utils tools=new Utils();
    private List<ObjNode> from_heap=new LinkedList<>();
    private List<ObjNode> to_heap=new LinkedList<>();
    private List<ObjNode> roots=new LinkedList<>();
    Reader reader = new Reader();
    public copyGC() throws IOException {
        tools.init();
        this.from_heap=tools.getHeap();
        this.roots=tools.getRoots();
    }
    public List<ObjNode> coping(){
        int index=copyRoots();
        for (int i=0;i<to_heap.size();i++) {
            index=bfs(to_heap.get(i),index);
        }
        reader.write_heap(to_heap);
        return to_heap;
    }
    private int copyRoots(){
        int index=roots.get(0).memory_end-roots.get(0).memory_start;
        roots.get(0).memory_start=0;
        roots.get(0).memory_end=index;
        roots.get(0).status=true;
        to_heap.add(roots.get(0));
        for (int i=1;i<roots.size();i++) {
            ObjNode root=roots.get(i);
           int size=root.memory_end- root.memory_start;
           root.memory_start=index+1;
           root.memory_end=index+size;
           root.status=true;
           index+=size+1;
           to_heap.add(root);
        }
     return index;
        
    }
    private int bfs(ObjNode parent,int index ){
        for (ObjNode node: parent.childs) {
            if(node.status!=true) {
                int size = node.memory_end - node.memory_start;
                node.memory_start = index;
                node.memory_end = index + size +1;
                node.status = true;
                index += size+1;
                to_heap.add(node);
            }
        }
       return index;
    }
}




