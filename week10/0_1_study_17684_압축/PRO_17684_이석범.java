package algorithm;
import java.io.*;
import java.util.*;

/*
map, �� �����͸� Ȱ��
*/
class PRO_17684_�̼��� {
    
    Map<String, Integer> map = new HashMap<>();
    
    //�ʱ� �빮�ڸ� �ִ� ���
    private void init() {
        for(char c = 'A'; c<='Z';c++) {
            map.put(String.valueOf(c), map.size()+1);
        }
    }
    
    public int[] solution(String msg) {
        
        init();
        
        // System.out.println(map.get("Z"));
        
        //�������� �̿�
        int start = 0;
        int end = 0;
        
        List<Integer> list = new ArrayList<>();
        //�ӽ� ���ڿ�
        String sub = "";
        while(end < msg.length()) {
            
            String tmp = msg.substring(start, end+1);
            //���� �ʿ� ���ԵǾ������� end�� +1�ϰ� ���� ���ڿ��� �ӽ÷� ����
            if(map.containsKey(tmp)) {
                sub = tmp;
                end++;
            }
            //������ �� ������ �־�ߵǴ� ���
            else {
                //������ �ְ�
                map.put(tmp, map.size()+1);
                //end�� �������� �ٽ� �ϱ�
                start = end;
                //����Ʈ�� ���� ������ ����� ���ڿ��� ����Ʈ�� �߰�
                list.add(map.get(sub));
            }
            
        }
        //������ ���� �� �ֱ�
        list.add(map.get(sub));
        
        //����Ʈ �迭�� ��ȯ
        int[] answer = list.stream().mapToInt(Integer::valueOf).toArray();
        // int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}