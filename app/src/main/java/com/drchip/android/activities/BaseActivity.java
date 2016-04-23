package com.drchip.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.drchip.android.R;

public abstract class BaseActivity extends AppCompatActivity {

    int fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Sets the fragment container id.
     *
     * @param id the new fragment container id
     */
    void setFragmentContainerId(int id) {
        fragment_container = id;
    }

    /**
     * Finish and load fragment.
     *
     * @param fragment        the fragment
     * @param pushToBackstack the push to backstack
     * @param backstackName   the backstack name
     */
    public void finishAndLoadFragment(Fragment fragment, boolean pushToBackstack, String backstackName) {
        finishCurrentFragment();
        loadFragment(fragment, pushToBackstack, backstackName);
    }

    /**
     * Load fragment.
     *
     * @param fragment        the fragment
     * @param pushToBackstack the push to backstack
     * @param backstackName   the backstack name
     */
    public void loadFragment(Fragment fragment, boolean pushToBackstack, String backstackName) {
        loadFragmentWithTag(fragment, pushToBackstack, backstackName, null);
    }

    /**
     * Load fragment with tag.
     *
     * @param fragment        the fragment
     * @param pushToBackstack the push to backstack
     * @param backstackName   the backstack name
     * @param tag             the tag
     */
    public void loadFragmentWithTag(Fragment fragment, boolean pushToBackstack, String backstackName, String tag) {
        backstackName= fragment.getClass().getSimpleName();
        tag= fragment.getClass().getSimpleName();

        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        if(oldFragment!=null) {
            getSupportFragmentManager().beginTransaction().remove(oldFragment).commit();
        }

        try {
            if (fragment_container > 0) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
               // if (!(fragment instanceof NewHomePageFragment))
                    //ft.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit, R.anim.anim_pop_enter, R.anim.anim_pop_exit);

                if (pushToBackstack) {
                    ft.addToBackStack(backstackName);
                } else {
                    try {
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    ft.replace(fragment_container, fragment, tag);
                ft.commit();
//                getSupportFragmentManager().executePendingTransactions();
            } else {
                throw new RuntimeException("must call setFragmentContainerId() with id for container");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void loadFragmentWithTagForReplace(Fragment fragment, boolean pushToBackstack) {
        String backstackName= fragment.getClass().getSimpleName();
        String tag= fragment.getClass().getSimpleName();
        try {
            if (fragment_container > 0) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                getSupportFragmentManager().popBackStackImmediate(backstackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if (pushToBackstack) {
                    ft.addToBackStack(backstackName);
                } else {
                    try {
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ft.replace(fragment_container, fragment, tag);
                ft.commit();
//                getSupportFragmentManager().executePendingTransactions();
            } else {
                throw new RuntimeException("must call setFragmentContainerId() with id for container");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the fragment.
     *
     * @param fragment the fragment
     */
    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            ft.remove(fragment);
        }
        ft.commit();
        getSupportFragmentManager().popBackStack();
//        getSupportFragmentManager().executePendingTransactions();
    }


    /**
     * Finish current fragment.
     */
    public void finishCurrentFragment() {
        try {
            if (getSupportFragmentManager() != null) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
