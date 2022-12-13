import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 */
public class SumOfBeautyOfAllSubstrings {
    //abaa
    //baa
    //aa
    //a

    public int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                cnt[s.charAt(j) - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[s.charAt(j) - 'a']);
                int minFreq = s.length();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minFreq = Math.min(minFreq, cnt[k]);
                    }
                }
                res += maxFreq - minFreq;
            }
        }
        return res;
    }

    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        Set<Long> set = new HashSet<>();
        int n = nums.length;
        for (int num : nums) {
            set.add((long) num);
        }
        int longest = 1;
        for (int i = 0; i < n - longest; i++) {
            long cur = (long) nums[i] * nums[i];
            int cnt = 1;
            while (set.contains(cur)) {
                cnt++;
                cur *= cur;
            }
            if (cnt > longest) {
                longest = cnt;
            }
        }
        return longest == 1 ? -1 : longest;
    }

    public static void main(String[] args) {
        SumOfBeautyOfAllSubstrings substrings = new SumOfBeautyOfAllSubstrings();
        System.out.println(substrings.longestSquareStreak(new int[]{57044, 68879, 916, 16512, 34776, 77929, 95685, 68153, 53877, 68617, 61264, 9172, 95471, 86374, 25292, 29948, 43434, 72108, 18536, 31149, 4871, 98887, 89004, 24718, 78079, 7433, 17954, 87036, 61732, 92976, 75756, 22963, 41305, 86318, 2642, 85551, 41664, 47274, 30273, 13649, 62700, 18784, 86619, 67061, 7742, 61448, 83406, 17828, 16384, 70815, 8431, 57596, 68118, 36095, 93523, 69623, 4603, 17368, 15193, 95191, 10133, 62694, 43974, 79584, 75489, 12104, 29073, 62700, 24320, 12330, 66491, 49740, 73184, 62854, 11648, 18196, 2475, 16624, 95444, 3745, 18076, 34719, 92759, 17190, 42969, 59774, 83083, 88130, 45304, 77588, 20928, 74712, 96631, 22665, 28183, 59984, 3663, 83781, 11902, 48445, 58424, 25315, 12874, 3960, 74187, 66127, 99769, 30927, 64001, 39377, 90658, 32484, 56058, 92466, 38484, 31137, 4672, 28168, 7825, 82423, 63338, 1065, 88473, 64371, 1414, 87734, 30799, 44383, 9626, 23589, 27125, 41538, 67024, 11753, 43086, 83809, 89273, 51545, 34671, 97600, 97124, 56601, 43953, 3426, 87357, 93958, 78617, 40820, 79406, 35356, 22773, 22331, 824, 13335, 87491, 45952, 64051, 4422, 32732, 54810, 58319, 80257, 8457, 38567, 23825, 90986, 53332, 80829, 42630, 84703, 92059, 71706, 82859, 10932, 44526, 75366, 94556, 63508, 97168, 93738, 50132, 97203, 37589, 25247, 91722, 78975, 46174, 18243, 31035, 35123, 68799, 90306, 37986, 54070, 74776, 81358, 68775, 58324, 89562, 2414, 25662, 89651, 32724, 1513, 7956, 99662, 43491, 87221, 78281, 18532, 85654, 27844, 94960, 12323, 69262, 77316, 1529, 96345, 54224, 31667, 11341, 74926, 81639, 22016, 15435, 21043, 75240, 44041, 41995, 63145, 31152, 11839, 44851, 41044, 25249, 85228, 32416, 19363, 59340, 92682, 8985, 34620, 19582, 34092, 44460, 75180, 30065, 87239, 82190, 65554, 21533, 17823, 4942, 74283, 85615, 98013, 77917, 91595, 13003, 47974, 71578, 90978, 85115, 90662, 24566, 94919, 12402, 16684, 367, 10186, 57090, 61947, 22330, 35424, 17835, 43363, 67607, 78103, 97290, 95214, 27559, 31675, 64594, 66189, 51482, 13368, 32055, 19302, 27842, 38091, 57168, 23314, 83867, 54887, 14179, 6793, 49643, 53522, 27599, 3919, 98308, 98405, 22269, 61504, 79522, 40657, 49053, 4709, 67769, 39429, 48828, 88834, 42535, 76393, 48147, 65246, 80079, 76512, 89293, 71234, 26528, 11235, 35342, 30643, 64679, 69718, 5338, 13441, 76133, 63183, 18984, 12510, 33658, 13884, 41050, 87905, 92799, 2178, 98761, 40606, 2992, 1268, 12352, 58325, 12272, 92713, 86555, 60458, 28896, 57882, 53824, 34237, 64917, 37947, 75421, 37784, 17352, 23495, 91134, 13002, 65928, 4803, 50925, 24483, 11272, 48590, 52836, 56399, 95390, 432, 58075, 338, 45148, 17047, 53132, 69305, 47917, 90444, 5875, 17277, 2599, 25016, 6913, 56469, 117, 4518, 1307, 53562, 53695, 50005, 80637, 19761, 99481, 48576, 41048, 7177, 74176, 99343, 97723, 16457, 80681, 12056, 3061, 89095, 82260, 58852, 33805, 20558, 45657, 18304, 269, 92630, 31192, 70905, 34505, 9195, 50200, 22082, 86326, 84806, 10492, 21917, 22761, 51636, 16320, 25925, 734, 45335, 8484, 25408, 92021, 17450, 93908, 85683, 29532, 58415, 37662, 24807, 32884, 30182, 46622, 90526, 23834, 25559, 93765, 91897, 92480, 77494, 10466, 91615, 1590, 68506, 57006, 88513, 89690, 86031, 78750, 10850, 98067, 78566, 64908, 36917, 96062, 62095, 40617, 71781, 77510, 76861, 64928, 73193, 76345, 97029, 89582, 37404, 95317, 80838, 52635, 54712, 65883, 18915, 83082, 70082, 17831, 73698, 8090, 42519, 48205, 12009, 59619, 1221, 1116, 26697, 47161, 39833, 37243, 24516, 68473, 88943, 53000, 34773, 6454, 19789, 92058, 94593, 32766, 82773, 7813, 58033, 13743, 81611, 53608, 77347, 18364, 38883, 14679, 67834, 27212, 45934, 55974, 37008, 65727, 53404, 83997, 72637, 44819, 57724, 54750, 72299, 67644, 28697, 45606, 19158, 39657, 78544, 25012, 97326, 65304, 95602, 8953, 82400, 31410, 7022, 64694, 15879, 49639, 52297, 99627, 90946, 58074, 8175, 39406, 34826, 31611, 95607, 54453, 28082, 53560, 19953, 41960, 99635, 23739, 83406, 89066, 46353, 43071, 52314, 61442, 83833, 67954, 42243, 28914, 97086, 58479, 90326, 3534, 73113, 20060, 69851, 92884, 66750, 4401, 70560, 50120, 96706, 59154, 4340, 92146, 88127, 65807, 71681, 22122, 22497, 66145, 55604, 85965, 38885, 66467, 21991, 78039, 29510, 41360, 3191, 2104, 10791, 73824, 7699, 80362, 27776, 51333, 2840, 56324, 48469, 3979, 49613, 44485, 82002, 40133}));
    }
}
