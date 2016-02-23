package com.jwplayer.opensourcedemo.videolist.dummydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DisplayableItem> ITEMS = new ArrayList<DisplayableItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DisplayableItem> ITEM_MAP = new HashMap<String, DisplayableItem>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        int j = 1;
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }

        j = j + COUNT;
        addVideoItem(createVideoItem(j));

        for (int i = j; i <= j+COUNT; i++) {
            addItem(createDummyItem(i));
        }

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static void addVideoItem(VideoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static VideoItem createVideoItem(int position) {
        return new VideoItem(String.valueOf(position), "http://dummy-url.com");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem implements DisplayableItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static class VideoItem implements DisplayableItem {

        private final String id;
        private final String url;

        public VideoItem(String id, String url) {
            this.id = id;
            this.url = url;
        }

        @Override
        public String toString() {
            return url;
        }
    }
}
