package Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Reader {
    public List<ObjNode> read_heap(String path) throws IOException {
        List<ObjNode> heap = new LinkedList<>();
        List<String> lines= Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            String[] col = line.split(",");
            Integer memory_start = Integer.valueOf(col[1]);
            Integer memory_end = Integer.valueOf(col[2]);
            heap.add(new ObjNode(col[0], memory_start,memory_end));
        });
        return heap;
    }

    public List<String> read_root(String path) throws IOException {
        return Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
    }

    public List<List<String>> read_pointers(String path) throws IOException {
        List<List<String>> pointers = new LinkedList<>();
        List<String> lines= Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            pointers.add(Arrays.asList(line.split(",")));
        });
        return pointers;
    }
}
