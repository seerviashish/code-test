class LongestEvenLengthSubString {

    public static void main(String[] args) {
        String str = "1538023";
        System.out.println(findLength(str));
    }

    private static int findLength(String str) {
        int n = str.length();
        int mxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j += 2) {
                int len = j - i + 1;
                int leftSum = 0, rightSum = 0;
                for (int k = 0; k < len / 2; k++) {
                    leftSum += (str.charAt(i + k) - '0');
                    rightSum += (str.charAt(i + k + len / 2) - '0');
                }
                if (leftSum == rightSum && mxLen < len) {
                    mxLen = len;
                }
            }
        }
        return mxLen;
    }
}