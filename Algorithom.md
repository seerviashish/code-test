# Algorithm

---

## Pattern Searching

1. Naive Algorithm

   ```java
   private static int search(String main, String ptrn) {
       int m = main.length();
       int n = ptrn.length();

       for (int i = 0; i <= n - m; i++) {
           int j;
           for (j = 0; j < m; j++) {
               if (main.charAt(i + j) != ptrn.charAt(j)) {
                   break;
               }
           }
           if (j == m) {
               return i;
           }
       }
   }
   ```

2. KMP (Knuth Morris Pratt) Algorithm

   ```java
       private static void kmpSearch(String pat, String txt) {
       int m = pat.length();
       int n = txt.length();
       int lps[] = new int[m];
       computeLPS(pat, m, lps);

       int j = 0, i = 0;

       while (i < n) {
           if (pat.charAt(j) == txt.charAt(i)) {
               j++;
               i++;
           }
           if (j == m) {
               System.out.println("Found pattern " + "at index " + (i - j));
               j = lps[j - 1];
           } else if (i < n && pat.charAt(j) != txt.charAt(i)) {
               if (j != 0)
                   j = lps[j - 1];
               else
                   i = i + 1;
           }
       }
   }

   private static void computeLPS(String pat, int m, int lps[]) {
       int len = 0;
       int i = 1;
       lps[0] = 0;

       while (i < m) {
           if (pat.charAt(i) == pat.charAt(len)) {
               len++;
               lps[i] = len;
               i++;
           } else {
               if (len != 0) {
                   len = lps[len - 1];
               } else {
                   lps[i] = len;
                   i++;
               }
           }
       }
   }

   ```

3. Trie Search

   ```java
   static final int ALPHABET_SIZE = 26;

   static TrieNode root;

   static class TrieNode {
       TrieNode[] child = new TrieNode[ALPHABET_SIZE];

       boolean isEndOfWord;

       TrieNode() {
           isEndOfWord = false;
           for (int i = 0; i < ALPHABET_SIZE; i++) {
               child[i] = null;
           }
       }
   }

   static void insert(String key) {
       int level;
       int length = key.length();
       int index;
       TrieNode pCrawl = root;
       for (level = 0; level < length; level++) {
           index = key.charAt(level) - 'a';
           if (pCrawl.child[index] == null) {
               pCrawl.child[index] = new TrieNode();
           }
           pCrawl = pCrawl.child[index];
       }
       pCrawl.isEndOfWord = true;
   }

   static boolean search(String key) {
       int level;
       int length = key.length();
       int index;
       TrieNode pCrawl = root;

       for (level = 0; level < length; level++) {
           index = key.charAt(level) - 'a';

           if (pCrawl.child[index] == null)
               return false;

           pCrawl = pCrawl.child[index];
       }

       return (pCrawl != null && pCrawl.isEndOfWord);
   }
   ```
