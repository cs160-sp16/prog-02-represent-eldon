package io.eldon.representapp;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by eldon on 3/1/2016.
 * Translates from CongressPerson to a ViewHolder
 */
public class CongressPersonAdapter extends RecyclerView.Adapter<CongressPersonAdapter.CongressPersonViewHolder>{
    List<CongressPerson> persons;

    public static class CongressPersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personWebsite;
        TextView personEmail;
        TextView personParty;
        TextView personLastTweet;
        ImageView personPhoto;

        CongressPersonViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.congressperson_cardview);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personWebsite = (TextView)itemView.findViewById(R.id.person_website);
            personEmail = (TextView)itemView.findViewById(R.id.person_email);
            //personLastTweet = (TextView)itemView.findViewById(R.id.person_last_tweet);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            personParty = (TextView)itemView.findViewById(R.id.person_party);

        }
    }

    CongressPersonAdapter(List<CongressPerson> persons){
        this.persons = persons;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public CongressPersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.congressperson_card, viewGroup, false);
        return new CongressPersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CongressPersonViewHolder congressPersonViewHolder, int i) {
        final int j = i; // to avoid a stupid java error
        congressPersonViewHolder.personName.setText(persons.get(i).getName());
        congressPersonViewHolder.personWebsite.setText(persons.get(i).getWebsite());
        congressPersonViewHolder.personEmail.setText(persons.get(i).getEmail());
        congressPersonViewHolder.personParty.setText(persons.get(i).getParty());
        //congressPersonViewHolder.personLastTweet.setText(persons.get(i).getTruncatedLastTweet());
        //congressPersonViewHolder.personPhoto.setImageResource(persons.get(i).getPhotoID());
        Picasso.with(congressPersonViewHolder.cv.getContext()).load(
                "https://theunitedstates.io/images/congress/225x275/"
                        + persons.get(i).getBioguideID()
                        + ".jpg").into(congressPersonViewHolder.personPhoto
        );
        congressPersonViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getDetailIntent = new Intent(v.getContext(), DetailActivity.class);
                getDetailIntent.putExtra("congressperson", persons.get(j));
                v.getContext().startActivity(getDetailIntent);
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}