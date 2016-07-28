package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Diagnostics {
    private Javascript javascript;
    private Cache[] cache;
    private Query[] query;
    private String buildVersion;
    private String serviceTime;

    private String publiclyCallable;

    private String userTime;

    private Url[] url;

    public Javascript getJavascript() {
        return javascript;
    }

    public void setJavascript(Javascript javascript) {
        this.javascript = javascript;
    }

    public Cache[] getCache() {
        return cache;
    }

    public void setCache(Cache[] cache) {
        this.cache = cache;
    }

    public Query[] getQuery() {
        return query;
    }

    public void setQuery(Query[] query) {
        this.query = query;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setbuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getserviceTime() {
        return serviceTime;
    }

    public void setserviceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getPubliclyCallable() {
        return publiclyCallable;
    }

    public void setPubliclyCallable(String publiclyCallable) {
        this.publiclyCallable = publiclyCallable;
    }

    public String getuserTime() {
        return userTime;
    }

    public void setuserTime(String userTime) {
        this.userTime = userTime;
    }

    public Url[] getUrl() {
        return url;
    }

    public void setUrl(Url[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [javascript = " + javascript + ", cache = " + cache + ", query = " + query + ", build-version = " + buildVersion + ", service-time = " + serviceTime + ", publiclyCallable = " + publiclyCallable + ", user-time = " + userTime + ", url = " + url + "]";
    }
}
