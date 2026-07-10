from collections import Counter

class Solution:
    def findSubstring(self, s: str, words: list[str]) -> list[int]:
        if not s or not words:
            return []
        
        word_len = len(words[0])
        num_words = len(words)
        total_len = word_len * num_words
        word_counts = Counter(words)
        ans = []
        
        # Check for each of the possible offsets
        for i in range(word_len):
            left = i
            right = i
            current_counts = Counter()
            
            while right + word_len <= len(s):
                # Get the next word
                word = s[right:right + word_len]
                right += word_len
                
                if word in word_counts:
                    current_counts[word] += 1
                    
                    # If count exceeds requirement, shrink from the left
                    while current_counts[word] > word_counts[word]:
                        left_word = s[left:left + word_len]
                        current_counts[left_word] -= 1
                        left += word_len
                    
                    # If window size equals total required length, we found a match
                    if right - left == total_len:
                        ans.append(left)
                else:
                    # Reset window if word is not in the list
                    current_counts.clear()
                    left = right
                    
        return ans