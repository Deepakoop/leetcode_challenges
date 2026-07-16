class Solution:
    def minimumCost(self, cost: list[int]) -> int:
        # 1. Prices ko descending order mein sort karein
        cost.sort(reverse=True)
        
        total_cost = 0
        
        # 2. Iterate karein aur har 3rd candy (index 2, 5, 8...) ko skip karein
        for i in range(len(cost)):
            if i % 3 != 2:
                total_cost += cost[i]
                
        return total_cost