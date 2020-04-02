package com.bione.corona.network;


import com.bione.corona.model.CommonResponse;
import com.bione.corona.model.getCoronaResults.Example;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


/**
 * Developer: Bione
 *
 * The API interface for your application
 */
public interface ApiInterface {

    // todo add these params to your API key constant file and remove them from here

    String AUTHORIZATION = "authorization";
    String CONTENT_LANG = "content-language";
    String LIMIT = "limit";
    String SKIP = "skip";

    //todo Declare your API endpoints here

    String GET_NOTIFICATIONS = "/driver/getNotifications";

    /**
     * Dummy sign in endpoint
     *
     * @param map the map of params to go along with reqquest
     * @return parsed common response object
     */

//    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/rest/V1/bioneapi-customersendotp/sendotp")
    Call<CommonResponse> sendOtp(@QueryMap Map<String, String> map);


    //    @Headers({"Content-Type: application/json; charset=utf-8"})
    @GET("/rest/V1/bioneapi-customersendotp/verifyotp")
    Call<CommonResponse> verifyOtp(@QueryMap Map<String, String> map);


    @GET("/api/unknown")
    Call<CommonResponse> doGetListResources();

    @GET("/data.json")
    Call<Example> getCoronaResults();



    /**
     * OTP verification
     *
     * @param map the map of params to go along with reqquest
     * @return parsed common response object
     */
    @FormUrlEncoded
    @POST("/signIn")
    Call<CommonResponse> signIn(@FieldMap Map<String, String> map);

//    /**
//     * * Fetch notifications data from server
//     *
//     * @param authorization auth
//     * @param contentlang   en
//     * @param limit         limit
//     * @param skip          skip
//     * @return parsed notifications response object
//     */
//    @GET(GET_NOTIFICATIONS)
//    Call<NotificationsResponse> getNotifications(@Header(AUTHORIZATION) String authorization,
//                                                 @Header(CONTENT_LANG) String contentlang,
//                                                 @Query(LIMIT) int limit,
//                                                 @Query(SKIP) int skip);
}
