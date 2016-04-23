package com.drchip.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drchip.android.R;
import com.drchip.android.models.HomePageOptions;

import java.util.List;

/**
 * Created by mohann on 22-04-2016.
 */
public class HomePageAdapter extends BaseAdapter/*RecyclerView.Adapter<RecyclerView.ViewHolder>*/ {

    Context context;
    List<HomePageOptions> homePageOptionsList;
    LayoutInflater layoutInflater;

    public HomePageAdapter(Context context, List<HomePageOptions> homePageOptionsList) {
        this.context = context;
        this.homePageOptionsList = homePageOptionsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(null != homePageOptionsList){
            return homePageOptionsList.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.micro_app_card, parent, false);
            listViewHolder.appName = (TextView)convertView.findViewById(R.id.app_name);
            listViewHolder.appDesc = (TextView)convertView.findViewById(R.id.app_desc);
            listViewHolder.appIcon = (ImageView)convertView.findViewById(R.id.app_icon);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.appName.setText(webAppInfoList.get(position).getName());
        listViewHolder.appDesc.setText(webAppInfoList.get(position).getDescription());
        Glide.with(context.getApplicationContext()).load(webAppInfoList.get(position).getIcon()).centerCrop().placeholder(R.drawable.web_app_placeholder).into(listViewHolder.appIcon);
        return convertView;*/
        HomePageOptions homePageOptions = homePageOptionsList.get(position);
        ViewHolder homePageViewHolder;
        if(null == convertView){
            homePageViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.home_page_item_card, parent, false);
            homePageViewHolder.serviceName = (TextView) convertView.findViewById(R.id.service_name);

            convertView.setTag(homePageViewHolder);
        } else {
            homePageViewHolder = (ViewHolder)convertView.getTag();
        }
        homePageViewHolder.serviceName.setText(homePageOptions.getName());


        return convertView;
    }

    static class ViewHolder {
        TextView serviceName;
        ImageView iconUrl;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    /*List<HomePageOptions> homePageOptionsList = new ArrayList<HomePageOptions>();

    public HomePageAdapter(Context context, List<HomePageOptions> homePageOptionsList) {
        this.context = context;
        this.homePageOptionsList.clear();
        this.homePageOptionsList.addAll(homePageOptionsList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomePageCardView homePageCardView = new HomePageCardView(context, null);
        return new HomePageViewHolder(homePageCardView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(null != homePageOptionsList && !homePageOptionsList.isEmpty()){
            HomePageOptions homePageOptions = homePageOptionsList.get(position);
            if(null != homePageOptions){
                ((HomePageViewHolder)holder).homePageCardView.setHomePageCardView(homePageOptions);
            }
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class HomePageViewHolder extends RecyclerView.ViewHolder {
        public HomePageCardView homePageCardView;
        public HomePageViewHolder(HomePageCardView itemView) {
            super(itemView);
            homePageCardView = itemView;
        }
    }*/

}
