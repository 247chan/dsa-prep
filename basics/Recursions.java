package basics;

import java.util.*;

public class Recursions {
    static int factorial(int n) {
        if (n == 0)
            return 1;

        return n * factorial(n - 1);
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    static long power(long x, int n) {
        if (n == 0)
            return 1;
        long half = power(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }

    static int sumDigits(int n) {
        if (n < 10)
            return n;

        return n % 10 + sumDigits(n / 10);
    }

    static boolean isPalindrome(String s, int i, int j) {
        return i >= j || (s.charAt(i) == s.charAt(j) && isPalindrome(s, i + 1, j - 1));
    }

    static int binarySearch(int[] a, int x, int lo, int hi) {
        if (lo > hi)
            return -1;

        int mid = lo + (hi - lo) / 2;

        if (a[mid] == x)
            return mid;
        else if (a[mid] < x)
            return binarySearch(a, x, mid + 1, hi);

        return binarySearch(a, x, lo, mid - 1);
    }

    static void hanoi(int n, char from, char to, char via, List<String> moves) {
        if (n == 0)
            return;
        hanoi(n - 1, from, via, to, moves);
        moves.add(from + "->" + to);
        hanoi(n - 1, via, via, to, moves);
    }

    // choose(add) -- explore(recurse) -- unchoose(remove)
    // reuse one placeholder and undo choices (backtracking)
    // subset has 2 options: include or exclude;
    static void subsets(char[] a, int idx, StringBuilder cur, List<String> out) {
        if (idx == a.length) {
            out.add(cur.toString());
            return;
        }

        cur.append(a[idx]);
        subsets(a, idx + 1, cur, out);
        cur.deleteCharAt(cur.length() - 1);
        subsets(a, idx + 1, cur, out);
    }

    static void permutations(char[] a, boolean[] used, StringBuilder cur, List<String> out) {
        if (cur.length() == a.length) {
            out.add(cur.toString());
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            cur.append(a[i]);
            permutations(a, used, cur, out);
            cur.deleteCharAt(cur.length() - 1);
            used[i] = false;
        }
    }

    // =============== DIVIDE & CONQUER ===================
    static int findMax(int[] a, int l, int r) {
        // l == r means only one element
        if (l == r)
            return a[l];

        int mid = (l + r) / 2;
        return Math.max(findMax(a, l, mid), findMax(a, mid + 1, r));

    }

    static int maxCrossing(int[] a, int l, int mid, int r) {
        int leftSum = Integer.MIN_VALUE;
        int lSum = 0;

        for (int i = mid; i >= l; i--) {
            lSum += a[i];
            leftSum = Math.max(leftSum, lSum);
        }

        int rightSum = Integer.MIN_VALUE;
        int rSum = 0;

        for (int i = mid + 1; i < r; i++) {
            rSum += a[i];
            rightSum = Math.max(rightSum, rSum);
        }

        return leftSum + rightSum;
    }

    // This method costs O(n log n)
    // faster way is kadane (below)
    static int maxSubarrayDC(int[] a, int l, int r) {
        if (l == r)
            return a[l];

        int mid = (l + r) / 2;

        int leftBest = maxSubarrayDC(a, l, mid);
        int rightBest = maxSubarrayDC(a, mid + 1, r);
        int crossBest = maxCrossing(a, l, mid, r);

        return Math.max(Math.max(leftBest, rightBest), crossBest);
    }

    // Instead of "what is the best array in the whole array",
    // try asking "what is the best subarray that ends exactly at index i"

    // Time - O(n)
    // Space - O(1)
    static int kadane(int[] a) {
        int cur = a[0]; // best sum of subarray ending at cur i
        int best = a[0]; // best seen so far

        for (int i = 0; i < a.length; i++) {
            cur = Math.max(cur + a[i], a[i]);
            best = Math.max(best, cur);
        }

        return best;
    }

}
