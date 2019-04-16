package com.example.muase.iloveyou;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickButtonListener{
    private AlertDialog mAlertDialog;
    private int mIndex = 0;
    private float mX1 = 0.0f;
    private float mX2 = 0.0f;
    private float mY = 0.0f;
    private Button buttonYes, buttonNo;
    private ImageView imgCuoi, imgLove;
    private boolean love = false, bye = false;
    private TextView fbAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
    }

    private void onClick() {
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exit = buttonYes.getText().toString();
                if (exit.equalsIgnoreCase("Bye anh!")){
                    if (love){
                        createDialog("<3.. <3", R.drawable.ic_bye_love);
                    }else createDialog("Chờ e đến ngày mai...", R.drawable.ic_bye);
                    bye = true;
                }else {
                    createDialog("Anh biết mà!! Anh cũng yêu em lắm lun.. <3", R.drawable.ic_kiss);
                    love = true;
                    imgLove.setVisibility(View.VISIBLE);
                    imgLove.setX(250);
                    imgLove.setY(100);
                    imgLove.setImageResource(R.drawable.ic_kvtm);

                    buttonYes.setText("Bye em!");
                }
            }
        });

        buttonNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    move();
                    return true;
                } else
                    return false;
            }
        });

        fbAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hongg.nhungg.nt"));
                startActivity(intent);
            }
        });
    }

    public void createDialog(String message, int idIcon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_message,null);

        TextView tvMessage = view.findViewById(R.id.textMessage);
        ImageView imgIcon = view.findViewById(R.id.imageIcon);
        Button btnHihi = view.findViewById(R.id.btn_HiHi);

        tvMessage.setText(message);
        imgIcon.setImageResource(idIcon);
        btnHihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                if (bye) finish();
            }
        });

        builder.setView(view);
        builder.setCancelable(false);
        mAlertDialog = builder.show();
    }

    private void initView() {
        buttonNo = findViewById(R.id.btn_no);
        buttonYes = findViewById(R.id.btn_yes);
        imgCuoi = findViewById(R.id.img_meu);
        fbAdmin = findViewById(R.id.tv_fb_admin);
        imgLove = findViewById(R.id.img_love);
    }

    private void move() {
        if (mIndex < 13) {
            mIndex += 1;
            if (mIndex == 11) {
                createDialog("Đã nghiện lại còn ngại !", R.drawable.ic_deu);
                buttonYes.setText("Bye em!");
                buttonNo.setVisibility(View.INVISIBLE);
                imgCuoi.setVisibility(View.VISIBLE);

                imgCuoi.setX(250);
                imgCuoi.setY(100);

                imgCuoi.setImageResource(R.drawable.ic_botay);
            }
        } else {
            mIndex = 1;
        }
        switch (mIndex) {
            case 0:
                reset();
                break;
            case 1:
                buttonNo.setY(buttonNo.getY() - 200);
                mX1 = buttonYes.getX();
                mX2 = buttonNo.getX();
                mY = buttonYes.getY();

                imgCuoi.setVisibility(View.VISIBLE);
                imgCuoi.setX(300);
                imgCuoi.setY(buttonNo.getY() - 100);
                imgCuoi.setImageResource(R.drawable.ic_meu1);
                break;
            case 2:
                imgCuoi.setVisibility(View.INVISIBLE);
                buttonNo.setY( buttonNo.getY() + 200);
                break;
            case 3:
                buttonYes.setX(mX2);
                buttonNo.setX(mX1);
                break;
            case 4:
                buttonYes.setX(mX1);
                buttonNo.setX(mX2);
                break;
            case 5:
                buttonNo.setX(100);
                buttonNo.setY(buttonNo.getY() + 200);

                imgCuoi.setVisibility(View.VISIBLE);
                imgCuoi.setX(125);
                imgCuoi.setY(buttonNo.getY() + 200);
                imgCuoi.setImageResource(R.drawable.ic_meu2);
                break;
            case 6:
                imgCuoi.setVisibility(View.INVISIBLE);
                buttonNo.setX(buttonNo.getX()-80);
                buttonNo.setY(buttonNo.getY() - 700);
                break;
            case 7:
                buttonNo.setX(buttonNo.getX()+200);
                buttonNo.setY(buttonNo.getY() + 900);
                break;
            case 8:
                buttonNo.setX(buttonNo.getX()+10);
                buttonNo.setY(buttonNo.getY() - 600);
                break;
            case 9:
                buttonNo.setX(buttonNo.getX()+100);
                buttonNo.setY(buttonNo.getY() - 300);
                break;
            case 10:
                buttonNo.setX(buttonNo.getX()-300);
                buttonNo.setY(buttonNo.getY() + 800);

                imgCuoi.setVisibility(View.VISIBLE);
                imgCuoi.setX(90);
                imgCuoi.setY(buttonNo.getY() + 200);
                imgCuoi.setImageResource(R.drawable.ic_meu3);
                break;
            case 11:
                imgCuoi.setVisibility(View.INVISIBLE);
                buttonNo.setX(buttonNo.getX()-100);
                buttonNo.setY(buttonNo.getY() + 100);
                break;
            default:
                break;
        }
    }
    private void reset() {
        buttonYes.setX(mX1);
        buttonYes.setY(mY);
        buttonNo.setX(mX2);
        buttonNo.setY(mY);
        buttonNo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDismiss() {
        if(mAlertDialog != null){
            mAlertDialog.dismiss();
        }
        mIndex = 0;
        reset();
    }
}
