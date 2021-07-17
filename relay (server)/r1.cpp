#include <iostream>
#include <wiringPi.h>
#include <cstring>


using namespace std;

int main()
{


	wiringPiSetup();
	pinMode(25,OUTPUT);

	cout<<"Relay 1 turned off.";
	digitalWrite(25,HIGH);




}
