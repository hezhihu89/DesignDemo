package com.bugke.demo.designdemo.bean;

import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */

public class NewsItem {

    /**
     * modelmode : u
     * digest :
     * skipType : photoset
     * commentCount : 528
     * url : 00AJ0003|614260
     * docid : C42QQR2Jbjsongyuxin
     * title : 孕妈Baby靓丽现身 腰身胖了好几圈
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/a4ec62bb8080465bbda205d68af805af20161023151742.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/b4b1e0a38b7340debb1b945ca74bfb1d20161023151742.jpeg"}]
     * source : 网易原创
     * priority : 250
     * imgsrc : http://cms-bucket.nosdn.127.net/10448cc308c545fa9d3bf0f361de640c20161023190236.jpeg
     * skipURL : http://3g.163.com/touch/photoview.html?channelid=0003&setid=614260
     * olympicType : 0
     * stitle : 00AJ0003|614260
     * photosetID : 0003|614260
     * ptime : 2016-10-23 15:17:59
     * imgsrc3gtype : 2
     */

    private String modelmode;
    private String digest;
    private String skipType;
    private int commentCount;
    private String url;
    private String docid;
    private String title;
    private String source;
    private int priority;
    private String imgsrc;
    private String skipURL;
    private int olympicType;
    private String stitle;
    private String photosetID;
    private String ptime;
    private String imgsrc3gtype;
    /**
     * imgsrc : http://cms-bucket.nosdn.127.net/a4ec62bb8080465bbda205d68af805af20161023151742.jpeg
     */

    private List<ImgextraBean> imgextra;

    public String getModelmode() {
        return modelmode;
    }

    public void setModelmode(String modelmode) {
        this.modelmode = modelmode;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSkipURL() {
        return skipURL;
    }

    public void setSkipURL(String skipURL) {
        this.skipURL = skipURL;
    }

    public int getOlympicType() {
        return olympicType;
    }

    public void setOlympicType(int olympicType) {
        this.olympicType = olympicType;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getImgsrc3gtype() {
        return imgsrc3gtype;
    }

    public void setImgsrc3gtype(String imgsrc3gtype) {
        this.imgsrc3gtype = imgsrc3gtype;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class ImgextraBean {
        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
