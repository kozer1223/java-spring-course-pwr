package matchers;

import com.example.entity.CurrencyData;
import com.example.entity.CurrencyValue;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Created by Kacper on 2017-04-03.
 */
public class CurrencyMatcher {

    public static <T>TypeSafeDiagnosingMatcher exchangeFrom(CurrencyData currency){
        return new TypeSafeDiagnosingMatcher<CurrencyValue>() {
            @Override
            protected boolean matchesSafely(CurrencyValue item, Description description) {
                return item.getBaseCurrency().equals(currency);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
