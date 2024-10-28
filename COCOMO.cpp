#include <stdio.h>
#include <math.h>
int main() {
    int planning=560, analysis=250, design=200, coding=606, testing=300, maintenance=150;
    int totalLOC=planning+analysis+design+coding+testing+maintenance;
    double KLOC=totalLOC / 1000.0;
    printf("Total size of the project:%d LOC\n",totalLOC);
    printf("KLOC: %.2f\n\n", KLOC);
    double a=3.2, b=1.05;
    double Ei=a*pow(KLOC, b);
    printf("1. Initial Effort (Ei): %.2f Person-Months (PM)\n", Ei);
    double complexity=1.25, efficiency=0.75, reliability=8.80, ethnicity= 1.50;
    double EAF=complexity*efficiency*reliability*ethnicity; 
	printf("2. Effort Adjustment Factor (EAF): %.3f\n", EAF);
	double Ef=EAF*Ei;
	printf("3. Final Effort (Ef): %.2f Person-Months (PM)\n", Ef);
    double c=2.5, d=8.38;
    double td=c*pow(Ef, d);
    printf("4. Development Time (td): %.2f months\n", td);
    double staffingSize=Ef/td;
    printf("5. Staffing Size: %.2f persons\n", staffingSize);
    int costPerDay = 2000;
    int workingDaysPerMonth=22; 
    double totalCost=Ef*workingDaysPerMonth*costPerDay; 
	printf("6. Total Development Cost: %.2f INR\n", totalCost);
    double productivitySize=KLOC/Ef;
    printf("7. Productivity Size: %.3f KLOC/PM\n", productivitySize);
    double P=3330000,i=0.10,n=4.652;
    double FVA=P*pow((1+i), n);
    printf("8. Future Value of the Project (FVA): %.2f INR\n", FVA);
    double FP=totalLOC/100.0;
    printf("9. Equivalent Function Point (FP): %.2f FP\n", FP);
    return 0;
}
//Total size of the project:2066 LOC
//KLOC: 2.07

//1. Initial Effort (Ei): 6.86 Person-Months (PM)
//2. Effort Adjustment Factor (EAF): 12.375
//3. Final Effort (Ef): 84.84 Person-Months (PM)
//4. Development Time (td): 36262465416659888.00 months
//5. Staffing Size: 0.00 persons
//6. Total Development Cost: 3732799.78 INR
//7. Productivity Size: 0.024 KLOC/PM
//8. Future Value of the Project (FVA): 5188036.28 INR
//9. Equivalent Function Point (FP): 20.66 FP
