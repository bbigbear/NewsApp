package com.example.news.Bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bear on 2016/5/21.
 */
public class RealTimeNewInfoTwo implements Serializable{
    private String errNum;
    private String errMsg;
    private List<RetDataEntity> retData;

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RetDataEntity> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataEntity> retData) {
        this.retData = retData;
    }



    public static class RetDataEntity implements Serializable{
        private String title;
        private String url;
        @SerializedName("abstract")
        private String abstract_content;
        private String image_url;

        public RetDataEntity(String title, String url, String abstract_content, String image_url) {
            this.title = title;
            this.url = url;
            this.abstract_content = abstract_content;
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAbstract_content() {
            return abstract_content;
        }

        public void setAbstract_content(String abstract_content) {
            this.abstract_content = abstract_content;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
