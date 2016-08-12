package com.jabava.test;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.MapContext;

public class FelEngineTest {
	public static void main(String[] args){
		FelEngine fel = new FelEngineImpl();
		MapContext ctx = new MapContext();
		ctx.set("人数", 3);
		ctx.set("总额", 689.39);
		Object result = fel.eval("总额/人数", ctx);
		System.out.println(result);

		/*ctx.set("人数", "3");
		ctx.set("总额", "689.39");
		result = fel.eval("总额/人数", ctx);
		System.out.println(result);*/
		
		
		System.out.println("------------------");
		
		ctx.set("need_secrecy_agreement", 2);
				
		result = fel.eval("(need_secrecy_agreement==1)?2:0", ctx);
		
		System.out.println(result);
		
		result = fel.eval("4", ctx);
		System.out.println(result);
		
		result = fel.eval("", ctx);
		System.out.println(result);
		
	}
	
	
	
	
}
