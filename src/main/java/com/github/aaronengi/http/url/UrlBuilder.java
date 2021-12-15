package com.github.aaronengi.http.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class UrlBuilder {
    private final String base;
    @Nullable
    private final Map<String, String> queries;

    public UrlBuilder(String base, Map<String, String> queries) {
        this.base = base;
        this.queries = queries;
    }

    public String build() {
        List<String> items = new ArrayList<>();
        if (queries != null) {
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                try {
                    items.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (items.isEmpty()) {
            return base;
        } else {
            return base + "?" + String.join("&", items);
        }
    }
}
