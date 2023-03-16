
/**
 * 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class KokoEatingBananas {

    public int minEatingSpeed1(int[] piles, int h) {
        int right = 0, left = 1;
        for (int pile : piles) {
            right = Math.max(pile, right);
        }
        while (right > left) {
            int mid = left + (right - left) / 2;
            int sum = getTime(piles, mid);
            if (sum > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int getTime(int[] piles, int mid) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile + mid - 1) / mid;
        }
        return sum;
    }

    public static void main(String[] args) {
        KokoEatingBananas bananas = new KokoEatingBananas();
        System.out.println(bananas.minEatingSpeed1(new int[]{10000, 100, 50}
                , 1000));
        System.out.println(bananas.minEatingSpeed1(new int[]{873375536, 395271806, 617254718, 970525912, 634754347, 824202576, 694181619, 20191396, 886462834, 442389139, 572655464, 438946009, 791566709, 776244944, 694340852, 419438893, 784015530, 588954527, 282060288, 269101141, 499386849, 846936808, 92389214, 385055341, 56742915, 803341674, 837907634, 728867715, 20958651, 167651719, 345626668, 701905050, 932332403, 572486583, 603363649, 967330688, 484233747, 859566856, 446838995, 375409782, 220949961, 72860128, 998899684, 615754807, 383344277, 36322529, 154308670, 335291837, 927055440, 28020467, 558059248, 999492426, 991026255, 30205761, 884639109, 61689648, 742973721, 395173120, 38459914, 705636911, 30019578, 968014413, 126489328, 738983100, 793184186, 871576545, 768870427, 955396670, 328003949, 786890382, 450361695, 994581348, 158169007, 309034664, 388541713, 142633427, 390169457, 161995664, 906356894, 379954831, 448138536}
                , 943223529));
        System.out.println(bananas.minEatingSpeed1(new int[]{991988498, 635554785, 800826315, 84628839, 125457816, 289057051, 932180235, 632966903, 737222110, 927657625, 980631240, 315272792}
                , 623699466));
    }
}