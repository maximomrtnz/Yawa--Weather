package com.yawaweather.model;

public class InHgToHectopascal implements PressureConversion{

	@Override
	public String convert(double pressureInHg) {
		// TODO Auto-generated method stub
		int pressure = (int)Math.round(pressureInHg/0.0295299830714);
		return pressure + "hPa";
	}

}
