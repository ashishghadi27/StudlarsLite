package lite.studlarsinc.root.com.studlarslite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    int secondsDelayed = 1;

    new Handler().postDelayed(new Runnable() {
      public void run() {
        try {
          Intent i = new Intent(Splash.this, MainActivity.class);
          startActivity(i);
          finish();
        }catch (Exception e)
        {
          Toast.makeText(Splash.this,"App crashed",Toast.LENGTH_SHORT).show();
        }


        // close this activity

      }
    }, secondsDelayed * 1000);

  }
}
