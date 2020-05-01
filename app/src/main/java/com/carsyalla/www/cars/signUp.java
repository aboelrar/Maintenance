package com.carsyalla.www.cars;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;

import com.carsyalla.www.cars.dialog.loading;
import com.carsyalla.www.cars.interfaces.facebook_login;
import com.carsyalla.www.cars.library.savedId;
import com.carsyalla.www.cars.presenter.fbLoginPresenter;
import com.carsyalla.www.cars.presenter.signupPresenter;
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

import com.carsyalla.www.cars.interfaces.signup;

public class signUp extends AppCompatActivity implements signup.interfaces.View, facebook_login.interfaces.View {
    com.carsyalla.www.cars.presenter.signupPresenter signupPresenter;
    LinearLayout login;
    EditText fullname,Password,Email,phoneNum;
    ImageView regist;
    static String city_id, gouvernate_id;
    static  String gouvernate_name,city_name;
    static TextView city;
    static  CallbackManager callbackManager;
    LoginButton fb_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullname=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Email=(EditText)findViewById(R.id.email);
        phoneNum=(EditText)findViewById(R.id.phone);
        city = (TextView) findViewById(R.id.city);
        Regist();
        GotToLogin();
        onClick();
        ifLogin();
        checkLoginStatus();
        fb_login();
    }

    //Go to Login Activity By Click Login Button
    private void GotToLogin()
    {
        login=(LinearLayout)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signUp.this,LoginAndRegist.class);
                startActivity(intent);
            }
        });
        }

        private void onClick()
        {
            //city
            city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(signUp.this, gouvernate_city.class);
                    startActivityForResult(i, 1);
                }
            });
        }




    //On click
    private void Regist()
    {
        regist=(ImageView)findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.getText().toString().equals(""))
                {
                    Toast.makeText(signUp.this, "من فضلك ادخل الاسم", Toast.LENGTH_SHORT).show();
                }
                else if(Email.getText().toString().equals(""))
                {
                    Toast.makeText(signUp.this, "من فضلك ادخل البريد الاكترونى", Toast.LENGTH_SHORT).show();
                }
                else if(fullname.getText().toString().equals(""))
                {
                    Toast.makeText(signUp.this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                }
                else if(city_id==null)
                {
                    Toast.makeText(signUp.this, "من فضلك ادخل المدينه", Toast.LENGTH_SHORT).show();
                }
                else if(Password.getText().toString().equals(""))
                {
                    Toast.makeText(signUp.this, "من فضلك ادخل كلمة السر", Toast.LENGTH_SHORT).show();
                }
                else {
                loading loading=new loading();
                loading.loadingDialog(signUp.this,R.layout.loading,.80);
                signupPresenter=new signupPresenter(signUp.this,signUp.this,Password,Email,fullname,phoneNum,gouvernate_id,city_id);
                signupPresenter.getData();
            }}
        });
    }


    //Check If Login Or Not
    private void ifLogin()
    {
        savedId savedId = new savedId();
        if(savedId.getUserBoolean(signUp.this)==true)
        {
            startActivity(new Intent(signUp.this,home.class));
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
        super.onActivityResult(requestCode, resultCode, data);
        //back from gouvertnate with cityid and gouvernate id
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                city_id = data.getStringExtra("city_id");
                gouvernate_id = data.getStringExtra("gouvernate_id");
                String city_name = data.getStringExtra("city_name");
                String gouvernate_name = data.getStringExtra("gouvernate_name");
                city.setText(gouvernate_name+","+city_name);
            }
        }
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
                    String email = object.getString("email");
                    String id = object.getString("id");
                    fbLoginPresenter fbLoginPresenter=new fbLoginPresenter(signUp.this,signUp.this,first_name+" "+last_name,id);
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
}



