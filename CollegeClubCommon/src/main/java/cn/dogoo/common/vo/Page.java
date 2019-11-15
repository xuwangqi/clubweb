package cn.dogoo.common.vo;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private Integer currentPage;
	private Integer totalPage;
	private List<?> products;
	private String info;
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getProducts() {
		return products;
	}
	public void setProducts(List<?> products) {
		this.products = products;
	}
	
	
	
}
