package com.greenstar.controller.sms;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.sql.ResultSet;
import java.sql.Statement;

public class FFM_Cache {

	private Hashtable<String, GSM_Staff> staffTable;
	private Hashtable<String, GSM_Provider> providerTable;
	private Hashtable<String, Hashtable<String,GSM_FFM_Activity>> activityTable;
	private Hashtable<String, String> providerAlias;
	private Hashtable<String, String> staffMSISDN;
	private Hashtable<String, GSM_Product> productTable;

	/**
	 * 
	 */
	public FFM_Cache() {

		super();
		// TODO Auto-generated constructor stub
		this.staffTable = new Hashtable<String, GSM_Staff>();
		this.providerTable = new Hashtable<String, GSM_Provider>();
		this.activityTable = new Hashtable<String, Hashtable<String,GSM_FFM_Activity>>();
		this.providerAlias = new Hashtable<String, String>();
		this.staffMSISDN   = new Hashtable<String, String>();
		this.productTable = new Hashtable<String, GSM_Product>();

	}

	/**
	 * 
	 * @param staffTable
	 * @param providerTable
	 * @param activityTable
	 * @param providerAlias
	 * @param staffMSISDN
	 * @param productTable
	 */
	public FFM_Cache(Hashtable<String, GSM_Staff> staffTable,
			Hashtable<String, GSM_Provider> providerTable,
			Hashtable<String, Hashtable<String,GSM_FFM_Activity>> activityTable,
			Hashtable<String, String> providerAlias, Hashtable<String,String> staffMSISDN,
			Hashtable<String, GSM_Product> productTable) {

		super();

		this.staffTable = staffTable;
		this.providerTable = providerTable;
		this.activityTable = activityTable;
		this.providerAlias = providerAlias;
		this.staffMSISDN = staffMSISDN;
		this.productTable = productTable;
	}

	/**
	 * 
	 */
	public void loadCache() {

		try {
			FFMDatabase db = new FFMDatabase();
			Statement statement = db.getDBConnection();

			 this.loadStaff(statement);
			 System.out.println("Loaded Staff in cache");
			 
			 this.loadProivder(statement);
			 System.out.println("Loaded Proivers in cache");
			 
			 this.loadActivities(statement);
			 System.out.println("Loaded Activities in cache");
			 
			 this.loadProducts(statement);
			 System.out.println("Loaded Products in cache");

			statement.close();
		} catch (Exception ex) {
			System.out.println("Load Cache: " + ex.toString());
		}

	}

	/**
	 * 
	 * @param statement
	 */
	private void loadStaff(Statement statement) {

		String query = "select "
				+ " distinct "
				+ " p.staff_code, "
				+ " st.mlc_desc, "
				+ " p.desig as designation_code, "
				+ " des.dtl_type_desc, "
				+ " st.MLC_MSISDN "
				+ " from GSM_SMS_POSTS p "
				+ " join inv_sm_location st on ( p.staff_code = st.mlc_code and st.mlc_cp_code = '01' "
				+ " and p.EFF_DATE = (select max(eff_date) from GSM_SMS_POSTS ti where ti.staff_code = p.staff_code) ) "
				+ " join INV_SO_LOC_TYPE_DTL des on (p.desig = des.dtl_type_code) "
//				+ " order by 1 asc "
				+ " union "
				+ " select "
				+ " distinct "
				+ " p.staff_code, "
				+ " st.mlc_desc, "
				+ " p.desig as designation_code, "
				+ " p.desig as designation_code, "
				+ " st.MLC_MSISDN "
				+ " from GSM_SMS_POSTS p "
				+ " join inv_sm_location st on ( p.staff_code = st.mlc_code and st.mlc_cp_code = '01' "
				+ " and p.EFF_DATE = (select max(eff_date) from GSM_SMS_POSTS ti where ti.staff_code = p.staff_code) and st.MLC_MSISDN is not null) "
				+ " order by 1 asc";

		try {

			ResultSet staffList = statement.executeQuery(query);

			while (staffList.next()) {

				GSM_Staff staff = new GSM_Staff();

				staff.setStaffCode(staffList.getString(1));
				staff.setStaffName(staffList.getString(2));
				staff.setDesignationCode(staffList.getString(3));
				staff.setDesignation(staffList.getString(4));
				staff.setStaffMSISDN(staffList.getString(5));

				this.staffTable.put(staff.getStaffCode(), staff);
				
				if (staff.getStaffMSISDN() != null)
				this.staffMSISDN.put(staff.getStaffMSISDN(), staff.getStaffCode());

			}

		} catch (Exception ex) {

			System.out.println("Load Staff List: " + "");
			ex.printStackTrace();

		}

	}
	
