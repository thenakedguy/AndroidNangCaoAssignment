package com.hngdngcorp.hngdng.androidnangcaoassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.widget.Toast.LENGTH_LONG;

public class ShareActivity<loginButton> extends BaseActivity {
    public Button loginButton;
    public CallbackManager callbackManager;
    public TextView textView;
    private static final String EMAIL = "email";
    private AccessToken mAccessToken;
    private LoginButton mLoginButton;
    private ProfilePictureView mProfilePictureView;
    private TextView mTvName;
    private TextView mTvEmail;
    private Button btnShare;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
         callbackManager = CallbackManager.Factory.create();
        btnShare = findViewById(R.id.btnShare);
//        btnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentDescription("Test share link on facebook")
//                        .setQuote("Day la mot ung dung ")
//                        .setContentTitle("Share something on facebook ")
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .build();
//            }
//        });




        loginButton = (LoginButton) findViewById(R.id.login_button);
        ((LoginButton) loginButton).setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        ((LoginButton) loginButton).registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mProfilePictureView = findViewById(R.id.profilePictureView);
                mTvName = findViewById(R.id.tvName);
                mTvEmail = findViewById(R.id.tvEmail);
                getUserProfile();
            }

            @Override
            public void onCancel() {
                mTvName.setText("");
                mTvEmail.setText("");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });





    }



    private void getUserProfile(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String email = object.getString("email");
                    String name = object.getString("name");
                    mTvName.setText(name);
                    mTvEmail.setText(email);
                    mProfilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();

    }
}
