class Solution:
    def sumAndMultiply(self, n):
        # संख्या को स्ट्रिंग में बदलें
        s = str(n)
        
        # बिना जीरो वाले डिजिट्स की लिस्ट बनाएं
        digits = [c for c in s if c != '0']
        
        # अगर कोई नॉन-जीरो डिजिट नहीं है
        if not digits:
            return 0
            
        # x की वैल्यू निकालें और अंकों का जोड़ करें
        x = int("".join(digits))
        digit_sum = sum(int(c) for c in digits)
        
        # दोनों का गुणा करके रिटर्न करें
        return x * digit_sum