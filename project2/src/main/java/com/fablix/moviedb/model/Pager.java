package com.fablix.moviedb.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {
	private int pageSize;
	private int currentPage;
	private int totalRecord;
	private int totalPage;
	private List<T> dataList = new ArrayList<T>();
	
	public Pager(){
		super();
	}
	
	public Pager(int pageSize, int currentPage, int totalRecord, List<T> dataList){
		if (dataList.size() == 0) return;
		
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.totalPage = (int) Math.ceil((double)this.totalRecord / this.pageSize);
		
		this.currentPage = currentPage > this.totalPage? this.totalPage:currentPage;
		//this.currentPage = currentPage;
		
		//int fromIndex = this.pageSize * (this.currentPage - 1);
		//int toIndex = this.currentPage * this.pageSize > this.totalRecord ? this.totalRecord : fromIndex + this.pageSize ;
		
		this.dataList = dataList;//.subList(fromIndex, toIndex);
		
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
}
