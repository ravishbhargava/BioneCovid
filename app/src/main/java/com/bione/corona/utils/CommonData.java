package com.bione.corona.utils;


import com.bione.corona.model.CommonResponse;

import java.util.Map;

import io.paperdb.Paper;

/**
 * Created by cl-macmini-84 on 22/11/17.
 */

public final class CommonData {

    private static final String PAPER_ACCESS_TOKEN = "paper_access_token";
    private static final String PAPER_DEVICE_TOKEN = "paper_device_token";
    private static final String PAPER_USER_DATA = "paper_user_data";
    private static final String PAPER_CONSTANT_DATA = "paper_constant_data";
    private static final String PAPER_NOTIFICATION_HAS_BEEN_DISPLAYED = "paper_notification_displayed";
    private static Map<String, String> pushData = null;

    private static final String PAPER_USER_NAME = "paper_user_name";
    private static final String PAPER_EMAIL = "paper_emaile";
    private static final String PAPER_PASSWORD = "paper_passworde";


    //App flyer event keys
    private static final String PAPER_IS_COMPLETE_PROFILE_EVENT_TRIGGERED = "PAPER_IS_COMPLETE_PROFILE_EVENT_TRIGGERED";


    /**
     * Empty Constructor
     * not called
     */
    private CommonData() {
    }


    //======================================== Access Token ============================================

    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
    public static void saveAccessToken(final String accessToken) {
        Paper.book().write(PAPER_ACCESS_TOKEN, "Bearer " + accessToken);
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public static String getAccessToken() {
        return Paper.book().read(PAPER_ACCESS_TOKEN);
    }


    public static void saveUserName(final String userName) {
        Paper.book().write(PAPER_USER_NAME, userName);
    }

    public static String getUserName() {
        return Paper.book().read(PAPER_USER_NAME);
    }

    public static void saveEmail(final String email) {
        Paper.book().write(PAPER_EMAIL, email);
    }

    public static String getEmail() {
        return Paper.book().read(PAPER_EMAIL);
    }

    public static void savePasswordl(final String password) {
        Paper.book().write(PAPER_PASSWORD, password);
    }

    public static String getPassword() {
        return Paper.book().read(PAPER_PASSWORD);
    }

    //======================================== FCM token ==============================================

    /**
     * Update fcm token.
     *
     * @param token the token
     */
    public static void updateFCMToken(final String token) {
        Paper.book().write(PAPER_DEVICE_TOKEN, token);
    }

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    public static String getFCMToken() {
        return Paper.book().read(PAPER_DEVICE_TOKEN);
    }


    /**
     * Save user data.
     *
     * @param mUserData         the m user data
     * @param isStudentDataSave is studentd data need to be save
     */
    public static void saveUserData(final CommonResponse mUserData) {
        if (mUserData == null) {
            return;
        }
//        if (mUserData.getAccessToken() != null
//                && !mUserData.getAccessToken().isEmpty()) {
//            saveAccessToken(mUserData.getAccessToken());
//        }


        Paper.book().write(PAPER_USER_DATA, mUserData);
    }

    /**
     * Get user data user data.
     *
     * @return the user data
     */
    public static CommonResponse getUserData() {
        return Paper.book().read(PAPER_USER_DATA);
    }


    /**
     * Method to get push data
     *
     * @return pushData
     */
    public static Map<String, String> getPushData() {
        return pushData;
    }

    public static boolean getNotificationPushHasBeenDisplayed() {


        return Paper.book().read(PAPER_NOTIFICATION_HAS_BEEN_DISPLAYED, false);
    }

    /**
     * @param hasNotificationDisplayed has notification has been displayed
     */

    public static void setNotificationPushHasBeenDisplayed(final boolean hasNotificationDisplayed) {
        Paper.book().write(PAPER_NOTIFICATION_HAS_BEEN_DISPLAYED, hasNotificationDisplayed);
    }


    /**
     * Method to set push data
     *
     * @param pushData push data
     */
    public static void setPushData(final Map<String, String> pushData) {
        if (pushData == null) {
            return;
        }
        CommonData.pushData = pushData;
    }


    /**
     * Clear data.
     */
    public static void clearData() {
        String fcmToken = getFCMToken();
        Paper.book().destroy();
        updateFCMToken(fcmToken);
    }

    /**
     * indicate wether evnt complete profile is triggered earlier on not
     * check for wether key is present or not
     * if present return false
     * if not than write key to paper db and return true
     * does opposite as name suggest
     *
     * @param write to write key in local storage or not
     * @return true if key is not present
     */
    public static boolean isCompleteProfileEventTriggered(final boolean write) {
        if (Paper.book().contains(PAPER_IS_COMPLETE_PROFILE_EVENT_TRIGGERED)) {
            return false;
        } else {
            if (write) {
                Paper.book().write(PAPER_IS_COMPLETE_PROFILE_EVENT_TRIGGERED, true);
            }
            return true;
        }
    }
}
