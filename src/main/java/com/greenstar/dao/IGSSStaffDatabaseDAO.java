package com.greenstar.dao;

public interface IGSSStaffDatabaseDAO {
    public void insertRecord(String code, int status, String token);

    public boolean isExist(String code);

    public void updateInformation(String code, int status, String token);

    public boolean isLoggedIn(String code);

    public boolean logout(String code);

    public String getToken(String code);
}
