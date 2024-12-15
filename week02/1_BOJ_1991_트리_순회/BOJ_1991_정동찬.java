package Q1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Q1991 {
	public static class Tree {
		Map<Object, Node> nodeMap = new HashMap<>();
		Node root;
		
		public class Node {
			Object data;
			Node left;
			Node right;
			
			public Node(Object data) {
				this.data = data;
				left = null;
				right = null;
			}
		}
		
		public Node addNode(Object data) {
			if (data.equals(".")) return null;
			
			// nodeMap에 data가 포함되어 있지 않다면 생성
			if (!nodeMap.containsKey(data)) {
				nodeMap.put(data, new Node(data));
			}
			return nodeMap.get(data);
		}
		
		public void setRoot(Object data) {
			root = addNode(data);
		}
		
		public Node getRoot() {
			return root;
		}
		
		// 전위순회 루트-좌-우
		public String preOrder(Node node) {
			if (node == null) return "";
			
			StringBuilder r = new StringBuilder();
			r.append(node.data.toString());
			r.append(preOrder(node.left));
			r.append(preOrder(node.right));
			
			return r.toString();
		}
		
		// 중위순회 좌-루트-우
		public String inOrder(Node node) {
			if (node == null) return "";
			
			StringBuilder r = new StringBuilder();
			r.append(inOrder(node.left));
			r.append(node.data.toString());
			r.append(inOrder(node.right));
			
			return r.toString();
		}
		
		// 후위순회 좌-우-루트
		public String postOrder(Node node) {
			if (node == null) return "";
			
			StringBuilder r = new StringBuilder();
			r.append(postOrder(node.left));
			r.append(postOrder(node.right));
			r.append(node.data.toString());
			
			return r.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		
		for (int idx = 0; idx < N; idx++) {
			String[] line = br.readLine().split(" ");
			
			Object parent = line[0];
			Object left = line[1];
			Object right = line[2];
			
			Tree.Node parentNode = tree.addNode(parent);
			if (idx == 0) tree.setRoot(parent);
			
			parentNode.left = tree.addNode(left);
			parentNode.right = tree.addNode(right);
		}
		
		Tree.Node root = tree.getRoot();
		System.out.println(tree.preOrder(root));
		System.out.println(tree.inOrder(root));
		System.out.println(tree.postOrder(root));
	}
}
