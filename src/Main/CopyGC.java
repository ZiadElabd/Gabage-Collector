package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CopyGC {
    private OPS tools;
    private List<ObjNode> from_heap;
    private List<ObjNode> to_heap=new LinkedList<>();
    private List<ObjNode> roots;
    Reader files = new Reader();

    public CopyGC(String heap_path, String pointers_path, String roots_path) throws IOException {
        tools = new OPS(heap_path,pointers_path,roots_path);
        this.from_heap = tools.getHeap();
        this.roots = tools.getRoots();
    }

    public List<ObjNode> coping(String newHeapFile){
        int index=copyRoots();
        for (int i=0;i<to_heap.size();i++) {
            index=bfs(to_heap.get(i),index);
        }
        files.write_heap(to_heap,newHeapFile);
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
           root.memory_end=index+size+1;
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
                node.memory_start = index+1;
                node.memory_end = index + size+1;
                node.status = true;
                index += size+1;
                to_heap.add(node);
            }
        }
       return index;
    }

    public static void main(String[] args){
        try {
            if(args.length == 0){
                CopyGC gc = new CopyGC("heap.csv","pointers.csv","roots.txt");
                gc.coping("default.csv");
            }else if (args.length == 4){
                CopyGC gc = new CopyGC(args[0],args[1],args[2]);
                gc.coping(args[3]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}




