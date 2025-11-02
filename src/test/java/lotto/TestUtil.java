package lotto;

import lotto.util.Global;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public interface TestUtil {

    TestUtil GLOBAL = new TestUtil() {
    };

    default AbstractThrowableAssert<?, ? extends Throwable> assertThatThrownBy(
            ThrowingCallable shouldRaiseThrowable) {
        return org.assertj.core.api.Assertions.assertThatThrownBy(shouldRaiseThrowable)
                .isInstanceOf(Global.BASE_RUNTIME_EXCEPTION)
                .hasMessageStartingWith(Global.ERROR_PREFIX);
    }

}
