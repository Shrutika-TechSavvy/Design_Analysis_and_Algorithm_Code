
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringGeneration{

    public static String findMatchingString(String pattern, String constraints){
        int n = pattern.length(), m = constraints.length();
        StringBuilder result = new StringBuilder("?".repeat(n+m-1));

        for(int i = 0; i < m; i++){
            if(constraints.charAt(i) == 'T'){
                for(int j = 0; j < n ; j++){
                    char currentChar = result.charAt(i+j);
                    if(currentChar!='?' && currentChar!=pattern.charAt(j)){
                        return "-1";
                    }
                    result.setCharAt(i+j, pattern.charAt(j));

                }
            }
        }

        List<Integer> kmpTable = computeKMPTable(pattern);
        int state = 0;
        for(int i=0; i < result.length(); i++){
            char c  = result.charAt(i);
            if(c == '?'){
                for(char tryChar = 'A'; tryChar<='B'; tryChar++){
                    int newState = state;
                    while(newState > 0 && tryChar!=pattern.charAt(newState)){
                        newState = kmpTable.get(newState - 1);
                    }
                    if(tryChar == pattern.charAt(newState)) newState++;
                    if(i < n -1 || constraints.charAt(i-n+1) != 'F' || newState!=n){
                        result.setCharAt(i, tryChar);
                        state = newState;
                        break;
                    }
                }
            }
            else{
                while(state > 0 && c!=pattern.charAt(state)){
                    state = kmpTable.get(state - 1);
                }
                if(c == pattern.charAt(state)) state ++;
            }
            if(state == n){
                if(i >= n-1 && constraints.charAt(i-n+1) == 'F') return "-1";
                state= kmpTable.get(state - 1);
            }
        }

        
        return result.toString();

    }
    private static List<Integer> computeKMPTable(String pattern) {
        int m = pattern.length();
        List<Integer> kmpTable = new ArrayList<>(Collections.nCopies(m, 0));

        for(int i = 1, j=0; i < m ; i++ ){
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = kmpTable.get(j - 1);
            }if(pattern.charAt(i) == pattern.charAt(j)) j++;
            kmpTable.set(i, j);
        }
        return kmpTable;
        
    }
    public static void main(String[] args) {
        String pattern = "A";
        String constraints="TTT";
        System.out.println(findMatchingString(pattern, constraints));

    }
}