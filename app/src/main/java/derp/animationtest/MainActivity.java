package derp.animationtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by daniel.colleluori on 24/03/2017.
 */

public class MainActivity extends AppCompatActivity
{
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ImageView ivAndroid = (ImageView) findViewById(R.id.ivAndroid);

        ivAndroid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(running)
                {
                    running = false;
                    ivAndroid.setAnimation(null);
                }
                else
                {
                    running = true;
                    RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.48f, Animation.RELATIVE_TO_SELF, 0.5f);
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setRepeatCount(Animation.INFINITE);
                    anim.setDuration(2000);
                    ivAndroid.startAnimation(anim);
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDisconnectPrompt();
            }
        });
    }

    private AlertDialog alertDisconnect;

    private void showDisconnectPrompt()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        @SuppressLint("InflateParams")
        final View inflater = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_dashboard_disconnect, null);
        builder.setView(inflater);

//        final CardView revealView = (CardView) inflater.findViewById(R.id.revealView);
        Button btnCancel = (Button) inflater.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alertDisconnect.dismiss();
            }
        });

        alertDisconnect = builder.create();
//        alertDisconnect.setOnShowListener(new DialogInterface.OnShowListener()
//        {
//            @Override
//            public void onShow(DialogInterface dialog)
//            {
//                Revealator.reveal(revealView).start();
//            }
//        });

//        Window dialogWindow = alertDisconnect.getWindow();
//        if(dialogWindow != null)
//        {
//            dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        }
        alertDisconnect.show();
    }
}
