package com.aboal3ta.recoder.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aboal3ta.recoder.Fragment.FileViewrFragment;
import com.aboal3ta.recoder.Fragment.RecordFragment;

public class Mytabadpater extends FragmentPagerAdapter {

    String [] titles={"Record","Saved Record"};

    public Mytabadpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new RecordFragment();
            case 1:
                return new FileViewrFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
