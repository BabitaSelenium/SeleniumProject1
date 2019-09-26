package com.training.bean;

public class RTTC_073_DBBean {
	private String pname;
	private String mTitle;
	private String model;
	private String tprice;
	private String category;
	private String qty;
	private String uprice;
	private String pts;
	
	public RTTC_073_DBBean() {
	}

	public RTTC_073_DBBean(String pname, String mTitle, String model, String tprice, String category, String qty, String uprice, String pts) {
		super();
		this.pname = pname;
		this.mTitle = mTitle;
		this.model = model;
		this.tprice = tprice;
		this.category = category;
		this.qty = qty;
		this.uprice = uprice;
		this.pts = pts;
	}

	public String getpname() {
		return pname;
	}

	public void setpname(String pname) {
		this.pname = pname;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public String getmodel() {
		return model;
	}

	public void setmodel(String model) {
		this.model = model;
	}

	public String gettprice() {
		return tprice;
	}

	public void settprice(String tprice) {
		this.tprice = tprice;
	}
	
	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}

	public String getqty() {
		return qty;
	}

	public void setqty(String qty) {
		this.qty = qty;
	}
	
	public String getuprice() {
		return uprice;
	}

	public void setuprice(String uprice) {
		this.uprice = uprice;
	}

	public String getpts() {
		return pts;
	}

	public void setpts(String pts) {
		this.pts = pts;
	}

	@Override
	public String toString() {
		return "RTTC_073_DBBean [pname=" + pname + ", mTitle=" + mTitle + ", model=" + model + ", tprice=" + tprice + ", category=" + category + ", qty=" + qty + ", uprice=" + uprice + ", pts=" + pts + "]";
	}

}
