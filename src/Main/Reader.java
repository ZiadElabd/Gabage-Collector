package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            line = fixLine(line);
            line.replaceFirst("ï»¿", "");
            String[] col = line.split(",");
            for(String s : col){
                s.replaceAll("[^0-9]+", "");
            }
            Integer memory_start = Integer.valueOf(col[1]);
            Integer memory_end = Integer.valueOf(col[2]);
            heap.add(new ObjNode(col[0], memory_start,memory_end));
        });
        return heap;
    }

    public void write_heap(List<ObjNode> heap){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("new-heap.csv"))){
            for (ObjNode obj : heap) {
                bw.write(obj.obj_id + "," + obj.memory_start + "," + obj.memory_end);
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<String> read_root(String path) throws IOException {
        List<String> roots =  Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        roots.forEach( r -> {
            r = fixLine(r);
            r.replaceFirst("ï»¿", "");
            r.replaceAll("[^0-9]+", "");
        });
        return roots;
    }

    public List<List<String>> read_pointers(String path) throws IOException {
        List<List<String>> pointers = new LinkedList<>();
        List<String> lines= Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            line = fixLine(line);
            line.replaceFirst("ï»¿", "");
            String[] col = line.split(",");
            for(String s : col){
                s.replaceAll("[^0-9]+", "");
            }
            pointers.add(Arrays.asList(col));
        });
        return pointers;
    }

    public String fixLine(String line){
        List<Character> ch = new LinkedList<>();
        for(char c : line.toCharArray()){
            if(c > 31 && c < 58)
                ch.add(c);
        }
        char[] l = new char[ch.size()];
        int i=0;
        for (Character c : ch) {
            l[i++] = c;
        }
        return new String(l);
    }
}
