package client;

import order.Order;
import order.OrderStatus;
import report.Report;
import report.ReportType;
import user.UserType;
import usersManagment.BranchManagerBoundary;
import usersManagment.UserBoundary;

public class Main {

	public static void main(String[] args)
	{
		ClientBoundary clientBoundary=new ClientBoundary();
		clientBoundary.connect("localhost",5555);
		UserBoundary loginuser=new UserBoundary();
		Boolean status=loginuser.requestLogin("Ronen", "Zeyan");
		if(status)
		{
			if(UserBoundary.CurrentUser.getUserType().equals(UserType.CEO))
			{
			System.out.println("the CEO is connected");
			}
			else
				System.out.println("user not found");
		}
		UserBoundary.CurrentUser=null;
		Boolean status1=loginuser.requestLogin("Rami", "Zeyan");
		if(!status1)
		{
			if(UserBoundary.CurrentUser==null)
			{
				System.out.println("user not found");
			}
			else
			if(UserBoundary.CurrentUser.getUserType().equals(UserType.CEO))
			{
			System.out.println("the CEO is connected");
			}
			
		}
		
		/*
		BranchManagerBoundary branch=new BranchManagerBoundary();
		Report report=branch.requestViewReport(ReportType.QUARTERLY_REVENUE_REPORT, 5, 2022);
		if(report!=null) {
		System.out.println("the month of report is:"+report.getMonth()+"and year is :"+report.getYear()+"and branch is "+report.getBranchName());
	System.out.println("you in account: "+branch.CurrentUser.getUsername()+" we are in branch name: "+branch.CurrentUser.getBranchName());
		}
		else
		{
			System.out.println("msg sended from method equal to null");
		}
	Order order=new Order();
	order.setOrderID(123);
	order.setOrderStatus(OrderStatus.WAITING_FOR_APPROAVL);
	status=branch.requestApproveOrder(123, false);
	if(status)
	{
		System.out.println("The order has been Approved!!!");
	}
	else
		System.out.println("The order has been not Approved!!!");

	*/
	}
	
}
