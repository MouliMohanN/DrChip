package com.drchip.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.drchip.android.R;
import com.drchip.android.activities.BaseActivity;
import com.drchip.android.activities.HomeActivity;
import com.drchip.android.adapters.HomePageAdapter;
import com.drchip.android.constants.DrChipConstants;
import com.drchip.android.models.HomePageBundle;
import com.drchip.android.models.HomePageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohann on 22-04-2016.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    View rootView;
    Toolbar toolbar;
    TextView appUdateText;
    GridView homePageGridView;
    ImageView loadDrChipWebSite;
    HomePageAdapter homePageAdapter;
    BaseActivity baseActivity;
    boolean isAnimated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (HomeActivity)getActivity();
        isAnimated = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_page, container, false);
        homePageGridView = (GridView) rootView.findViewById(R.id.home_page_grid_view);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        getActionBar().show();
        setHasOptionsMenu(true);
        setupActionBar();
        loadDrChipWebSite = (ImageView) rootView.findViewById(R.id.drchip_load_website_icon);
        appUdateText = (TextView) rootView.findViewById(R.id.app_update_text);
        appUdateText.setSelected(true);
        final List<HomePageOptions> homePageOptionsList = getHomePageOptionsList();
        homePageAdapter = new HomePageAdapter(baseActivity, homePageOptionsList);
        homePageGridView.setAdapter(homePageAdapter);
        final DrChipConstants constants = new DrChipConstants();
        if(!isAnimated){
            isAnimated = true;
        } else {
            homePageGridView.setLayoutAnimation(null);
        }
        //loadDrChipWebSite.setOnClickListener(this);
        homePageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HomePageBundle homePageBundle = new HomePageBundle();
                HomePageOptions homePageOptions = homePageOptionsList.get(position);
                String serviceType = homePageOptions.getType();

                if(serviceType.equalsIgnoreCase(constants.COMPUTER_SERVICE)
                        || serviceType.equalsIgnoreCase(constants.LAPTOP_SERVICE)
                        || serviceType.equalsIgnoreCase(constants.CONTACT)){
                    // show enter phone number page
                    // place a visit
                    homePageBundle.setName(homePageOptions.getName());
                    homePageBundle.setType(constants.PLACE_A_VISIT);
                    homePageBundle.setShowOs(false);
                    loadSaveUserPhoneNumberFragment(homePageBundle);
                } else if (serviceType.equalsIgnoreCase(constants.ACCESSORIES)
                        || serviceType.equalsIgnoreCase(constants.OTHER)
                        || serviceType.equalsIgnoreCase(constants.DATA_RECOVERY)) {
                    // show enter phone number page
                    // enquiry
                    homePageBundle.setName(homePageOptions.getName());
                    homePageBundle.setType(constants.ENQUIRY);
                    homePageBundle.setShowOs(false);
                    loadSaveUserPhoneNumberFragment(homePageBundle);
                } else if(serviceType.equalsIgnoreCase(constants.ONLINE_TROUBLSHOOTING)){
                    homePageBundle.setName(homePageOptions.getName());
                    homePageBundle.setType(constants.PLACE_A_VISIT);
                    homePageBundle.setShowOs(true);
                    loadSaveUserPhoneNumberFragment(homePageBundle);
                } else {
                    Toast.makeText(baseActivity, serviceType + ", feature is comming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void setupActionBar() {
        super.setupActionBar();
        setTitle("DrChip");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<HomePageOptions> getHomePageOptionsList(){
        List<HomePageOptions> homePageOptionsList = new ArrayList<HomePageOptions>();

        DrChipConstants constants = new DrChipConstants();

        //option 1
        HomePageOptions homePageOptions1 = new HomePageOptions();
        homePageOptions1.setName(getString(R.string.computer_service));
        homePageOptions1.setType(constants.COMPUTER_SERVICE);
        homePageOptions1.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.computer_service));


        // option 2
        HomePageOptions homePageOptions2 = new HomePageOptions();
        homePageOptions2.setName(getString(R.string.laptop_service));
        homePageOptions2.setType(constants.LAPTOP_SERVICE);
        homePageOptions2.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.laptop_service));

        // option 3
        HomePageOptions homePageOptions3 = new HomePageOptions();
        homePageOptions3.setName(getString(R.string.accessories));
        homePageOptions3.setType(constants.ACCESSORIES);
        homePageOptions3.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.accessories));

        // option 4
        HomePageOptions homePageOptions4 = new HomePageOptions();
        homePageOptions4.setName(getString(R.string.other));
        homePageOptions4.setType(constants.OTHER);
        homePageOptions4.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.others));

        // option 5
        HomePageOptions homePageOptions5 = new HomePageOptions();
        homePageOptions5.setName(getString(R.string.data_recovery));
        homePageOptions5.setType(constants.DATA_RECOVERY);
        homePageOptions5.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.data_recovery));

        // option 6
        HomePageOptions homePageOptions6 = new HomePageOptions();
        homePageOptions6.setName(getString(R.string.online_troubleshooting));
        homePageOptions6.setType(constants.ONLINE_TROUBLSHOOTING);
        homePageOptions6.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.online_trouble_shoot));

        // option 7
        HomePageOptions homePageOptions7 = new HomePageOptions();
        homePageOptions7.setName(getString(R.string.offers));
        homePageOptions7.setType(constants.OFFERS);
        homePageOptions7.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.offers));

        // option 8
        HomePageOptions homePageOptions8 = new HomePageOptions();
        homePageOptions8.setName(getString(R.string.contact));
        homePageOptions8.setType(constants.CONTACT);
        homePageOptions8.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.contact_us));

        // option 9
        HomePageOptions homePageOptions9 = new HomePageOptions();
        homePageOptions9.setName(getString(R.string.chat));
        homePageOptions9.setType(constants.CHAT);
        homePageOptions9.setIconUrl(baseActivity.getResources().getDrawable(R.mipmap.chat));

        homePageOptionsList.add(homePageOptions1);
        homePageOptionsList.add(homePageOptions2);
        homePageOptionsList.add(homePageOptions3);
        homePageOptionsList.add(homePageOptions4);
        homePageOptionsList.add(homePageOptions5);
        homePageOptionsList.add(homePageOptions6);
        homePageOptionsList.add(homePageOptions7);
        homePageOptionsList.add(homePageOptions8);
        homePageOptionsList.add(homePageOptions9);

        return homePageOptionsList;
    }

    private void loadSaveUserPhoneNumberFragment(HomePageBundle homePageBundle){

        Bundle bundle = new Bundle();
        bundle.putParcelable("homePageBundle", homePageBundle);
        SaveUserPhoneNumber saveUserPhoneNumber = new SaveUserPhoneNumber();
        saveUserPhoneNumber.setArguments(bundle);
        baseActivity.loadFragment(saveUserPhoneNumber, true, saveUserPhoneNumber.getClass().getSimpleName());
    }

}
