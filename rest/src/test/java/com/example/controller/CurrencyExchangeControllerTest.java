package com.example.controller;

import com.example.controller.CurrencyExchangeController;
import matchers.NumberMatcher;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static matchers.NumberMatcher.*;

/**
 * Created by Kacper on 2017-03-28.
 */
public class CurrencyExchangeControllerTest {

    CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        System.out.println("setUp");
    };

    @After
    public void tearDown(){
        System.out.println("tearDown");
    };

    @Test
    public void twoMultiplyByTwoReturnsFour(){
        Assert.assertEquals(4L, currencyExchangeController.multiplyByTwo(2L), 0L);
    }

    @Test(expected = RuntimeException.class)
    public void testAddCurrencySignatureThrowsException(){
        currencyExchangeController.addCurrencySignature(2L, "not a currency");
    }

    @Test
    public void testAddCurrencySignatureThrowsSomethingWentWrong(){
        try{
            currencyExchangeController.addCurrencySignature(2L, "not a currency");
            Assert.fail();
        } catch(RuntimeException e){
            Assert.assertEquals("Something went wrong.", e.getMessage());
        }
    }

    @Test
    public void testAddCurrencySignatureThrowsSomethingWentWrongWithRule() throws Exception{
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Something went wrong.");

        currencyExchangeController.addCurrencySignature(2L, "not a currency");
    }

    @Test
    public void shouldReturnEvenWhenMultiplyingFiveByTwo(){
        Long result = currencyExchangeController.multiplyByTwo(5L);
        Assert.assertThat(result, isEven());
    }

    @Test
    public void shouldReturnNegativeWhenMultiplyingMinusTwoByTwo(){
        Long result = currencyExchangeController.multiplyByTwo(-2L);
        Assert.assertThat(result, isNegative());
    }
}
