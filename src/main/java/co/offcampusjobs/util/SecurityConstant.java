package co.offcampusjobs.util;

public class SecurityConstant {
    // 10 HRS IN MILLISECOND
    public static final long EXPIRATION_TIME = 36_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot be Verified";
    public static final String OFF_CAMPUS_JOBS_LLC = "OFF Campus JOBS, LLC";
    public static final String OFF_CAMPUS_JOBS_ADMINISTRATION = "admin token";
    public static final String OFF_CAMPUS_JOBS_CREATOR = "creator token";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to login to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/api/v1/user/register", "/api/v1/user/login",
            "/api/v1/user/resetpassword/**", "/api/v1/job/**", "/user/image/**"};
}
