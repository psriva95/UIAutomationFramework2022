package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final int DEFAULT_TIME_OUT = 5;
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String REGISTER_PAGE_FRACTION_URL = "route=account/register";
	public static final String ACCOUNT_PAGE_TITLE = "My Account"; 
	public static final String REGISTER_PAGE_TITLE = "Register Account"; 
	
	public static final List<String> ACCOUNTS_SEC_LIST = Arrays.asList("My Account",
														"My Orders",
														"My Affiliate Account",
														"Newsletter");
	
	public static final int MACBOOK_PRODUCT_COUNT = 3;
	public static final int IMAC_PRODUCT_COUNT = 1;

	public static final int MACBOOK_IMAGES_COUNT = 4;
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "Register";
	public static final String PRODUCT_SHEET_NAME = "Product";
	
	public static String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/DemoCartTestData.xlsx";

}
