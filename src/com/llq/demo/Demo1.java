package com.llq.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class Demo1 {

	
	/**
	 * 
	 * 
	 *   3    7
	 *  2 4  6 8
	 * 1   5    9
	 * 
	 * 平面图形题    (二维数组)
	 * 
	 * 
	 */
	@Test
	public void test1() throws Exception {
		
		int num=9;
		int arr[][]=new int[3][9];
		
		int x=2;
		int y=0;
		
		boolean order=false;
		
		for(int i=1;i<=9;i++){
			arr[x][y]=i;
			y++;
			
			if(!order){
				x--;
			}
			
			if(order){
				x++;
			}
			
			if(x<0){
				order=true;
				x=x+2;
			}
			if(x>2){
				order=false;
				x=x-2;
			}
		}
		
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				if(arr[i][j]==0){
					System.out.print(" ");
				}else{
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
		
	}
	
	
	
	/**
	 * 
	 * 
	 *   3    7
	 *  2 4  6 8
	 * 1   5    9
	 * 
	 * 平面图形题    (二维数组)
	 * 
	 * 通用
	 * 
	 * 
	 */
	@Test
	public void test2() throws Exception {
		
		int num=35;
		int height=num/4+1;
		int width=num;
		
		int arr[][]=new int[height][width];
		
		int x=height-1;
		int y=0;
		
		boolean order=false;
		
		for(int i=1;i<=num;i++){
			arr[x][y]=i;
			y++;
			
			if(!order){
				x--;
			}
			
			if(order){
				x++;
			}
			
			if(x<0){
				order=true;
				x=x+2;
			}
			if(x>height-1){
				order=false;
				x=x-2;
			}
		}
		
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				if(arr[i][j]==0){
					System.out.print(" ");
				}else{
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
		
	}
}
