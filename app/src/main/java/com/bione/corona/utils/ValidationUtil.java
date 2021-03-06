package com.bione.corona.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.bione.corona.R;

/**
 * Developer: Bione
 */

public final class ValidationUtil {

    private static final int PASSWORD_LENGTH = 6;
    private static final int PHONE_LENGTH = 10;

    /**
     * Prevent instantiation
     */
    private ValidationUtil() {
    }

    /**
     * Method to validate email id
     *
     * @param email user email
     * @return whether email is valid
     */
    public static boolean checkEmail(final String email) {
        if (TextUtils.isEmpty(email) || (!email.matches(Patterns.EMAIL_ADDRESS.toString()))) {
            return false;
        }
        return true;
    }

    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        if (TextUtils.isEmpty(password) || (password.length() < PASSWORD_LENGTH)) {
            return false;
        }
        return true;
    }

    /**
     * Method to validate Phone Number
     *
     * @param phone user entered password
     * @return whether the password is valid
     */
    public static boolean checkPhone(final String phone) {
        if (TextUtils.isEmpty(phone) || (phone.length() < PHONE_LENGTH)) {
            return false;
        }
        return true;
    }

    public static void showToast(Context mContext) {
        Toast.makeText(mContext, mContext.getResources().getString(R.string.string_please_select_an_option), Toast.LENGTH_SHORT).show();
    }

    public static void showToast(final Context mContext, final String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
