package pairmatching.util;

import java.util.function.Supplier;

public final class Retry {

    private Retry() {}

    public static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
