class Solution:
    def sequentialDigits(self, low: int, high: int) -> list[int]:
        result = []
        
        # Sequential numbers 1 se 9 se start ho sakte hain
        for i in range(1, 10):
            num = i
            next_digit = i + 1
            
            # Jab tak number high se chota hai aur hume valid digits mil rahe hain
            while num <= high and next_digit <= 9:
                # Naye digit ko current number ke peeche add karna
                num = num * 10 + next_digit
                
                # Check agar number range mein hai
                if low <= num <= high:
                    result.append(num)
                
                # Agle loop ke liye agla digit prepare karna
                next_digit += 1
        
        # Kyunki hume numbers sort karke dene hain
        result.sort()
        
        return result