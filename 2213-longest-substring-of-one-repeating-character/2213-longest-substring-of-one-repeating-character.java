class Solution {
    class Node {
        int l, r;
        int lmx, rmx, mx; // left max repeating, right max repeating, total max repeating
        
        Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.lmx = 1;
            this.rmx = 1;
            this.mx = 1;
        }
    }

    private Node[] tr;
    private char[] s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s.toCharArray();
        int n = this.s.length;
        tr = new Node[n * 4];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            this.s[idx] = c;
            modify(1, idx, c);
            res[i] = tr[1].mx;
        }
        return res;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    private void modify(int u, int idx, char c) {
        if (tr[u].l == tr[u].r) {
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (idx <= mid) {
            modify(u << 1, idx, c);
        } else {
            modify(u << 1 | 1, idx, c);
        }
        pushup(u);
    }

    private void pushup(int u) {
        Node left = tr[u << 1];
        Node right = tr[u << 1 | 1];
        
        tr[u].lmx = left.lmx;
        if (left.lmx == (left.r - left.l + 1) && s[left.r] == s[right.l]) {
            tr[u].lmx += right.lmx;
        }

        tr[u].rmx = right.rmx;
        if (right.rmx == (right.r - right.l + 1) && s[right.l] == s[left.r]) {
            tr[u].rmx += left.rmx;
        }

        tr[u].mx = Math.max(left.mx, right.mx);
        if (s[left.r] == s[right.l]) {
            tr[u].mx = Math.max(tr[u].mx, left.rmx + right.lmx);
        }
    }
}