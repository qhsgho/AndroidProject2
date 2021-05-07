package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class week_fragement_Adapter extends FragmentStateAdapter {
    int mcount;
    public week_fragement_Adapter(@NonNull WeekViewFragment fragmentActivity,int count) {
        super(fragmentActivity);
        mcount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        int index = getRealPosition(position);
        switch (index){
            case 0:
                week_fragment_1 first = new week_fragment_1();
                return first;
            case 1:
                week_fragment_2 second = new week_fragment_2();
                return second;
            case 2:
                week_fragment_3 third = new week_fragment_3();
                return third;
            default:
                return null;
        }

    }

    private int getRealPosition(int position) {
        return position%mcount;
    }

    @Override
    public int getItemCount() {
        return 2000;
    }
}
