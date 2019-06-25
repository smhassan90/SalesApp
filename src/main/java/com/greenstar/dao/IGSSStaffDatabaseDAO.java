package com.greenstar.dao;

import org.json.JSONObject;

public interface IGSSStaffDatabaseDAO {
    public void insertRecord(String code, int status, String token);

    public boolean isExist(String code);

    public void updateInformation(String code, int status, String token);

    public boolean isLoggedIn(String code);

    public boolean logout(String code);

    public String getToken(String code);

    /*
    This method is to check if this user code exist in Database
     */
    public boolean isCorrect(String code);

    public JSONObject performSync(String code);
}
