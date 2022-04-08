package src.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *
 * </p>
 *
 * @author messi
 * @package concurrent.atomic
 * @description
 * @date 2022-04-07 23:38
 * @verison V1.0.0
 */
public class AtomicReferenceTest {
    @Test
    public void AtomicReferenceTest() {
        AtomicReference<Account> reference = new AtomicReference<>();
        reference.compareAndSet()
    }

    static class Account {
        int value;
        int version;
    }
}
