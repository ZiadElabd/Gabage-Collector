package Main;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException{

        /*Utils tools=new Utils();
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










        /*Utils u = new Utils();
        u.init();
        u.getHeap().forEach( e -> {
            System.out.println(e.obj_id +"  "+e.memory_start+"  "+ e.memory_end);
            System.out.println("childs *****");
            e.childs.forEach(Main::print);
            System.out.println("*******************");
        });
        */

        /*SwepAndCompactGC cl = new SwepAndCompactGC();
        cl.markAndCompact();
        */
    }
    public static void print(ObjNode e){
        System.out.println(e.obj_id +"  "+e.memory_start+"  "+ e.memory_end);
    }
}
