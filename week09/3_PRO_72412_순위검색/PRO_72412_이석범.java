package algorithm;
/*
map�� ����ϸ� key�� ��ĥ �����ִµ� value�� ���� List�� ����� �ذ�
*/
import java.io.*;
import java.util.*;
class PRO_72412_�̼��� {
    
    Map<String, String> convertMap = new HashMap<>();
    
    Map<String, ArrayList<Integer>> map = new HashMap<>();
    
    //���ڿ��� ���� ó���ϱ� ���� ���� ���ڿ��� �ٲ�
    private void init() {
        convertMap.put("cpp", "0");
        convertMap.put("java", "1");
        convertMap.put("python", "2");
        convertMap.put("backend", "0");
        convertMap.put("frontend", "1");
        convertMap.put("junior", "0");
        convertMap.put("senior", "1");
        convertMap.put("chicken", "0");
        convertMap.put("pizza", "1");        
    }
    //�� ���Ǻ��� ������ ���� ��Ÿ��
    //-�� ��� ����ϱ� ����
    private int[] rotate = {2, 1, 1, 1};
    private int[] result;
    //-�� ��� ��� ��츦 �����ϹǷ� sum�� ���� ����
    private int sum;
    
    private void back(int idx, String q, String answer, int score) {
        
        //Ű�� �ϼ��� ��� �� Ű�� �����ϴ� �������� ��
        if(idx==4) {
            // System.out.println(answer);
            int cnt = 0;
            //����Ž���� �ؾ��� ȿ���� ������
            binarySearch(answer, score);
            return;
        }
        
        //-�� ��� ��� ����� ���� ��Ʈ��ŷ
        if(q.charAt(idx) == '9') {
            for(int i=0; i<=rotate[idx];i++) {
                back(idx+1, q, answer+String.valueOf(i), score);
            }
        }
        
        else {
            back(idx+1, q, answer+q.charAt(idx), score);
        }
    }
    
    private void binarySearch(String answer, int score) {
        //�������� ������ ��ȯ
        if(!map.containsKey(answer)) return;
        //���ǿ� �´� �͵��� ��� ��ȸ
        ArrayList<Integer> list = map.get(answer);
        
        int start = 0;
        int end = list.size();
        
        //���� Ž������ ���ǿ� �´� ���� ã��
        while(start < end) {
            int mid = (start+end) / 2;
            if(list.get(mid) >= score) {
                end = mid;
            }
            else start = mid+1;
        }
        
        sum += list.size() - start;
        // System.out.println(sum);
        
        return;
    }
    
    public int[] solution(String[] info, String[] query) {
        
        map = new HashMap<>();
        
        init();
         //Ű ����� ����
        for(int i=0; i<info.length;i++) {
            String[] tmp = info[i].split(" ");
            
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<4;j++) {
                sb.append(convertMap.get(tmp[j]));
            }
            
            String key = sb.toString();
            
            if(map.containsKey(key)) {
                map.get(key).add(Integer.parseInt(tmp[4]));
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                
                list.add(Integer.parseInt(tmp[4]));
                
                map.put(key, list);
            }
        }
        
        //�� ����Ʈ ����
        Set<String> key = map.keySet();
        for(String k:key) {
            Collections.sort(map.get(k));
        }

        int[] answer = new int[query.length];
        
        for(int i=0; i<query.length;i++) {
            sum = 0;
            String[] tmp = query[i].split(" ");
            
            StringBuilder sb = new StringBuilder();

            
            for(int j=0; j<=6;j += 2) {
                if(tmp[j].equals("-")) sb.append("9");
                else sb.append(convertMap.get(tmp[j]));
            }
            // System.out.println(sb.toString());
            
            String q = sb.toString();
            
            back(0, q, "", Integer.parseInt(tmp[7]));
            
            answer[i] = sum;
        }
        
        return answer;
    }
}