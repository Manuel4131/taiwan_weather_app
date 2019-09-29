//package com.alston.cuteweatherapp.log;
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//
//import okhttp3.ResponseBody;
//import retrofit2.HttpException;
//
//public class RetrofitErrorHandling {
//
//    public RetrofitErrorHandling(Throwable t) {
//        if (t instanceof HttpException) {
//            ResponseBody body = ((HttpException) t).response().errorBody();
//        }
//        Gson gson = new Gson();
//        TypeAdapter<ErrorParser> adapter = gson.getAdapter
//                (ErrorParser
//                        .class);
//        try {
//            ErrorParser errorParser =
//                    adapter.fromJson(body.string());
//
//            Logger.i(TAG, "Error:" + errorParser.getError());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
