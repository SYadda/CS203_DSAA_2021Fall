class Solution {
    public int myAtoi(String s) {
        int length = s.length();
        int i;
        for (i = 0; i < length; i++) {
            if (s.charAt(i) != 32) {
                break;
            }
        }
        if (i == length) return 0;

        boolean positive;
        char this_c = s.charAt(i);

        if (this_c == 43) {
            positive = true;
            i++;
        } else if (this_c == 45) {
            positive = false;
            i++;
        } else if (47 < this_c && this_c < 58) {
            positive = true;
        } else {
            return 0;
        }

        int count = 0, next_count;
        char this_c_48;
        if (positive) {
            for (; i < length; i++) {
                this_c = s.charAt(i);
                if (47 < this_c && this_c < 58) {
                    this_c_48 = (char) (this_c - 48);
                    next_count = count * 10 + this_c_48;
                    if ( next_count >= 0 && ( next_count - this_c_48 ) / 10 == count ) {
                        count = next_count;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    break;
                }
            }
            return count;
        } else {
            for (; i < length; i++) {
                this_c = s.charAt(i);
                if (47 < this_c && this_c < 58) {
                    this_c_48 = (char) (this_c - 48);
                    next_count = count * 10 - this_c_48;
                    if ( next_count <= 0 && ( next_count + this_c_48 ) / 10 == count ) {
                        count = next_count;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    break;
                }
            }
            return count;
        }
    }
}

public class tenp {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.myAtoi("00000-42a1234"));
    }
}