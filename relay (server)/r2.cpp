#include <iostream>
#include <wiringPi.h>
#include <cstring>


using namespace std;

int main()
{


	wiringPiSetup();
	pinMode(28,OUTPUT);

	cout<<"Relay 2 turned off.";
	digitalWrite(28,LOW);




}
