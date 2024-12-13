import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 입력 알파벳의 크기만큼의 노드 배열을 만듦
 * 자식이 주어졌을때 노드배열의 주소를 자식으로 추가
 * 11588KB 68ms
 */
public class BOJ_1991_이석범 {
	
	static int N;
	
	static class Node {
		char cur;
		Node left;
		Node right;
		
		Node(char cur) {
			this.cur = cur;
		}
		
		public void update(Node left, Node right) {
			this.left = left;
			this.right = right;
		}

		//전위는 자기자신이 먼저 출력
		public void preOrder() {
			System.out.print(this.cur);
			if(left !=null) {
				left.preOrder();
			}
			if(right != null) {
				right.preOrder();
			}
		}

		//중위는 자기자신이 중간
		public void inOrder() {
			if(left !=null) {
				left.inOrder();
			}
			System.out.print(this.cur);
			if(right != null) {
				right.inOrder();
			}
		}

		//후위는 마지막
		public void postOrder() {
			if(left !=null) {
				left.postOrder();
			}
			if(right != null) {
				right.postOrder();
			}
			System.out.print(this.cur);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		//트리 부분 
		Node[] nodeList = new Node[N];
		for(int i=0; i<N;i++) {
			nodeList[i] = new Node((char)('A'+i));
		}
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			char cur = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			Node curNode = nodeList[cur-'A'];
			Node leftNode = null;
			Node rightNode = null;
			//자식이 존재하는 경우만 추가
			if(left!='.') {
				leftNode = nodeList[left-'A'];
			}
			
			if(right!='.') {
				rightNode = nodeList[right-'A'];
			}

			curNode.update(leftNode, rightNode);
			
		}
		
		//전위
		nodeList[0].preOrder();
		System.out.println();
		
		//중위
		nodeList[0].inOrder();
		System.out.println();
		
		//후위 
		nodeList[0].postOrder();
		System.out.println();
		
	}
}
