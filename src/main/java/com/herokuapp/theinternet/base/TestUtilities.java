package com.herokuapp.theinternet.base;

import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest{

    protected void sleep(long seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @DataProvider(name="positiveLoginCredentials")
    protected static Object[][] positiveLogin(){
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!", "You logged into a secure area!"}
        };
    }

    @DataProvider(name="positiveLogoutCredentials")
    protected static Object[][] positiveLogout(){
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!", "You logged out of the secure area!"}
        };
    }

    @DataProvider(name="negativeLoginCredentials")
    protected static Object[][] negativeLogin(){
        return new Object[][] {
                {"incorrectUserName", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "incorrectPassword!", "Your password is invalid!"},
                {"incorrectUserName", "incorrectPassword!", "Your username is invalid!"},
                {"", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "", "Your password is invalid!"}
        };
    }
}
