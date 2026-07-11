from collections import defaultdict

class Solution:
    def countCompleteComponents(self, n: int, edges: list[list[int]]) -> int:
        # Build the graph
        graph = defaultdict(list)
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
            
        visited = [False] * n
        complete_count = 0
        
        def dfs(node):
            visited[node] = True
            # Returns (node_count, degree_sum)
            nodes = 1
            degrees = len(graph[node])
            
            for neighbor in graph[node]:
                if not visited[neighbor]:
                    n_count, d_sum = dfs(neighbor)
                    nodes += n_count
                    degrees += d_sum
            return nodes, degrees

        for i in range(n):
            if not visited[i]:
                v_count, d_sum = dfs(i)
                # A complete component has V vertices, each with degree V-1.
                # Total degree sum = V * (V - 1)
                if d_sum == v_count * (v_count - 1):
                    complete_count += 1
                    
        return complete_count