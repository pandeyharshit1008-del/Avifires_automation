package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v149.network.Network;
import org.openqa.selenium.devtools.v149.network.model.RequestId;
import org.openqa.selenium.devtools.v149.network.model.ResponseReceived;

import java.util.Base64;
import java.util.Optional;

/**
 * Utility class for intercepting OTP from network responses
 * Uses Selenium 4 Chrome DevTools Protocol (CDP) to capture network responses
 */
public class OTPInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(OTPInterceptor.class);

    private WebDriver driver;
    private DevTools devTools;
    private String capturedOtp;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor to initialize OTPInterceptor
     *
     * @param driver WebDriver instance (must be ChromeDriver)
     */
    public OTPInterceptor(WebDriver driver) {
        this.driver = driver;
        this.capturedOtp = null;
    }

    /**
     * Start listening for network responses
     * Must be called before triggering the OTP request
     */
    public void startListening() {
        try {
            if (driver instanceof ChromeDriver) {
                devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSessionIfThereIsNotOne();

                // Enable Network domain - v149 requires 5 parameters: maxTotalBufferSize, maxResourceBufferSize, maxPostDataSize, maxPostDataSize, disableDataURLDocument
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

                // Listen for response received event
                devTools.addListener(Network.responseReceived(), response -> {
                    handleNetworkResponse(response);
                });

                logger.info("✓ Network listener started");
            } else {
                logger.info("✗ Driver is not ChromeDriver. Cannot use DevTools.");
            }
        } catch (Exception e) {
            logger.error("Error starting network listener", e);
        }
    }

    /**
     * Stop listening for network responses
     */
    public void stopListening() {
        try {
            if (devTools != null) {
                devTools.send(Network.disable());
                logger.info("✓ Network listener stopped");
            }
        } catch (Exception e) {
            logger.info("✗ Error stopping network listener: " + e.getMessage());
        }
    }

    /**
     * Handle network response and extract OTP if it matches the OTP endpoint
     *
     * @param response Network response object
     */
    private void handleNetworkResponse(ResponseReceived response) {
        try {
            String url = response.getResponse().getUrl();
            
            // Check if this is the password auth endpoint that returns OTP
            if (url.contains(ConfigConstants.PASSWORD_AUTH_ENDPOINT) || url.contains("user_auth_password")) {
                logger.info("✓ Password auth endpoint detected: " + url);

                RequestId requestId = response.getRequestId();
                extractOtpFromResponse(requestId);
            }
        } catch (Exception e) {
            logger.info("Error processing network response: " + e.getMessage());
        }
    }

    /**
     * Extract OTP from the response body
     * Gets the response body from DevTools and parses it as JSON
     */
    private void extractOtpFromResponse(RequestId requestId) {
        try {
            if (devTools == null) {
                logger.info("✗ DevTools session is not available");
                return;
            }

            var bodyResponse = devTools.send(Network.getResponseBody(requestId));
            String responseBody = bodyResponse.getBody();
            if (bodyResponse.getBase64Encoded()) {
                responseBody = new String(Base64.getDecoder().decode(responseBody));
            }

            logger.info("✓ Response body captured for OTP request: " + responseBody);

            String otp = extractOtp(responseBody);
            if (otp != null) {
                this.capturedOtp = otp;
            }
        } catch (Exception e) {
            logger.info("✗ Error extracting OTP from response: " + e.getMessage());
        }
    }

    /**
     * Extract OTP value from JSON response
     *
     * @param responseBody JSON response body as string
     * @return Extracted OTP value
     */
    public String extractOtp(String responseBody) {
        try {
            JsonNode root = mapper.readTree(responseBody);

            JsonNode otpNode = root.path("data").path(ConfigConstants.OTP_RESPONSE_FIELD);
            if (!otpNode.isMissingNode() && !otpNode.isNull()) {
                String otp = otpNode.asText();
                this.capturedOtp = otp;
                logger.info(ConfigConstants.LOG_OTP_CAPTURED + otp);
                return otp;
            }

            if (root.has(ConfigConstants.OTP_RESPONSE_FIELD)) {
                String otp = root.get(ConfigConstants.OTP_RESPONSE_FIELD).asText();
                this.capturedOtp = otp;
                logger.info(ConfigConstants.LOG_OTP_CAPTURED + otp);
                return otp;
            }

            logger.info("✗ OTP field not found in response: " + ConfigConstants.OTP_RESPONSE_FIELD);
            return null;
        } catch (Exception e) {
            logger.info("✗ Error parsing OTP from JSON response: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get the last captured OTP
     *
     * @return Captured OTP or null if not yet captured
     */
    public String getCapturedOtp() {
        return this.capturedOtp;
    }

    /**
     * Set captured OTP (useful for manual injection or testing)
     *
     * @param otp OTP value to set
     */
    public void setCapturedOtp(String otp) {
        this.capturedOtp = otp;
        logger.info(ConfigConstants.LOG_OTP_CAPTURED + otp);
    }

    /**
     * Reset captured OTP
     */
    public void resetCapturedOtp() {
        this.capturedOtp = null;
        logger.info("✓ Captured OTP reset");
    }

}
