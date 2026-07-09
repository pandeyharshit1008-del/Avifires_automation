package com.automation.test.Signup;

import com.automation.utils.BaseTest;
import com.automation.pages.SignupWorkflow;
import com.automation.utils.UserCredentials;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test
    public void testValidSignUp() {
        SignupWorkflow signupWorkflow = new SignupWorkflow(driver);

        UserCredentials user = signupWorkflow.generateRandomUser();
        signupWorkflow.startOtpCapture();
        signupWorkflow.Signup(user);
        signupWorkflow.stopOtpCapture();

        boolean signupSuccess = signupWorkflow.verifySignupSuccess();
        Assert.assertTrue(signupSuccess, "Landed on signup form page after signup");
    }

    @Test
    public void testSignUpAddDetails() {
        SignupWorkflow signupWorkflow = new SignupWorkflow(driver);

        UserCredentials user = signupWorkflow.generateRandomUser();
        signupWorkflow.startOtpCapture();
        signupWorkflow.completeSignup(user);
        boolean signupSuccess = signupWorkflow.verifySignupSuccess();

        signupWorkflow.stopOtpCapture();

        Assert.assertTrue(signupSuccess, "Landed on signup form page after signup");
    }

}

