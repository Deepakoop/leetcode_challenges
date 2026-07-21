import heapq

class Solution:
    def trapRainWater(self, heightMap: list[list[int]]) -> int:
        if not heightMap or not heightMap[0]:
            return 0
            
        m, n = len(heightMap), len(heightMap[0])
        visited = [[False] * n for _ in range(m)]
        min_heap = []
        
        for i in range(m):
            heapq.heappush(min_heap, (heightMap[i][0], i, 0))
            heapq.heappush(min_heap, (heightMap[i][n - 1], i, n - 1))
            visited[i][0] = True
            visited[i][n - 1] = True
            
        for j in range(1, n - 1):
            heapq.heappush(min_heap, (heightMap[0][j], 0, j))
            heapq.heappush(min_heap, (heightMap[m - 1][j], m - 1, j))
            visited[0][j] = True
            visited[m - 1][j] = True
            
        water = 0
        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        while min_heap:
            h, r, c = heapq.heappop(min_heap)
            
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < m and 0 <= nc < n and not visited[nr][nc]:
                    visited[nr][nc] = True
                    if heightMap[nr][nc] < h:
                        water += h - heightMap[nr][nc]
                        heapq.heappush(min_heap, (h, nr, nc))
                    else:
                        heapq.heappush(min_heap, (heightMap[nr][nc], nr, nc))
                        
        return water