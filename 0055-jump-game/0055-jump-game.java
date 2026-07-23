class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // अगर वर्तमान इंडेक्स (i) हमारे अधिकतम पहुँचने योग्य इंडेक्स (reachable) से ज़्यादा है,
            // तो इसका मतलब हम यहाँ तक नहीं पहुँच सकते।
            if (i > reachable) {
                return false;
            }
            
            // अपडेट करें कि हम अधिकतम कहाँ तक पहुँच सकते हैं
            reachable = Math.max(reachable, i + nums[i]);
            
            // अगर हम आखिरी इंडेक्स या उससे आगे पहुँच चुके हैं, तो true रिटर्न कर दें
            if (reachable >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}