package com.jwplayer.opensourcedemo.videolist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.videolist.dummydata.DisplayableItem;
import com.jwplayer.opensourcedemo.videolist.dummydata.DummyContent;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;
import hugo.weaving.DebugLog;
import java.util.List;

public class VideoAdapterDelegate extends AbsAdapterDelegate<List<DisplayableItem>> {

    LayoutInflater inflater;

    public VideoAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        inflater = activity.getLayoutInflater();
    }
    @Override
    public boolean isForViewType(List<DisplayableItem> items, int position) {
        return items.get(position) instanceof DummyContent.VideoItem;
    }

    @DebugLog
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new VideoViewHolder(inflater.inflate(R.layout.videoitem_list_videoitem, parent, false));
    }

    @DebugLog
    @Override
    public void onBindViewHolder(@NonNull List<DisplayableItem> items, int position,
        @NonNull RecyclerView.ViewHolder holder) {
        final VideoViewHolder vh = (VideoViewHolder) holder;

        PlaylistItem pi = new PlaylistItem("http://vod.stream.vrt.be/mediazone_canvas/_definst_/smil:2016/01/mz-ast-0ae9ee02-9cae-48de-9a71-9ba5d1c7d29d/video.smil/playlist.m3u8");
        vh.playerView.load(pi);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        public final JWPlayerView playerView;

        public VideoViewHolder(View view) {
            super(view);
            playerView = (JWPlayerView) view.findViewById(R.id.playerView);
        }
    }
}
