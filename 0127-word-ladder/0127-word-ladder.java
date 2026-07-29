import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> allComboDict = new HashMap<>();
        int L = beginWord.length();

        for (String word : wordList) {
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Pair node = queue.poll();
            String currentWord = node.word;
            int level = node.level;

            for (int i = 0; i < L; i++) {
                String newWord = currentWord.substring(0, i) + '*' + currentWord.substring(i + 1);

                for (String neighbor : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (neighbor.equals(endWord)) {
                        return level + 1;
                    }
                    
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(new Pair(neighbor, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    private static class Pair {
        String word;
        int level;

        Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}