package pl.kedziorek.liquorganizer.utils;

public class Cookie {
    public static javax.servlet.http.Cookie buildCookie(int maxAge, boolean secure, boolean httpOnly, String path, String domain, String value, String name){
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        cookie.setPath(path);
        cookie.setDomain(domain);
        return cookie;
    }
}
