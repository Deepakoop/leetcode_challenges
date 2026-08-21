import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long minCoin = Arrays.stream(coins).min().getAsInt();
        long right = (long) k * minCoin;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countValidAmounts(coins, mid) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private long countValidAmounts(int[] coins, long mid) {
        int n = coins.length;
        long totalCount = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long lcmVal = 1;
            int setBits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBits++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    if (lcmVal > mid) {
                        break;
                    }
                }
            }

            if (lcmVal <= mid) {
                if (setBits % 2 == 1) {
                    totalCount += mid / lcmVal;
                } else {
                    totalCount -= mid / lcmVal;
                }
            }
        }
        return totalCount;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}