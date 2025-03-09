/*
메모리 : 25276
시간 : 340

문제
1. 트리 : 사이클이 없는 무방향 그래프
2. 트리에서 두 노드의 거리 중 가장 긴 거리를 지름이라고 하자

입력
1. 노드의 개수 N 10000이하
2. 간선의 정보 : 시간, 끝, 가중치

출력
트리의 지름

아이디어
다익스트라

풀이과정
1. 자식노드가 없는 노드 Queue 넣기
2. 다음노드에 값 큰 값으로 초기화
 */
import java.util.*;
import java.io.*;
public class BOJ_1967_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<List<int[]>> edges = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            edges.add(new ArrayList<>());
        }

        int[] countArr = new int[N + 1];
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.get(start).add(new int[]{end, weight});
            edges.get(end).add(new int[]{start, weight});
            countArr[start] ++;
            countArr[end] ++;
        }

        Node[] nodes = new Node[N + 1];
        Queue<Node> queue = new ArrayDeque<>();
        for(int i = 1; i < N + 1; i++){
            nodes[i] = new Node(i, 0);
            if(edges.get(i).size() == 1 && i != 1){
                queue.add(nodes[i]);
            }
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            countArr[node.idx] --;

            if(node.idx == 1) continue;
            List<int[]> list = edges.get(node.idx);
            for(int[] arr : list){
                int next = arr[0];
                int weight = arr[1] + nodes[node.idx].weight;

                if(countArr[next] == 0) continue;
                nodes[next].queue.add(weight);
                nodes[next].weight = Math.max(nodes[next].weight, weight);
                countArr[next] --;

                if(next == 1 && countArr[next] == 0){
                    queue.add(nodes[arr[0]]);
                }

                else if(next != 1 && countArr[next] == 1){
                    queue.add(nodes[arr[0]]);
                }

            }
        }

        int answer = 0;

        for(int i = 1; i <= N; i++){
            System.out.println(nodes[i]);
            if(nodes[i].queue.size() == 1){
                answer = Math.max(answer, nodes[i].queue.poll());
            }
            else if(!nodes[i].queue.isEmpty()){
                answer = Math.max(answer, nodes[i].queue.poll() + nodes[i].queue.poll());
            }
        }



        System.out.println(answer);
    }

    static class Node{
        int idx;
        PriorityQueue<Integer> queue;
        int weight;

        public Node(int idx, int weight){
            this.idx = idx;
            queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            this.weight = weight;
        }

        public String toString(){
            return "idx : " + idx + " queue : " + queue;
        }
    }
}
