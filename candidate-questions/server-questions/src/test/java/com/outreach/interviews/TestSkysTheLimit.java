package com.outreach.interviews;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.outreach.interviews.map.builder.SkysTheLimit;


public class TestSkysTheLimit {
	
	@Test
	public void TestSkysTheLimit1() throws UnsupportedOperationException, IOException{
		new SkysTheLimit.LongLatFinder()
						.setLocation("124", "Millgreen Crescent", "Ottawa", "Canada")
						.build();
	}
	@Test
	public void TestSkysTheLimit2() throws UnsupportedOperationException, IOException{
		String Latitude = new SkysTheLimit.LongLatFinder()
						  .setLocation("124", "Millgreen Crescent", "Ottawa", "Canada")
						  .build().getLat();
		
		String Longitude = new SkysTheLimit.LongLatFinder()
				  			.setLocation("124", "Millgreen Crescent", "Ottawa", "Canada")
				  			.build().getLong();
	
		System.out.println("Latitude :" + Latitude);
		System.out.println("Longitute:" + Longitude);
		assertNotNull(Latitude);
		assertNotNull(Longitude);
	}
	
	@Test (expected = java.lang.IndexOutOfBoundsException.class)
	public void TestSkysTheLimit3() throws AssertionError, IOException{
		String Latitude = new SkysTheLimit.LongLatFinder()
				  .setLocation("aaa", "asdgasd", "asdgasdasg", "asdgadsga")
				  .build().getLat();

		String Longitude = new SkysTheLimit.LongLatFinder()
		  			.setLocation("aaa", "asdgasd", "asdgasdasg", "asdgadsga") 	
		  			.build().getLong();
		
		System.out.println("Latitude :" + Latitude);
		System.out.println("Longitute:" + Longitude);
		assertNotNull(Latitude);
		assertNotNull(Longitude);
	}
	
	@Test
	public void TestSkysTheLimit4() throws UnsupportedOperationException, IOException{
		String Latitude = new SkysTheLimit.LongLatFinder()
						  .setLocation("Canada", "Ottawa", "MillgreenCrescent", "124")
						  .build().getLat();
		
		String Longitude = new SkysTheLimit.LongLatFinder()
				  			.setLocation("Canada", "Ottawa", "MillgreenCrescent", "124")
				  			.build().getLong();
	
		System.out.println("Latitude :" + Latitude);
		System.out.println("Longitute:" + Longitude);
		assertNotNull(Latitude);
		assertNotNull(Longitude);
	}
	
}
