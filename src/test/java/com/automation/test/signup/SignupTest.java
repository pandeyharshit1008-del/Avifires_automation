package com.automation.signup;

import com.automation.base.BaseTest;
import com.automation.pages.SignupWorkflow;
import com.automation.utils.UserCredentials;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Signup functionality.
 */
public class SignupTest extends BaseTest {

    /**
     * Test successful sign-up using dynamically generated credentials and OTP capture.
     */
    @Test
    public void testValidSignUp() {
        SignupWorkflow signupWorkflow = new SignupWorkflow(driver);

        UserCredentials user = signupWorkflow.generateRandomUser();
        signupWorkflow.startOtpCapture();
        signupWorkflow.Signup(user);
        boolean signupSuccess = signupWorkflow.verifySignupSuccess();
        signupWorkflow.stopOtpCapture();
        Assert.assertTrue(signupSuccess, "Landed on signup form page after signup");
    }

    /**
     * Test signup with additional details.
     */
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
