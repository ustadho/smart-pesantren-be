package id.smartpesantren.service.dto;

public class BrandBoxChartData {
    int[] data;
    String label;

    public BrandBoxChartData() {
    }

    public BrandBoxChartData(String label, int[] data) {
        this.data = data;
        this.label = label;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
