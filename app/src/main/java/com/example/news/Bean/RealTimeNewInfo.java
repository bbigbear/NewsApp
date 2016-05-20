package com.example.news.Bean;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bear on 2016/5/19.
 */
public class RealTimeNewInfo {
    private int errNum ;
    private String errMsg;
    @SerializedName("retData")
    private List<RetDataEntity> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
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

    public static class RetDataEntity {
        private int return_count;
        private int has_more;
        private int offset;
        @SerializedName("data")
        private List<DataEntity> data;

        public int getReturn_count() {
            return return_count;
        }

        public void setReturn_count(int return_count) {
            this.return_count = return_count;
        }

        public int getHas_more() {
            return has_more;
        }

        public void setHas_more(int has_more) {
            this.has_more = has_more;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public static class DataEntity {

            private String title;


            @SerializedName("abstract")
            private String isabstract;

            private String url;
            private String datetime;
            private String img_url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIsabstract() {
                return isabstract;
            }

            public void setIsabstract(String isabstract) {
                this.isabstract = isabstract;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDatatime() {
                return datetime;
            }

            public void setDatatime(String datatime) {
                this.datetime = datatime;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }

    @Override
    public String toString() {
        return "RealTimeNewInfo{" +
                "errNum=" + errNum +
                ", errMsg='" + errMsg + '\'' +
                ", retData=" + retData +
                '}';
    }
}
