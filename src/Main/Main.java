package Main;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
        System.out.println("content of heap file ");
        List<ObjNode> heap = reader.read_heap("heap.csv");
        heap.forEach(h -> {
            System.out.println(h.obj_id +"  "+h.memory_start+"  "+ h.memory_end);
        });
        System.out.println("\n\n");

        System.out.println("content of roots file ");
        List<String> root = reader.read_root("roots.txt");
        root.forEach(System.out::println);
        System.out.println("\n\n");
        */
     /*   System.out.println("content of pointers file ");
        List<List<String>> pointers = reader.read_pointers("pointers.csv");
        pointers.forEach(p -> {
            System.out.println(p.get(0)+"  "+p.get(1));
        });*/
        Utils tools=new Utils();
        tools.init();
        List<ObjNode> roots=tools.getRoots();
        System.out.println(roots.size());
        for (ObjNode node:roots) {
            System.out.println(node.memory_start+" "+node.memory_end);
        }
        List<ObjNode> heap=tools.getHeap();
        System.out.println(heap.size());
        for (ObjNode node:heap) {
            System.out.println(node.memory_start+" "+node.memory_end);
        }
        copyGC copy=new copyGC();
         List<ObjNode> to_heap= copy.coping();
        System.out.println(to_heap.size());
        for (ObjNode node:to_heap) {
            System.out.println(node.memory_start+" "+node.memory_end);
        }



    }
}
