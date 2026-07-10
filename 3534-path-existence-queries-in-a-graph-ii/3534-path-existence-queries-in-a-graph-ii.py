class Solution:
    def pathExistenceQueries(self, n: int, nums: list[int], maxDiff: int, queries: list[list[int]]) -> list[int]:
        # नोड्स को सॉर्ट करें ताकि हम रेंज के साथ काम कर सकें
        sorted_nodes = sorted(range(n), key=lambda i: nums[i])
        sorted_nums = [nums[i] for i in sorted_nodes]
        
        # 'up[k][i]' का मतलब है: i-th sorted node से 2^k जंप के बाद इंडेक्स
        # चूँकि ग्राफ undirected है, हम दोनों तरफ जंप देख सकते हैं
        # यहाँ हम एक Sparse Table (Binary Lifting) बनाएंगे
        LOG = 18
        
        # left_reach[i]: नोड i से सबसे दूर बाएँ जा सकने वाला इंडेक्स
        # right_reach[i]: नोड i से सबसे दूर दाएँ जा सकने वाला इंडेक्स
        left_reach = [i for i in range(n)]
        right_reach = [i for i in range(n)]
        
        # Sliding window से सबसे दूर तक पहुँचने वाले इंडेक्स निकालें
        l = 0
        for r in range(n):
            while sorted_nums[r] - sorted_nums[l] > maxDiff:
                l += 1
            left_reach[r] = l
            
        r = n - 1
        for l in range(n - 1, -1, -1):
            while sorted_nums[r] - sorted_nums[l] > maxDiff:
                r -= 1
            right_reach[l] = r
            
        # Binary Lifting Table
        up_right = [[i for i in range(n)] for _ in range(LOG)]
        up_left = [[i for i in range(n)] for _ in range(LOG)]
        
        up_right[0] = right_reach
        up_left[0] = left_reach
        
        for k in range(1, LOG):
            for i in range(n):
                up_right[k][i] = up_right[k-1][up_right[k-1][i]]
                up_left[k][i] = up_left[k-1][up_left[k-1][i]]
        
        # मैप: ओरिजिनल इंडेक्स -> सॉर्टेड इंडेक्स
        pos = [0] * n
        for i, original_idx in enumerate(sorted_nodes):
            pos[original_idx] = i
            
        ans = []
        for u, v in queries:
            if u == v:
                ans.append(0)
                continue
                
            start, target = pos[u], pos[v]
            # यदि target, start से आगे है तो Right lift, पीछे है तो Left lift
            curr = start
            dist = 0
            
            # Binary lifting का उपयोग करके दूरी पता करें
            if start < target:
                table = up_right
                for k in range(LOG - 1, -1, -1):
                    if table[k][curr] < target:
                        curr = table[k][curr]
                        dist += (1 << k)
                ans.append(dist + 1 if table[0][curr] >= target else -1)
            else:
                table = up_left
                for k in range(LOG - 1, -1, -1):
                    if table[k][curr] > target:
                        curr = table[k][curr]
                        dist += (1 << k)
                ans.append(dist + 1 if table[0][curr] <= target else -1)
                
        return ans