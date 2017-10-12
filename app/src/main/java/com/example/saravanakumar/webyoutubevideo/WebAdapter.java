package com.example.saravanakumar.webyoutubevideo;

/**
 * Created by saravanakumar on 04/09/17.
 */

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebAdapter extends RecyclerView.Adapter<WebAdapter.ViewHolder> {
    private Context activity;
    private String video;

    public WebAdapter(Context activity, String video) {
        this.video = video;
        this.activity = activity;
    }

    @Override
    public WebAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_web, viewGroup, false);
        return new WebAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WebAdapter.ViewHolder viewHolder, final int position) {
        if(video != null && video.length()>0) {
            try {
                viewHolder.webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                //getting the web settings
                WebSettings webSettings = viewHolder.webView.getSettings();

                webSettings.setJavaScriptEnabled(true);
                String frameVideo = getVideoId(video);
                webSettings.setPluginState(WebSettings.PluginState.ON);
                viewHolder.webView.setWebChromeClient(new WebChromeClient());
                if (frameVideo != null) {
                    viewHolder.webView.setVisibility(View.VISIBLE);
                    viewHolder.webView.loadUrl("https://www.youtube.com/embed/" + frameVideo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            viewHolder.webView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return video.length();
    }

    //method to get videoID
    public String getVideoId(String videoId) throws Exception {
        String pattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(videoId);
        int youtu = videoId.indexOf("youtu");
        if (m.matches() && youtu != -1) {
            int ytu = videoId.indexOf("http://youtu.be/");
            if (ytu != -1) {
                String[] split = videoId.split(".be/");
                return split[1];
            }
            URL youtube = new URL(videoId);
            String[] split = youtube.getQuery().split("=");
            int query = split[1].indexOf("&");
            if (query != -1) {
                String[] nSplit = split[1].split("&");
                return nSplit[0];
            } else return split[1];
        }
        return null; //throw something or return what you want
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private WebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            webView = (WebView) itemView.findViewById(R.id.webView);
        }
    }
}