	private void loadProducts(Statement statement){
		
		String query = "select "
				+ " PRODUCT_SKU, "
				+ " PRODUCT_NAME, "
 				+ " PRODUCT_TYPE, "
				+ " PRODUCT_SHORTCODE, "
				+ " STATUS "
				+ " from SFM_PRODUCTS";
		
		try {

			ResultSet productList = statement.executeQuery(query);

			while (productList.next()) {

				GSM_Product product = new GSM_Product();
				product.setSKU(productList.getString(1));
				product.setName(productList.getString(2));
				product.setType(productList.getString(3));
				product.setShortcode(productList.getString(4));
				product.setStatus(productList.getString(5));
				
							

				this.productTable.put(product.getShortcode(), product);
				

			}

		} catch (Exception ex) {

			System.out.println("Load Product List: " + "");
			ex.printStackTrace();

		}
	}
	

	/**
	 * 
	 * @param statement
	 */
	private void loadProivder(Statement statement) {

//		String query = "select " + " mpv_code,mpv_desc,mpv_alias "
	//			+ " from sal_sm_provider where mpv_cp_code = '01' "
		//		+ " and mpv_alias is not null " + " and LANDSCAPE_MPV = 'Y' "
			//	+ " and MPV_FLAG = 'Y'";

		
		String query = "select " + " mpv_code,mpv_desc,mpv_alias "
				+ " from sal_sm_provider where mpv_cp_code = '01' "
				+ " and mpv_alias is not null " 
				+ " and MPV_FLAG = 'Y'";

		
		try {

			ResultSet providerfList = statement.executeQuery(query);

			while (providerfList.next()) {

				GSM_Provider provider = new GSM_Provider();

				provider.setProviderCode(providerfList.getString(1));
				provider.setProviderName(providerfList.getString(2));
				provider.setProviderAlias(providerfList.getString(3));
				// staff.setStaffCode(staffList.getString(0));

				this.providerTable.put(provider.getProviderCode(), provider);
				this.providerAlias.put(provider.getProviderAlias(),
						provider.getProviderCode());

			}

		} catch (Exception ex) {

			System.out.println("Load Staff List: " + "");
			ex.printStackTrace();

		}
	}

	/**
	 * 
	 * @param statement
	 */
	private void loadActivities(Statement statement) {

		String query = "select " + "activity_id, " + "activity_code, "
				+ "activity_name, " + "activity_type "
				+ "from GSM_SMS_ACTVITIES " + "where status = 'ACTV'";

		try {

			ResultSet activityList = statement.executeQuery(query);

			while (activityList.next()) {

				GSM_FFM_Activity activity = new GSM_FFM_Activity();

				activity.setActivityID(activityList.getInt(1));
				activity.setActivityCode(activityList.getInt(2));
				activity.setActivityName(activityList.getString(3));
				activity.setActivityType(activityList.getString(4));

				if (this.activityTable.get(activity.getActivityType()) != null) {

					//this.activityTable.get(activity.getActivityType()).add(activity);
					this.activityTable.get(activity.getActivityType()).put(activity.getActivityCode()+"", activity);
					

				} else {

					//ArrayList<GSM_FFM_Activity> activityArrayList = new ArrayList<GSM_FFM_Activity>();
					//activityArrayList.add(activity);
					Hashtable<String, GSM_FFM_Activity> activityHashTable = new Hashtable<String,GSM_FFM_Activity>();
					activityHashTable.put(activity.getActivityCode()+"", activity);
					
					this.activityTable.put(activity.getActivityType(), activityHashTable);

					//this.activityTable.put(activity.getActivityType(), activityArrayList);

				}

				// this.staffTable.put(staff.getStaffCode(), staff);

			}

		} catch (Exception ex) {

			System.out.println("Load Activity List: " + "");
			ex.printStackTrace();

		}

	}

	/**
	 * 
	 * @param staffCode
	 * @return
	 */
	public GSM_Staff getStaff(String staffCode) {
		
		//System.out.println(staffCode);

		try {
			//System.out.println(this.staffTable.get(staffCode) + "----------------------");
			//System.out.println(staffCode);
			if (staffCode != null
					&& this.staffTable.containsKey(staffCode))
				return this.staffTable.get(staffCode);

		} catch (Exception ex) {

			System.out.println("Get Staff Object: " + "");
			ex.printStackTrace();

		}

		return null;
	}
	
