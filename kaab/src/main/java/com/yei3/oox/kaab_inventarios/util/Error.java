package com.yei3.oox.kaab_inventarios.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Error {
	private static final Map<Integer, String> errorMessages = createMap();

    private static Map<Integer, String> createMap() {
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "Success");
        result.put(-1, "one");
        result.put(-2, "two");
        result.put(-3, "The given id does not exists.");
        result.put(-4, "The given companyId does not match any existing Company");
        result.put(-5, "The given userId does not match any existing User.");
        result.put(-6, "The given statusId does not match any existing Status.");
        result.put(-7, "The given projectId does not match any existing Project.");
        result.put(-8, "The given currentDepartmentId does not match any existing Department.");
        result.put(-9, "The given costCenterId does not match any existing Cost Center.");
        result.put(-10, "The given accountingAccountId does not match any existing Accounting Account.");
        result.put(-11, "The given locationId does not match any existing Location.");
        result.put(-12, "The given companyAccountId does not match any existing Company Account.");
        result.put(-13, "The given addressId does not match any existing Address.");
        result.put(-14, "The given registrationSessionId does not match any existing Registration Session");
        result.put(-15, "The given departmentId does not match any existing Department.");
        result.put(-16, "The given menuId does not match any existing Menu.");
        result.put(-17, "The given contactId does not match any existing Contact.");
        result.put(-18, "The given assetId does not match any existing Asset.");
        result.put(-19, "The given fileId does not match any existing File.");
        result.put(-20, "two");
        result.put(-21, "two");
        result.put(-22, "two");
        return Collections.unmodifiableMap(result);
    }
	public static String getErrorByCode(int code) {
		return errorMessages.get(code);
	}
}
