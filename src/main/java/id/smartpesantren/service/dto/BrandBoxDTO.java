package id.smartpesantren.service.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandBoxDTO {
    String tujuan;
    List<BrandBoxChartData> brandBoxChartData = new ArrayList<>();
    Integer totalLCL;
    Integer totalFCL;

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<BrandBoxChartData> getBrandBoxChartData() {
        return brandBoxChartData;
    }

    public void setBrandBoxChartData(List<BrandBoxChartData> brandBoxChartData) {
        this.brandBoxChartData = brandBoxChartData;
    }

    public Integer getTotalLCL() {
        return totalLCL;
    }

    public void setTotalLCL(Integer totalLCL) {
        this.totalLCL = totalLCL;
    }

    public Integer getTotalFCL() {
        return totalFCL;
    }

    public void setTotalFCL(Integer totalFCL) {
        this.totalFCL = totalFCL;
    }

}