	public GSM_Staff getStaffFromMSISDN(String staffMSISDN){
		
		
		try{
			//System.out.println(staffMSISDN);
						
			if (staffMSISDN != null && this.staffMSISDN.containsKey(staffMSISDN)){
			
				//System.out.println(this.staffMSISDN.get(staffMSISDN));
				String staffcode = this.staffMSISDN.get(staffMSISDN);
				//System.out.println("Our number: " + staffMSISDN + "|" + staffcode);
				
				
				return this.getStaff(staffcode);
				//return this.getStaff(this.staffMSISDN.get(staffMSISDN));
			}
//			else{
//				System.out.println("Not our number: " + staffMSISDN);
//			}
			
		}
		catch(Exception ex){
			System.out.println("Get Staff Object From MSISDN: " + "");
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public String getStaffCode(String MSISDN){
		
		try{
			
			this.staffMSISDN.get(MSISDN);
		}
		catch(Exception ex){
			System.out.println("getStaffCode From MSISDN: " + "");
			ex.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 
	 * @param providerCode
	 * @return
	 */
	public GSM_Product getProductFromCode(String productCode) {

		try {

			if (this.productTable != null
					&& this.productTable.containsKey(productCode))
				return this.productTable.get(productCode);

		} catch (Exception ex) {

			System.out.println("Get Product Object: " + "");
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * 
	 * @param providerCode
	 * @return
	 */
	public GSM_Provider getProviderFromCode(String providerCode) {

		try {

			if (this.providerTable != null
					&& this.providerTable.containsKey(providerCode))
				return this.providerTable.get(providerCode);

		} catch (Exception ex) {

			System.out.println("Get Provider Object: " + "");
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * 
	 * @param providerAlias
	 * @return
	 */
	public GSM_Provider getProviderFromAlias(String providerAlias) {
		
		try {

			if (this.providerAlias != null
					&& this.providerAlias.containsKey(providerAlias))
				return getProviderFromCode(this.providerAlias.get(providerAlias));

		} catch (Exception ex) {

			System.out.println("Get Provider Object From Alias: " + "");
			ex.printStackTrace();

		}
		

		return null;
	}

	/**
	 * 
	 * @param activityType
	 * @param activityCode
	 * @return
	 */
	public GSM_FFM_Activity getActivity(String activityType, String activityCode) {

		try {

			if (this.activityTable != null
					&& this.activityTable.containsKey(activityType)){
				
				try {
					return this.activityTable.get(activityType).get(activityCode); // returns the activity 
				}
				catch(IndexOutOfBoundsException ex){
					 
					System.out.println("Activty Index Out of bound: " + "");
					ex.printStackTrace();
					return null;
					}
				
				
			}
		} catch (Exception ex) {

			System.out.println("Get Activity Object: " + "");
			ex.printStackTrace();

		}
		
		
		
		return null;
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void printStaff() {

		try {

			Iterator<Entry<String, GSM_Staff>> iterator = this.staffTable
					.entrySet().iterator();

			while (iterator.hasNext()) {

				// GSM_Staff staff = (GSM_Staff) iterator.next();
				Entry<String, GSM_Staff> entry = iterator.next();

				System.out.println(entry.getKey() + " - "
						+ entry.getValue().toString());

			}

		} catch (Exception ex) {
			System.out.println("Print Staff List: " + "");
			ex.printStackTrace();
		}

	}// End method printStaff();

	/**
	 * 
	 * 
	 * 
	 */
	public void printProvider() {

		try {

			Iterator<Entry<String, GSM_Provider>> iterator = this.providerTable
					.entrySet().iterator();

			while (iterator.hasNext()) {

				// GSM_Staff staff = (GSM_Staff) iterator.next();
				Entry<String, GSM_Provider> entry = iterator.next();

				System.out.println(entry.getKey() + " - "
						+ entry.getValue().toString());

			}

		} catch (Exception ex) {
			System.out.println("Print Provider List: " + "");
			ex.printStackTrace();
		}

	}// End method printProvider();

	/**
	 * 
	 * 
	 * 
	 */
	public void printActivity() {

		try {
			
			System.out.println(this.activityTable.size());

			Iterator<Entry<String, Hashtable<String,GSM_FFM_Activity>>> iterator = this.activityTable
					.entrySet().iterator();

			while (iterator.hasNext()) {

				Entry<String, Hashtable<String,GSM_FFM_Activity>> entry = iterator.next();

				System.out.println("Activity: " + entry.getKey() + " Size:" + entry.getValue().size());
				
				Hashtable<String, GSM_FFM_Activity> subTable = entry.getValue();
				
				//System.out.println(subTable);
				
				
				Iterator<Entry<String, GSM_FFM_Activity>> subIterator = subTable.entrySet().iterator();

				while (subIterator.hasNext()) {

					Entry<String, GSM_FFM_Activity> entry2 = subIterator.next();

					System.out.println(entry2.getKey() + " - "
							+ entry2.getValue().toString());

				}
				

			}

		} catch (Exception ex) {
			System.out.println("Print Activity List: " + "");
			ex.printStackTrace();
		}

	}// End method printActivity();
	
	public void printMSIDSNStaff(){
		
		try{
			
			
			Iterator<Entry<String, String>> iterator = this.staffMSISDN.entrySet().iterator();
					
			while(iterator.hasNext()){
				
				Entry<String,String> entry = iterator.next();
				
				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
				
			}
			
		}
		catch(Exception ex){
			System.out.println("printMSIDSNStaff " + "");
			ex.printStackTrace();
		}
	}
	
	public boolean isStaff(String staffCode){
		
		//System.out.println("-------------------------" + staffCode);
		if (this.staffTable.containsKey(staffCode)) return true;
		
		return false;
	}
	
	public boolean isProvider(String privderShortCode){
		
		//System.out.println("-------------------------" + privderShortCode);
		//if (this.providerTable.containsKey(privderShortCode)) return true;
		
		if (this.providerTable.containsKey(privderShortCode)) return true;		// Check first if the staff has sent the acutal provider code (9 Digit).
		else if (this.providerAlias.containsKey(privderShortCode)) return true; // Otherwise check if the staff has sent provider short code.
		
		return false;
	}

}
