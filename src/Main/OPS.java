package Main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OPS {
	private Reader files = new Reader();
	private List<ObjNode> heap;
	private List<List<String>> edges;
	private List<String> roots;
	private List<ObjNode> rootsNodes = new LinkedList<>();

	public OPS(String heap_path, String pointers_path, String roots_path) throws IOException{
		heap = files.read_heap(heap_path);
		edges = files.read_pointers(pointers_path);
		this.link();
		roots = files.read_root(roots_path);
		this.setRoots();
	}

	private void link() {
		edges.forEach(e -> {
			heap.forEach(p -> {
				if (e.get(0).equals(p.obj_id)) {
					for (ObjNode ch : heap) {
						if (e.get(1).equals(ch.obj_id)) {
							p.childs.add(ch);
							break;
						}
					}
				}
			});
		});
	}
	
	private void setRoots() {
		roots.forEach(s -> {
			heap.forEach( h -> {
				if (s.equals(h.obj_id))
					rootsNodes.add(h);
			});
		});
	}

	public List<ObjNode> getHeap() throws IOException{
		return this.heap ; 
	}
	
	public List<ObjNode> getRoots() throws IOException{
		return this.rootsNodes ; 
	}
}
