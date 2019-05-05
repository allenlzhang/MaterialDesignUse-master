package com.hebin.mduse.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hebin.mduse.R;
import com.hebin.mduse.ui.fragment.AnimationFragment;
import com.hebin.mduse.ui.fragment.BackHandledFragment;
import com.hebin.mduse.ui.fragment.ElevationFragment;
import com.hebin.mduse.ui.fragment.MainFragment;
import com.hebin.mduse.ui.fragment.RecycleViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BackHandledFragment.BackHandlerInterface {

    @BindView(R.id.toolbar_title)
    TextView             toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar              toolbar;
    @BindView(R.id.frame_content)
    FrameLayout          frameContent;
    @BindView(R.id.main_content)
    CoordinatorLayout    mainContent;
    @BindView(R.id.navigation_view)
    NavigationView       navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout         drawerLayout;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private BackHandledFragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);
        switchToMain();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_weapon_inc:
                        switchToMain();
                        navigationView.setCheckedItem(R.id.navigation_item_main);
                        break;
                    case R.id.bottom_people_inc:
                        switchToExample();
                        navigationView.setCheckedItem(R.id.navigation_item_tl);
                        break;
                    case R.id.bottom_sub_inc:
                        switchToBlog();
                        navigationView.setCheckedItem(R.id.navigation_item_snackbar);
                        break;
                    case R.id.bottom_things_inc:
                        switchToElevation();
                        navigationView.setCheckedItem(R.id.navigation_item_elevation);
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_item_main:
                                switchToMain();
                                bottomNavigationView.setSelectedItemId(R.id.bottom_weapon_inc);
                                break;
                            case R.id.navigation_item_tl:
                                switchToExample();
                                bottomNavigationView.setSelectedItemId(R.id.navigation_item_tl);
                                break;
                            case R.id.navigation_item_snackbar:
                                switchToBlog();
                                bottomNavigationView.setSelectedItemId(R.id.navigation_item_snackbar);
                                break;
                            case R.id.navigation_item_elevation:
                                switchToElevation();
                                bottomNavigationView.setSelectedItemId(R.id.navigation_item_elevation);
                                break;
                            case R.id.navigation_item_animation:
                                startActivity(new Intent(MainActivity.this, MDViewActivity.class));
                                break;
                            case R.id.navigation_item_dialog:
                                showDialog();
                                break;
                        }
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void showDialog() {


        new AlertDialog.Builder(this).setTitle("title")//设置对话框标题
                .setMessage("message")//设置显示的内容
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        //TODO Auto-generated method stub

                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        }).show();//在按键响应事件中显示此对话框
    }

    private void switchToMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        toolbarTitle.setText("主页");
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new AnimationFragment()).commit();
        toolbarTitle.setText("Animation");
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new RecycleViewFragment()).commit();
        toolbarTitle.setText("RecycleViewFragment");
    }

    private void switchToElevation() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ElevationFragment()).commit();
        toolbarTitle.setText("Elevation");
    }


    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }


    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment == null || !selectedFragment.onBackPressed()) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                doExitApp();
            }
        }
    }
}
