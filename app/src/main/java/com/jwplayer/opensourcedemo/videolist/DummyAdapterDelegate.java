package com.jwplayer.opensourcedemo.videolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.videolist.dummydata.DisplayableItem;
import com.jwplayer.opensourcedemo.videolist.dummydata.DummyContent;
import java.util.List;

public class DummyAdapterDelegate extends AbsAdapterDelegate<List<DisplayableItem>> {

    LayoutInflater inflater;

    public DummyAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(List<DisplayableItem> items, int position) {
        return items.get(position) instanceof DummyContent.DummyItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new DummyViewHolder(
            inflater.inflate(R.layout.videoitem_list_dummyitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<DisplayableItem> items, int position,
        @NonNull final RecyclerView.ViewHolder holder) {
        final DummyViewHolder vh = (DummyViewHolder) holder;
        DummyContent.DummyItem item = (DummyContent.DummyItem) items.get(position);

        vh.item = item;
        vh.idView.setText(item.id);
        vh.contentView.setText(item.content);

        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, VideoItemDetailActivity.class);
                intent.putExtra(VideoItemDetailFragment.ARG_ITEM_ID, vh.item.id);

                context.startActivity(intent);
            }
        });
    }

    static class DummyViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView idView;
        public final TextView contentView;
        public DummyContent.DummyItem item;

        public DummyViewHolder(View view) {
            super(view);
            this.view = view;
            idView = (TextView) view.findViewById(R.id.id);
            contentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
