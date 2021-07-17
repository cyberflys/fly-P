#include <iostream>
#include <wiringPi.h>
#include <cstring>


using namespace std;

int main()
{


	wiringPiSetup();
	pinMode(29,OUTPUT);

	cout<<"Relay 3 turned off.";
	digitalWrite(29,HIGH);




}
