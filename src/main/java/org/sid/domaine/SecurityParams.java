package org.sid.domaine;

public interface SecurityParams {
    public static final String PRIVATE_SECRET = "groupe";
    public static final String JWT_HEADER = "authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION = 10 * 24 * 3600;
}
