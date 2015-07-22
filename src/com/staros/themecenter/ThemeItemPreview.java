package com.staros.themecenter;

import android.graphics.Bitmap;

public class ThemeItemPreview {
	public int    themeId;
	public int    previewResouceID = -1;
	public String themePreivewUrl;
	public String themeName;
	public double themePrice;
	public String downloadUrl;
	
	public void setPreviewResouceID(int resID){
		this.previewResouceID = resID;
	}
	public void setThemeID(int id){
		this.themeId = id;
	}
	public void setDownloadUrl(String url){
		this.downloadUrl = url;
	}
	public void setThemePreview(String url){
		this.themePreivewUrl = url;
	}
	public void setThemeName(String name){
		this.themeName = name;
	}
	public void setThemePrice(double price){
		this.themePrice = price;
	}
	
	public String getThemePreviewUrl(){
		return themePreivewUrl;
	}
	public int getThemeId(){
		return themeId;
	}
	public String getThemeName(){
		return themeName;
	}
	public double getThemePrice(){
		return themePrice;
	}
	public String getDownloadURl(){
		return downloadUrl;
	}
	public int getPreviewResourceID(){
		return previewResouceID;
	}

}
