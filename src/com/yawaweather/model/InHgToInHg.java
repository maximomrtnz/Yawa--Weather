package com.yawaweather.model;

public class InHgToInHg implements PressureConversion{

	@Override
	public String convert(double pressureInHg) {
		// TODO Auto-generated method stub
		int pressure = (int) Math.round(pressureInHg);
		return  String.valueOf(pressure)+"inHg";
	}

}
