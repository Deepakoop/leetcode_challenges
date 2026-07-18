class Solution:
    def maximalRectangle(self, matrix: list[list[str]]) -> int:
        if not matrix or not matrix[0]:
            return 0
        
        n = len(matrix[0])
        heights = [0] * n
        max_area = 0
        
        for row in matrix:
            for j in range(n):
                heights[j] = heights[j] + 1 if row[j] == "1" else 0
            
            max_area = max(max_area, self.largestRectangleArea(heights))
            
        return max_area

    def largestRectangleArea(self, heights: list[int]) -> int:
        stack = []
        max_area = 0
        h_list = heights + [0]
        
        for i, h in enumerate(h_list):
            while stack and h < h_list[stack[-1]]:
                height = h_list[stack.pop()]
                width = i if not stack else i - stack[-1] - 1
                max_area = max(max_area, height * width)
            stack.append(i)
            
        return max_area