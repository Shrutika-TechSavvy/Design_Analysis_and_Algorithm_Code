import java.util.*;

class Main {

    // Build KMP prefix table (LPS array)
    private static int[] computeKMPTable(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            //MISMATCH WALI CONDITION
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }
            //MATCH WALI CONDITION
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            
            lps[i] = j;
        }
        return lps;
    }

    // Run KMP search: find all start indices where pattern occurs in text
    private static List<Integer> kmpSearch(String text, String pattern) {
        int[] lps = computeKMPTable(pattern);
        List<Integer> matches = new ArrayList<>();

        int j = 0; // length of current match
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                matches.add(i - j + 1); // store start index
                j = lps[j - 1]; // continue search
            }
        }
        return matches;
    }

    public static String findMatchingString(String pattern, String constraints) {
        int n = pattern.length();
        int m = constraints.length();
        StringBuilder result = new StringBuilder("?".repeat(n + m - 1));

        // Step 1: Apply 'T' constraints
        for (int i = 0; i < m; i++) {
            if (constraints.charAt(i) == 'T') {
                for (int j = 0; j < n; j++) {
                    int idx = i + j;
                    char needed = pattern.charAt(j);
                    char current = result.charAt(idx);
                    if (current == '?' || current == needed) {
                        result.setCharAt(idx, needed);
                    } else {
                        return "-1";
                    }
                }
            }
        }

        // Step 2: Apply 'F' constraints
        for (int i = 0; i < m; i++) {
            if (constraints.charAt(i) == 'F') {
                boolean diffFound = false;
                for (int j = 0; j < n; j++) {
                    int idx = i + j;
                    char needed = pattern.charAt(j);
                    char current = result.charAt(idx);
                    if (current == '?') {
                        char replacement = (needed == 'A') ? 'B' : 'A';
                        result.setCharAt(idx, replacement);
                        diffFound = true;
                        break;
                    } else if (current != needed) {
                        diffFound = true;
                        break;
                    }
                }
                if (!diffFound) return "-1";
            }
        }

        // Step 3: Fill '?' with 'A'
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '?') result.setCharAt(i, 'A');
        }

        // Step 4: Verify using KMP
        List<Integer> matches = kmpSearch(result.toString(), pattern);
        Set<Integer> matchSet = new HashSet<>(matches);

        for (int i = 0; i < m; i++) {
            boolean hasMatch = matchSet.contains(i);
            if (constraints.charAt(i) == 'T' && !hasMatch) return "-1";
            if (constraints.charAt(i) == 'F' && hasMatch) return "-1";
        }

        return result.toString();
    }

    // Example
    public static void main(String[] args) {
        System.out.println(findMatchingString("ABCA", "TFFF")); // "ABAB"
        System.out.println(findMatchingString("AB", "FT"));  // might be valid
        System.out.println(findMatchingString("AB", "TF"));  // might be valid
        System.out.println(findMatchingString("ABA", "FTF")); // test conflict
    }
}
