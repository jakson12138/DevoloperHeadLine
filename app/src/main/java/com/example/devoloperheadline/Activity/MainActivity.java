package com.example.devoloperheadline.Activity;

import android.content.ContentValues;
import android.os.Build;
import android.sax.RootElement;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.devoloperheadline.Fragment.ContentFragment;
import com.example.devoloperheadline.Fragment.GiftFragment;
import com.example.devoloperheadline.Fragment.MainFragment;
import com.example.devoloperheadline.Fragment.ShareFragment;
import com.example.devoloperheadline.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RelativeLayout rlHome,rlGift,rlShare;
    private int currentSelectItem = R.id.rl_home;
    private MainFragment mainFragment;
    private ShareFragment shareFragment;
    private GiftFragment giftFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private MainDrawerListener drawerListener = new MainDrawerListener() {
        @Override
        public void open() {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    };

    private void initView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //findViewById(R.id.iv_menu).setOnClickListener(clickListener);

        initLeftMenu();

        mainFragment = MainFragment.newInstance(drawerListener);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame,mainFragment).commit();

        setWindowStatus();
    }

    private void initLeftMenu(){
        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlGift = (RelativeLayout) findViewById(R.id.rl_gift);
        rlShare = (RelativeLayout) findViewById(R.id.rl_share);

        rlHome.setOnClickListener(onLeftMenuClickListener);
        rlGift.setOnClickListener(onLeftMenuClickListener);
        rlShare.setOnClickListener(onLeftMenuClickListener);

        rlHome.setSelected(true);
    }

    private View.OnClickListener onLeftMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(currentSelectItem != v.getId()){
                currentSelectItem = v.getId();
                noItemSelect();
                changeFragment(v.getId());

                switch(v.getId()){
                    case R.id.rl_home:
                        rlHome.setSelected(true);
                        break;
                    case R.id.rl_gift:
                        rlGift.setSelected(true);
                        break;
                    case R.id.rl_share:
                        rlShare.setSelected(true);
                        break;
                }
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    };

    private void changeFragment(int resId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        hideFragment(transaction);
        if(resId == R.id.rl_home){
            if(mainFragment == null){
                mainFragment = MainFragment.newInstance(drawerListener);
                transaction.add(R.id.content_frame,mainFragment);
            }
            else{
                transaction.show(mainFragment);
            }
        }
        else if(resId == R.id.rl_share){
            if(shareFragment == null){
                shareFragment = ShareFragment.newInstance(drawerListener);
                transaction.add(R.id.content_frame,shareFragment);
            }
            else{
                transaction.show(shareFragment);
            }
        }
        else if(resId == R.id.rl_gift){
            if(giftFragment == null){
                giftFragment = GiftFragment.newInstance(drawerListener);
                transaction.add(R.id.content_frame,giftFragment);
            }
            else{
                transaction.show(giftFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(mainFragment != null){
            transaction.hide(mainFragment);
        }
        if(shareFragment != null){
            transaction.hide(shareFragment);
        }
        if(giftFragment != null){
            transaction.hide(giftFragment);
        }
    }

    private void noItemSelect(){
        rlHome.setSelected(false);
        rlGift.setSelected(false);
        rlShare.setSelected(false);
    }

    /*
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.iv_menu:
                    drawerLayout.openDrawer(Gravity.LEFT);
                    break;
            }
        }
    };
    */

    private void setWindowStatus(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置状态栏颜色
            getWindow().setBackgroundDrawableResource(R.color.main_color);
        }
    }

    public interface MainDrawerListener{
        public void open();
    }
}
