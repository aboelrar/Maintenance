package com.carsyalla.www.cars;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.forget_forget.forget_passwrd;



import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.facebook_login;
import com.carsyalla.www.cars.interfaces.login;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.fbLoginPresenter;
import com.carsyalla.www.cars.presenter.loginPresenter;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginAndRegist extends AppCompatActivity implements login.interfaces.View, facebook_login.interfaces.View {
  LinearLayout CreateAccount;
    com.carsyalla.www.cars.presenter.fbLoginPresenter fbLoginPresenter;
 static EditText email,password;
 static TextView city,skip;
  ImageView Home;
  String res="notRes";
  loginPresenter loginPresenter;
    static String city_id, gouvernate_id;
    LoginButton fb_login;
   static  CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_regist);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        printKeyHash();
        OnClickButton();
        YesIcon();
        ifLogin();
        checkLoginStatus();
        fb_login();
        }



    //Go to SignUp Activity By Click Button
    private void OnClickButton()
    {
        CreateAccount=(LinearLayout)findViewById(R.id.createAccount);
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAndRegist.this,signUp.class);
                startActivity(intent);
            }
        });

        //login Button
        Home=(ImageView)findViewById(R.id.goToHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals(""))
                {
                    Toast.makeText(LoginAndRegist.this, "من فضلك ادخل البريد الالكترونى", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equals(""))
                {
                    Toast.makeText(LoginAndRegist.this, "من فضلك ادخل كلمة السر", Toast.LENGTH_SHORT).show();
                }
                else {
                //Login Data Connected Server
                loading loading=new loading();
                loading.loadingDialog(LoginAndRegist.this,R.layout.loading_login,.80);
                Intent intent=getIntent();
                if(intent.getStringExtra("res")==null)
                {
                    res="notres";
                }else {
                    res=intent.getStringExtra("res");
                    }
                loginPresenter=new loginPresenter(LoginAndRegist.this,LoginAndRegist.this,email,password,res);
                loginPresenter.getData();
            }}
        });
        skip=(TextView)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegist.this,home.class));
            }
        });

        //go To Forget Password
        TextView forget_password=(TextView)findViewById(R.id.forget_password);
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegist.this, forget_passwrd.class));
            }
        });

    }

        //Yes Icon When Click
    private void YesIcon()
    {

        //on click on password edittext
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    email.setCompoundDrawablesWithIntrinsicBounds( R.drawable.email,0, R.drawable.yesicon, 0);
                }
            }
        });
        }

    //Check If Login Or Not
       private void ifLogin()
       {
           savedId savedId = new savedId();
           if(savedId.getUserBoolean(LoginAndRegist.this)==true)
           {
               startActivity(new Intent(LoginAndRegist.this,home.class));
               finish();
               }
       }

    //When Back from Login Application Will Close
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    //FACEBOOK LOGIN
    private void fb_login()
    {
        callbackManager=CallbackManager.Factory.create();
        fb_login=findViewById(R.id.fb_login);
        fb_login.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            AccessToken.setCurrentAccessToken(null);
            Profile.setCurrentProfile(null);

            if(currentAccessToken==null)
            {
                }
            else
                loadUserProfile(currentAccessToken);

        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                  //  String email = object.getString("email");
                    String id = object.getString("id");
                    fbLoginPresenter=new fbLoginPresenter(LoginAndRegist.this,LoginAndRegist.this,first_name+" "+last_name,id);
                    fbLoginPresenter.getData();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus()
    {
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    public void printKeyHash()
    {

            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.carsyalla.www",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
        }

}
