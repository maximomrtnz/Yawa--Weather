package com.yawaweather.model;


public class WindConvertVelocityMphToKmh implements WindVelocityConversion{

	@Override
	public String convert(int mPHVelocity) {
		// TODO Auto-generated method stub
		int kPHVelocity = (int)(mPHVelocity * 1.609344);
		return Integer.toString(kPHVelocity) + "kph";
	}

}
