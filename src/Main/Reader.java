package Main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    /*public List<ObjNode> read_heap(String path) throws IOException {
        List<ObjNode> heap = new LinkedList<>();
        List<String> lines= Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            String[] col = line.split(",");
            System.out.println(col[0] +"  "+col[1]+"  "+ col[2]);
            System.out.println(Integer.valueOf(col[0]));
            Integer id = Integer.valueOf(col[0]);
            Integer memory_start = Integer.valueOf(col[1]);
            Integer memory_end = Integer.valueOf(col[2]);
            heap.add(new ObjNode(id,memory_start,memory_end));
        });
        return heap;
    }*/
    public List<ObjNode> read_heap(String path) throws IOException {
        List<ObjNode> heap = new LinkedList<>();
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNextLine()){
            Integer id = scan.nextInt();
            Integer memory_start = scan.nextInt();
            Integer memory_end = scan.nextInt();
            heap.add(new ObjNode(id,memory_start,memory_end));
        }
        return heap;
    }
    public List<List<Integer>> read_pointers(String path) throws IOException {
        List<List<Integer>> pointers = new LinkedList<>();
        List<String> lines= Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            String[] col = line.split(",");
            List<Integer> edge = new LinkedList<>();
            edge.add(Integer.valueOf(col[0]));
            edge.add(Integer.valueOf(col[1]));
            pointers.add(edge);
        });
        return pointers;
    }
}
