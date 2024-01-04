package com.example.subscriptionflowdemoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.subscriptionflow.sdk.SubscriptionFlow
import com.subscriptionflow.sdk.utils.`Constants$SFAuthentication`.ERROR
import com.subscriptionflow.sdk.utils.`Constants$SFAuthentication`.SUCCESSFUL
import com.subscriptionflow.sdk.SFConfigurations

class MainActivity : AppCompatActivity() {

    // Set the clientID, secretKey, and domain for authentication and configuration.
    private val clientID = ""
    private val secretKey = ""
    private val domain = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initiateSubscriptionFlowObserverMode()

    }

    /**
     * Initiates the SubscriptionFlow SDK in observer mode, configuring it with the specified SF configurations.
     *
     * The authentication result is logged, indicating whether the configuration was successful or resulted in an error.
     */
    private fun initiateSubscriptionFlowObserverMode() {
        // Configure the SubscriptionFlow SDK with SF configurations obtained from the getSfConfigurations() method.
        SubscriptionFlow.getInstance()
            .configureSDK(getSfConfigurationsWithRequestParams()) { authenticationResult ->

                // Configure the SubscriptionFlow SDK with SF configurations obtained from the getSfConfigurations() method.
                when (authenticationResult?.status) {
                    SUCCESSFUL -> Log.d("Authentication Response", authenticationResult.message)
                    ERROR -> Log.d("Authentication Response", authenticationResult.message)
                }
            }
    }

    /**
     * Retrieves the SubscriptionFlow (SF) configurations with additional request parameters required
     * for initializing the SF SDK.
     *
     * @return An instance of [SFConfigurations] containing the necessary parameters for SF initialization,
     *         including observer mode and request parameters, or null if the configurations cannot be obtained.
     */
    private fun getSfConfigurationsWithRequestParams(): SFConfigurations? {

        // Create request parameters with sample attribute values.
        val requestAttributes: MutableMap<String, String> = HashMap()
        requestAttributes["name"] = "dummy_value_1"
        requestAttributes["email"] = "dummy_value_1"

        // Build and return SFConfigurations using the SFConfigurations.Builder, enabling observer mode
        // and setting additional request parameters.
        return SFConfigurations.Builder(this, clientID, secretKey, domain)
            .observerMode(true)
            .setRequestParameters(requestAttributes)
            .build()
    }

    /**
     * Retrieves the SubscriptionFlow (SF) configurations required for initializing the SF SDK.
     *
     * @return An instance of [SFConfigurations] containing the necessary parameters for SF initialization,
     *         or null if the configurations cannot be obtained.
     */
    private fun getSfConfigurations(): SFConfigurations? {
        // Build and return SFConfigurations using the SFConfigurations.Builder.
        return SFConfigurations.Builder(this, clientID, secretKey, domain)
            .observerMode(true)
            .build()
    }
}