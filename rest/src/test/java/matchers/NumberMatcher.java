package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Created by Kacper on 2017-03-28.
 */
public class NumberMatcher {

    public static <T>TypeSafeDiagnosingMatcher isEven(){
        return new TypeSafeDiagnosingMatcher<Long>() {
            @Override
            protected boolean matchesSafely(Long item, Description description) {
                return (item % 2 == 0);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static <T>TypeSafeDiagnosingMatcher isNegative(){
        return new TypeSafeDiagnosingMatcher<Long>() {
            @Override
            protected boolean matchesSafely(Long item, Description description) {
                return item < 0;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
