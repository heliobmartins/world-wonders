package br.com.ciandt.helio.worldwonders.ui.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.ciandt.helio.worldwonders.ui.activity.R;
import br.com.ciandt.helio.worldwonders.entity.Wonder;

import java.util.List;

public class FeedBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<Wonder> mWonderList;

    public FeedBaseAdapter(final Context context, final List<Wonder> wonderList) {
        mContext = context;
        mWonderList = wonderList;
    }

    @Override
    public int getCount() {
        return mWonderList.size(); //TODO
    }

    @Override
    public Wonder getItem(int i) {
        return mWonderList.get(i); //TODO
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // TODO

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.feed_item, viewGroup, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Wonder wonder = getItem(position);

        viewHolder.textViewName.setText(wonder.name);
        viewHolder.textViewCountry.setText(wonder.country);
        viewHolder.prgBarViewWorldWonder.setIndeterminate(true);
        viewHolder.prgBarViewWorldWonder.setVisibility(View.VISIBLE);

        return convertView;
    }

    private class ViewHolder {

        TextView textViewName;
        ImageView imageViewLike;
        TextView textViewCountry;
        ImageView imageViewWorldWonder;
        ProgressBar prgBarViewWorldWonder;

        private ViewHolder(View convertView) {
            textViewName = (TextView) convertView.findViewById(R.id.text_view_name);
            imageViewLike = (ImageView) convertView.findViewById(R.id.image_view_world_wonder);
            textViewCountry = (TextView) convertView.findViewById(R.id.text_view_country);
            //imageViewWorldWonder = (ImageView) convertView.findViewById(R.id.text_view_name);
            prgBarViewWorldWonder = (ProgressBar) convertView.findViewById(R.id.prg_bar_world_wonder);
        }

    }
}
