package com.ls.bean;

/**
 * Created by ls on 15-6-6.
 * <p/>
 * lineId	线路编号
 * specialLine	是否专线
 * route	途径地
 * companyId	物流公司编号
 */
public class Route {
    public Route(String lineId, String id, String specialLine, String route, String companyId) {
        this.lineId = lineId;
        this.id = id;
        this.specialLine = specialLine;
        this.route = route;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", lineId='" + lineId + '\'' +
                ", specialLine='" + specialLine + '\'' +
                ", route='" + route + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }


    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getSpecialLine() {
        return specialLine;
    }

    public void setSpecialLine(String specialLine) {
        this.specialLine = specialLine;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    private String id;
    private String lineId;
    private String specialLine;
    private String route;
    private String companyId;
}
