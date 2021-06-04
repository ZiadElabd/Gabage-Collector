package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Utils {
	private Reader cin = new Reader();
	private List<ObjNode> heap  = new LinkedList<>();
	private List<String> roots = new LinkedList<>();
	private List<ObjNode> rootsNodes = new LinkedList<>();
	private List<List<String>> edges = new LinkedList<>();
	
	private void readHeap(String path) throws IOException {
		heap = cin.read_heap(path);
	}
	
	private void readEdges(String path) throws IOException{
		edges = cin.read_pointers(path);
	}
	
	private void readRoots(String path) throws IOException{
		 roots = cin.read_root(path);
	}
	
	private void link() {
		for (List<String> e : edges) {
			String u = e.get(0) ; 
			String v = e.get(1) ;
			for (ObjNode o : heap) {
				if (u.equals(o.obj_id)) {
					for (ObjNode oo :heap) {
						if (v.equals(oo.obj_id)) {
							o.childs.add(oo);
							break ;
						}
					}
				}
			}
		}
	}
	
	private void setRoots() {
		for (String s : roots) {
			for (ObjNode o : heap) {
				if (s.equals(o.obj_id)) {
					rootsNodes.add(o) ; 
				}
			}
		}
	}
	
	public void init(String heap_path,String pointers_path,String roots_path) throws IOException {
		this.readHeap(heap_path);
		this.readEdges(pointers_path);
		this.link();
		this.readRoots(roots_path);
		this.setRoots();
	}
	List<ObjNode> getHeap() throws IOException{
		return this.heap ; 
	}
	
	List<ObjNode> getRoots() throws IOException{
		return this.rootsNodes ; 
	}
}
