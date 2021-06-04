package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MarkAndCompactGC {

    private OPS tools;
    private List<ObjNode> heap;
    private List<ObjNode> roots;
    private List<ObjNode> newHeap = new LinkedList<>();
    private int index = 0 ;
    Reader files = new Reader();

    public MarkAndCompactGC(String heap_path, String pointers_path, String roots_path) throws IOException {
        tools = new OPS(heap_path,pointers_path,roots_path);
        this.heap=tools.getHeap();
        this.roots=tools.getRoots();
    }

    private void mark(ObjNode node){
        node.status = true ;
        List<ObjNode> childs = node.childs;
        childs.forEach( ch -> {
            if (!ch.status)
                mark(ch);
        });
    }

    public void markAndCompact(String new_heap_file){
        roots.forEach(this::mark);

        heap.forEach( obj -> {
            if(obj.status){
                int size = obj.memory_end - obj.memory_start;
                obj.memory_start = index ;
                obj.memory_end = index + size ;
                index = index + size + 1 ;
                newHeap.add(obj);
            }
        });
        files.write_heap(newHeap, new_heap_file);
    }

    public static void main(String[] args){
        try {
            if(args.length == 0){
                MarkAndCompactGC gc = new MarkAndCompactGC("heap.csv","pointers.csv","roots.txt");
                gc.markAndCompact("default.csv");
            }else if (args.length == 4){
                MarkAndCompactGC gc = new MarkAndCompactGC(args[0],args[1],args[2]);
                gc.markAndCompact(args[3]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }





}