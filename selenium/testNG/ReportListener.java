package testNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Làm gì đó khi chạy DONE class");

	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Làm gì đó trc khi chạy class");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("Làm gì đó trc khi fail 30%");

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("Làm gì đó khi chạy fail");

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Làm gì đó khi chạy skip");

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("Làm gì đó trc khi chạy tc này");

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Làm gì đó khi chạy pass");

	}

}
