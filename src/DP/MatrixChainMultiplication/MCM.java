package DP.MatrixChainMultiplication;

import java.util.ArrayList;
import java.util.List;

public class MCM {

    public int solve(String s, int i, int j, int[][] t) {
        if(i >= j) return 0;
        if(t[i][j] != -1) return t[i][j];
        if(isPalindrome(s, i, j)) return 0;

        int min = Integer.MAX_VALUE;

        for(int k = i; k <= j - 1; k++) {
            int temp = solve(s, i, k, t) + solve(s, k + 1, j, t) + 1;
            if(temp < min) {
                min = temp;
            }
        }
        t[i][j] = min;
        return min;
    }

    public static boolean isPalindrome(String s, int start, int end) {
        // returns true if string is a palindrome
        // else returns false
        int l = start;
        int r = end;

        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static List<String> findPalindromes(String s, int numPartsLeft, int start, int end) {
        List<String> palindromes = new ArrayList<>();
        if(numPartsLeft == 0) {
            List<String> sublist = new ArrayList<>();
            if(isPalindrome(s, start, end)) {
                palindromes.add(s);
            } else {
                return sublist;
            }
        }
        if(start == s.length() && numPartsLeft > 0) return new ArrayList<>();
        for(int i = start; i <= end; i++) {
            if(isPalindrome(s, start, i)) {
                palindromes.add(s.substring(start, i + 1));
                palindromes.addAll(findPalindromes(s, i + 1, numPartsLeft - 1, end));
            }
        }
        return palindromes;
    }
}
