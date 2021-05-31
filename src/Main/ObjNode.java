package Main;

import java.util.LinkedList;
import java.util.List;

public class ObjNode {
    Integer obj_id;
    Integer memory_start;
    Integer memory_end;
    List<ObjNode> childs;

    public ObjNode(Integer obj_id, Integer memory_start, Integer memory_end) {
        this.obj_id = obj_id;
        this.memory_start = memory_start;
        this.memory_end = memory_end;
        this.childs = new LinkedList<>();
    }
}
