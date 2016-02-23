package com.jwplayer.opensourcedemo.videolist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates.AdapterDelegatesManager;
import com.jwplayer.opensourcedemo.videolist.dummydata.DisplayableItem;
import java.util.List;

public class DelegatingAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<DisplayableItem>> delegatesManager;
    private List<DisplayableItem> items;

    public DelegatingAdapter(Activity activity, List<DisplayableItem> items) {
        this.items = items;

        // Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new DummyAdapterDelegate(activity, 0));
        delegatesManager.addDelegate(new VideoAdapterDelegate(activity, 1));
    }

    @Override public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
