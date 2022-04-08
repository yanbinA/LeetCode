/**
 * <p>
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description submissions
 * @date 2022-04-04 10:51
 * @verison V1.0.0
 */
public class NumArray {
    int[] tree;
    int n;
    public void buildTree(int node, int start, int end, int[] nums) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int mid = (end - start) / 2 + start;
        buildTree(node * 2 + 1, start, mid, nums);
        buildTree(node * 2 + 2, mid + 1, end, nums);
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    public void updateTree(int node, int start, int end, int index, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (end - start) / 2 + start;
        if (mid < index) {
            updateTree(node * 2 + 2, mid + 1, end, index, val);
        } else {
            updateTree(node * 2 + 1, start, mid, index, val);
        }
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    public int queryTree(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        if (start == end) {
            return tree[node];
        }
        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (end - start) / 2 + start;
        int sumLeft = queryTree(node * 2 + 1, start, mid, left, right);
        int sumRight = queryTree(node * 2 + 2, mid + 1, end, left, right);
        return sumLeft + sumRight;
    }


    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[n * 4];
        this.buildTree(0, 0, this.n - 1, nums);
    }

    public void update(int index, int val) {
        updateTree(0, 0 , n - 1, index, val);
    }

    public int sumRange(int left, int right) {
        return queryTree(0, 0, n - 1, left, right);
    }

    public static void main(String[] args) {
        NumArray array = new NumArray(new int[]{1, 3, 5});
        array.sumRange(0, 2);
        array.update(1, 2);
        array.sumRange(0, 2);
    }


}
