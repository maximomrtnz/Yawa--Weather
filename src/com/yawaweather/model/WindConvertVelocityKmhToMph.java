package com.yawaweather.model;

public class WindConvertVelocityKmhToMph implements WindVelocityConversion{

	@Override
	public String convert(int kPHvelocity) {
		// TODO Auto-generated method stub
		int mPHVelocity = (int) (kPHvelocity * 1/1.609344);
		return Integer.toString(mPHVelocity) + "mph";
	}

}
