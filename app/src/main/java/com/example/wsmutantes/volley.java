package com.example.wsmutantes;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

public class volley {

    public static final String URL ="http://hero.kramer.dev.br:3000";
    private static volley instancia;
    private static Context contexto;
    public static String usuario;
    private ImageLoader imageLoader;
    private RequestQueue filaRequest;

    private volley(Context ctx){
        contexto = ctx;
        filaRequest = getFilaRequest();
        imageLoader = new ImageLoader(filaRequest,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }

                }
        );
    }

    public static synchronized volley getInstancia(Context contexto){
        if(instancia == null){
            instancia = new volley(contexto);
        }
        return instancia;
    }

    public RequestQueue getFilaRequest() {
        if (filaRequest == null){
            Cache cache = new DiskBasedCache(contexto.getCacheDir(), 10*1024*1024)
            Network net = new BasicNetwork(new HurlStack());
            filaRequest = new RequestQueue(cache, net);
            filaRequest.start();
        }
        return filaRequest;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }


}
