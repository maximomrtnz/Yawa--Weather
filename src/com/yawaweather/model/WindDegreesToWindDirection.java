package com.yawaweather.model;

public class WindDegreesToWindDirection implements WindConversion{

	@Override
	public int convert(int degrees) {
		// TODO Auto-generated method stub
		return getIndex(degrees);
	}
	
	private int getIndex(int degrees){
		
		if((348.75 < degrees && degrees <=360) ||  (0 <= degrees &&  degrees <= 11.25) ){
			//N
			return 0;
		}
		
		if( 11.25 < degrees &&  degrees <= 33.75){
			//NNE
			return 1;
		}
		
		if( 33.75 < degrees &&  degrees <= 56.25){
			//NE
			return 2;
		}
		
		if(56.25 < degrees &&  degrees <= 78.75){
			//ENE
			return 3;
		}
		
		

		if (78.75 < degrees &&  degrees <= 101.25){
			//E
			return 4;
		}

		

		if(101.25 < degrees &&  degrees <= 123.75){
			//ESE
			return 5;
		}
		
		
		if(123.75 < degrees &&  degrees <= 146.25){

			//SE
			return 6;
		}
		
		if(146.25 < degrees &&  degrees <= 168.75){

			//SSE
			return 7;
		}
		
		if(168.75 < degrees &&  degrees <= 191.25){

			//S
			return 8;
		}
		
		
		if(191.25 < degrees &&  degrees <= 213.75){

			//SSW
			return 9;
		}
		
		if(213.75 < degrees &&  degrees <= 236.25){

			//SW
			return 10;
		}	
		
		if(236.25 < degrees &&  degrees <= 258.75){

			//wsW
			return 11;
		}
		
		if(258.75 < degrees &&  degrees <= 281.25){

			//W
			return 12;
		}	
		
		if(281.25 < degrees &&  degrees <= 303.75){

			//WNW
			return 13;
		}	
		
		if(303.75 < degrees &&  degrees <= 326.25){

			//NW
			return 14;
		}
		//if(326.25 < degrees &&  degrees <= 348.75){
			//NNW	
			return 15;
		
		//}
		
	}
}
