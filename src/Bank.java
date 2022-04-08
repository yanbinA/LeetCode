/**
 * <p>
 * <h3>2043. 简易银行系统</h3>
 * 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance 中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。
 *
 * 请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
 *
 * 指定的账户数量在 1 和 n 之间，且
 * 取款或者转账需要的钱的总数 小于或者等于 账户余额。
 * 实现 Bank 类：
 *
 * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
 * boolean transfer(int account1, int account2, long money) 从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
 * boolean deposit(int account, long money) 向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description simple-bank-system
 * @date 2022-03-18 9:43
 * @verison V1.0.0
 */
public class Bank {
    int n;
    long[] balance;

    public Bank(long[] balance) {
        this.n = balance.length;
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account2 < 0 || account2 > n) {
            return false;
        }
        if (account1 < 0 || account1 > n) {
            return false;
        }
        if (balance[account1 - 1] < money) {
            return false;
        }
        balance[account2 - 1] += money;
        balance[account1 - 1] -= money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account < 0 || account > n) {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account < 0 || account > n && balance[account - 1] < money) {
            return false;
        }
        balance[account - 1] -= money;
        return true;
    }


    public static void main(String[] args) {
        Bank bank = new Bank(new long[]{10, 100, 20, 50, 30});
        System.out.println(bank.withdraw(3, 20));
    }
}
