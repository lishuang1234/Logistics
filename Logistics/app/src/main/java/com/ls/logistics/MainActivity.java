package com.ls.logistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ls.adapter.PagerAdapter;
import com.ls.bean.SoftUser;
import com.ls.fragment.ContentFragment;
import com.ls.util.Constant;
import com.ls.util.UserDBUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.toolbar)
    private Toolbar mToolbar;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    @ViewInject(R.id.drawer_layout)
    private DrawerLayout mDrawerLayout;
    @ViewInject(R.id.nav_view)
    private NavigationView mNavigationView;
    private PagerAdapter mPagerAdapter;
    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;
    private TextView mUserNameTextView;
    private UserDBUtils mUserDBUtils;
    private SoftUser mSoftUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        mUserDBUtils = new UserDBUtils(this);
        mSoftUser = mUserDBUtils.getSoftUser();
        initToolbar();
        initNavgaView();
        initViewPager();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private void initViewPager() {
        //String[] titles = new String[]{"更新信息", "查询信息", "数据统计"};
        List<Fragment> fragmentList = new ArrayList<>();
        for (String title : Constant.titles) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            fragment.setArguments(bundle);
            fragment.setListTitle(Constant.getListTitle(title));
            fragmentList.add(fragment);
        }
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initNavgaView() {
        mUserNameTextView = (TextView) mNavigationView.findViewById(R.id.username);
        mUserNameTextView.setText(mSoftUser.getRealName() + "，欢迎您");
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        mUserDBUtils.clearSoftUser();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.finish();
                        break;
                    case R.id.nav_edit_password:
                        editPassword();
                        break;
                    case R.id.nav_daohang:
                        startActivity(new Intent(MainActivity.this, DaoHangActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    private void editPassword() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog,
                (ViewGroup) findViewById(R.id.dialog));
        final String newPassword = ((EditText) layout.findViewById(R.id.dialog_new)).getText().toString();
        final String oldPassword = ((EditText) layout.findViewById(R.id.dialog_old)).getText().toString();
        new AlertDialog.Builder(this).setTitle("请修改密码").setView(
                layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (oldPassword.equals("") || !mSoftUser.getUserPassword().equals(oldPassword)) {
                    showToast("密码修改失败");
                    return;
                }
                mSoftUser.setUserPassword(newPassword);
                mUserDBUtils.saveUser(mSoftUser);
                showToast("密码修改成功");
            }
        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
