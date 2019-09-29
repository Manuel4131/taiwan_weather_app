//package com.alston.cuteweatherapp.network;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import org.json.JSONException;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class CustomResponseInterceptor implements Interceptor {
//
//    private static String newToken;
//    private String bodyString;
//
//    private final String TAG = getClass().getSimpleName();
//
//    @Override
//    public Response intercept(@NonNull Chain chain) throws IOException {
//        Request request = chain.request();
//        Response response = chain.proceed(request);
//        if (response.code() != 200) {
//            Response r = null;
//            try {
//                r = makeTokenRefreshCall(request, chain);
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return r;
//        }
//        Log.d(TAG, "INTERCEPTED:$ " response.toString());
//        return response;
//    }
//
//    private Response makeTokenRefreshCall(Request req, Chain chain) throws JSONException, IOException {
//        Log.d(TAG, "Retrying new request");        /* fetch refreshed token, some synchronous API call, whatever */
//        String newToken = fetchToken();        /* make a new request which is same as the original one, except that its headers now contain a refreshed token */                       Request newRequest;
//        newRequest = req.newBuilder().header("Authorization", " Token " + newToken).build();
//        Response another =  chain.proceed(newRequest);
//        while (another.code() != 200) {
//            makeTokenRefreshCall(newRequest, chain);
//        }
//        return another;
//    }
//
//}
