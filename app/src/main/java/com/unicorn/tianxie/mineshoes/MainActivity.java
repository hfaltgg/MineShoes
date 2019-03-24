package com.unicorn.tianxie.mineshoes;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unicorn.tianxie.mineshoes.Cart.CartFragment;
import com.unicorn.tianxie.mineshoes.Departments.DepartmentFragment;
import com.unicorn.tianxie.mineshoes.Favorites.FavoritesFragment;
import com.unicorn.tianxie.mineshoes.Home.HomeFragment;
import com.unicorn.tianxie.mineshoes.MyAccount.MyAccountFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_fl_fragment) FrameLayout flFragment;
    @BindView(R.id.icon_home) ImageView mHomeImageView;
    @BindView(R.id.txt_home) TextView mHomeTextView;
    @BindView(R.id.ll_tab_home) ConstraintLayout mHomeTab;
    @BindView(R.id.icon_myaccount) ImageView mMyaccountImageView;
    @BindView(R.id.txt_myaccount) TextView mMyaccountTextView;
    @BindView(R.id.ll_tab_myaccount) ConstraintLayout mMyaccountTab;
    @BindView(R.id.icon_favorites) ImageView mFavoritesImageView;
    @BindView(R.id.txt_favorites) TextView mFavoritesTextView;
    @BindView(R.id.ll_tab_favorites) ConstraintLayout mFavoritesTab;
    @BindView(R.id.icon_department) ImageView mDepartmentImageView;
    @BindView(R.id.txt_department) TextView mDepartmentTextView;
    @BindView(R.id.ll_tab_department) ConstraintLayout mDepartmentTab;
    @BindView(R.id.icon_cart) ImageView mCartImageView;
    @BindView(R.id.txt_cart) TextView mCartTextView;
    @BindView(R.id.ll_tab_cart) ConstraintLayout mCartTab;
    Unbinder unbinder;
    private Fragment currentFragment = null;
    private List<ImageView> mImageViewlist;
    private List<TextView> mTextViewlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        inint();
    }

    private void inint() {
        mImageViewlist = new ArrayList<>();
        mTextViewlist = new ArrayList<>();

        mImageViewlist.add(mHomeImageView);
        mImageViewlist.add(mMyaccountImageView);
        mImageViewlist.add(mDepartmentImageView);
        mImageViewlist.add(mFavoritesImageView);
        mImageViewlist.add(mCartImageView);

        mTextViewlist.add(mHomeTextView);
        mTextViewlist.add(mMyaccountTextView);
        mTextViewlist.add(mDepartmentTextView);
        mTextViewlist.add(mFavoritesTextView);
        mTextViewlist.add(mCartTextView);
        changePageSelect(0);
        changePageFragment(R.id.ll_tab_home);
    }

    @OnClick({R.id.ll_tab_home, R.id.icon_home, R.id.ll_tab_myaccount, R.id.icon_myaccount
            , R.id.ll_tab_department , R.id.icon_department , R.id.ll_tab_favorites
            , R.id.icon_favorites, R.id.ll_tab_cart, R.id.icon_cart})
    public void onViewClicked(View view) {
        changePageFragment(view.getId());
    }

    /**
     * 选中的tab 和 没有选中的tab 的图标和字体颜色
     *
     * @param index
     */
    public void changePageSelect(int index) {
        for (int i = 0; i < mImageViewlist.size(); i++) {
            if (index == i) {
                mImageViewlist.get(i).setEnabled(false);
                mImageViewlist.get(i).setColorFilter(this.getResources().getColor(R.color.colorBlue));
                mTextViewlist.get(i).setTextColor(getResources().getColor(R.color.colorBlue));
            } else {
                mImageViewlist.get(i).setEnabled(true);
                mImageViewlist.get(i).setColorFilter(this.getResources().getColor(R.color.colorGrey));
                mTextViewlist.get(i).setTextColor(getResources().getColor(R.color.colorGrey));
            }
        }
    }

    public void changePageFragment(int id) {
        switch (id) {
            case R.id.ll_tab_home:
            case R.id.icon_home:
                changePageSelect(0);
                switchFragment(currentFragment, HomeFragment.newInstance());
                Log.d("click","ll_tab_home");
                break;

            case R.id.ll_tab_myaccount:
            case R.id.icon_myaccount:
                changePageSelect(1);
                switchFragment(currentFragment, MyAccountFragment.newInstance());
                Log.d("click","ll_tab_myaccount");
                break;

            case R.id.ll_tab_department:
            case R.id.icon_department:
                changePageSelect(2);
                switchFragment(currentFragment, DepartmentFragment.newInstance());
                Log.d("click","ll_tab_favorites");
                break;

            case R.id.ll_tab_favorites:
            case R.id.icon_favorites:
                changePageSelect(3);
                switchFragment(currentFragment, FavoritesFragment.newInstance());
                Log.d("click","ll_tab_department");
                break;

            case R.id.ll_tab_cart:
            case R.id.icon_cart:
                changePageSelect(4);
                switchFragment(currentFragment, CartFragment.newInstance());
                Log.d("click","ll_tab_cart");
                break;
        }
    }

    public void switchFragment(Fragment from, Fragment to) {
        if (to == null)
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (from == null) {
                transaction.add(R.id.main_fl_fragment, to).show(to).commit();
            } else {
                transaction.hide(from).add(R.id.main_fl_fragment, to).commitAllowingStateLoss();
            }
        } else {
            transaction.hide(from).show(to).commit();
        }
        currentFragment = to;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
