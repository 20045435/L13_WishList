package sg.edu.rp.c346.id20045435.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<List> wishList;

    public CustomAdapter (Context context, int resource, ArrayList<List> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        wishList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent,false);

        TextView tvWish = rowView.findViewById(R.id.tvWish);
        RatingBar rb = rowView.findViewById(R.id.rating);
        tvWish.setText(wishList.get(position).getWish());
        rb.setRating(wishList.get(position).getWantRating());

        return rowView;
    }
}
