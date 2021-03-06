package id.co.ifest.marjan.spotevent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class SponsorshipAdapter extends RecyclerView.Adapter<SponsorshipAdapter.ViewHolder>{
    private ArrayList<Sponsor> sponsors;
    private Context context;

    public SponsorshipAdapter(Context context, ArrayList<Sponsor> sponsors) {
        this.context = context;
        this.sponsors = sponsors;
    }

    @Override
    public SponsorshipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsorship_row_layout, parent, false);
        return new SponsorshipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.sponsorDesc.setText(sponsors.get(position).getDesc());
        holder.sponsorName.setText(sponsors.get(position).getName());
        holder.sponsorMoney.setText(sponsors.get(position).getMoney());
        holder.sponsorTime.setText(sponsors.get(position).getTime());
        Picasso.with(context).load(sponsors.get(position).getImageUrl()).resize(500, 300).into(holder.sponsorImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SponsorReadSingleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sponsorObject", sponsors.get(position));
                context.startActivity(intent);
            }
        });
    }

//    private void fragmentJump(Sponsor sponsorSelected){
//        SponsorReadSingleFragment mFragment = new SponsorReadSingleFragment();
//        Bundle mBundle = new Bundle();
//        mBundle.putParcelable("item_selected_key", (Parcelable) sponsorSelected);
//        mFragment.setArguments(mBundle);
//        switchContent(R.id.sponsor_layout, mFragment);
//    }

//    public void switchContent(int id, Fragment fragment){
//        if(context == null)
//            return;
//        if(context instanceof SponsorshipUpcomingEventActivity){
//            SponsorshipUpcomingEventActivity sponsorshipActivity = (SponsorshipUpcomingEventActivity) context;
//            Fragment fragment1 = fragment;
//            SponsorshipUpcomingEventActivity.switchContent(id, fragment1);
//        }
//    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView sponsorImage;
        public TextView sponsorName;
        public TextView sponsorDesc;
        public TextView sponsorMoney;
        public TextView sponsorTime;

        public ViewHolder(View itemView) {
            super(itemView);

            sponsorName = (TextView)itemView.findViewById(R.id.tv_sponsor_name);
            sponsorImage = (ImageView)itemView.findViewById(R.id.img_sponsor);
            sponsorDesc = (TextView)itemView.findViewById(R.id.tv_sponsor_desc);
            sponsorMoney = (TextView)itemView.findViewById(R.id.tv_sponsor_money);
            sponsorTime = (TextView)itemView.findViewById(R.id.tv_sponsor_time);
        }
    }
}
