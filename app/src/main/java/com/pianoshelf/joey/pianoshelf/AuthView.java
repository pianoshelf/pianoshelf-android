package com.pianoshelf.joey.pianoshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * It seems impossible to execute two concurrent activities in android
 * Thus its only logical that either the login was done on the main activity or
 * in a separate login activity.
 * We can assume that every time the user presses the login key, we complete the POST request
 * However, we only process the information received by the POST request iff we are in the same
 * activity that initiated the POST request, thus we are exposed to an edge case where the request
 * is sent but the activity is frozen on the stack if we allow the user to go back from the login
 * activity. Thus I believe we either need to stall the user (bad) or find a concurrent way to
 * process the return value of the POST request.
 * http://django-rest-auth.readthedocs.org/en/latest/api_endpoints.html
 * Created by root on 11/25/14.
 */
public class AuthView extends Activity implements TaskDelegate {
    private String token;
    private String username;
    private String password;
    private ProgressBar progressBar;
    private String LOG_TAG = "AuthView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String intentAction = intent.getAction();
        // Originally this class was intended to house both login and logout, now they are separate
        if (Main.ACTION_LOGIN.equals(intentAction)) {
            //username = intent.getStringExtra(Main.USERNAME);
            //password = intent.getStringExtra(Main.PASSWORD);
            setContentView(R.layout.activity_loginview);
            progressBar = (ProgressBar) findViewById(R.id.loginview_progress);
        }
    }

    @Override
    public void onBackPressed () {

    }

    /**
     * Logout the current user, if user is logged in
     * @param view
     */
    public void invokeLogout(View view) {
        progressBar.setVisibility(View.VISIBLE);
        // TODO add a logout asynctask and implement corresponding UI changes here
    }

    /**
     * POST login if username and password are valid
     * @param view layout for this class
     */
    public void invokeLogin(View view) {
        String username = ((EditText) findViewById(R.id.loginview_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.loginview_password)).getText().toString();
        TextView errorMessageTextView = (TextView) findViewById(R.id.loginview_warning_message);

        String errorMessage = errorMessageTextView.getText().toString();
        // Clear the error messages if there was any
        if ((errorMessage != null) && !errorMessage.isEmpty()) {
            errorMessageTextView.setText("");
        }

        // Verify username and password
        // Short circuiting
        if (checkUsername(username) && checkPassword(password)) {
            progressBar.setVisibility(View.VISIBLE);
            LoginTask login = new LoginTask(this);
            login.execute(username, password);
        }
    }

    private boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            ((TextView) findViewById(R.id.loginview_warning_message))
                    .setText("Please enter an username");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            ((TextView) findViewById(R.id.loginview_warning_message))
                    .setText("Please enter a password");
            return false;
        }
        return true;
    }

    /**
     * Required by delegate
     * Update views and set Token if login has succeeded
     * @param token Authorization token returned by POST login
     */
    @Override
    public void taskCompleted(String token) {
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        Intent returnIntent = new Intent();
        progressBar.setVisibility(View.INVISIBLE);
        if (token != null) {
            this.token = token;
            returnIntent.putExtra(Main.AUTHORIZATION_TOKEN, token);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else {
            // Failed to fetch token, update view accordingly
            ((TextView) view.findViewById(R.id.loginview_error_message))
                    .setText("Login failed. Please verify your username and password");
            // TODO put a failure reason on returnIntent
            setResult(Main.RESULT_FAILED);
        }
    }
}