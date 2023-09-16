package namtdph08817.android.apptruyentranh.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.apptruyentranh.fragment.AccountFragment;
import namtdph08817.android.apptruyentranh.fragment.HomeFragment;

public class adapterViewPager2 extends FragmentStateAdapter {

    public adapterViewPager2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = AccountFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
