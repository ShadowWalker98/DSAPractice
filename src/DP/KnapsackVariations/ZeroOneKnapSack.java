package DP.KnapsackVariations;

public class ZeroOneKnapSack {

    public static boolean subsetSum(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j <= target; j++) {
                if(j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][target];
    }
}
