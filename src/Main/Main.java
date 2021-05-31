package Main;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        String s1 = "111111";
        System.out.println("csdvsc" + Integer.valueOf(s1));
        List<ObjNode> heap = reader.read_heap("heap.csv");
        heap.forEach(h -> {
            System.out.println(h.obj_id +"  "+h.memory_start+"  "+ h.memory_end);
        });
    }
}
