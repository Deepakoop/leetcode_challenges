from collections import deque, defaultdict
import sys

class Solution:
    def minScore(self, n, roads):
        # Step 1: Graph (Adjacency List) banana
        graph = defaultdict(list)
        for u, v, dist in roads:
            graph[u].append((v, dist))
            graph[v].append((u, dist))
        
        # Step 2: BFS ke liye queue aur visited set initialize karna
        queue = deque([1])
        visited = {1}
        min_score = sys.maxsize
        
        # Step 3: Graph ko traverse karna
        while queue:
            node = queue.popleft()
            
            for neighbor, weight in graph[node]:
                # Har raste (edge) ka weight check karke minimum store karna
                min_score = min(min_score, weight)
                
                # Agar city visited nahi hai, toh queue mein daalna
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
                    
        return min_score