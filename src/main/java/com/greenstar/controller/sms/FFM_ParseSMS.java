package com.greenstar.controller.sms;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Locale;

//import java.util.Map.Entry;

public class FFM_ParseSMS {

	private ArrayList<GSM_Raw_SMS> rawSMSList;
	private static final String status_Posted = "Posted";
	private static final String status_Started = "STARTED";
	private static final String status_Invalid = "INVALID";
	private static final String status_Raw = "Raw";

	public FFM_ParseSMS() {
		super();

		this.rawSMSList = new ArrayList<GSM_Raw_SMS>();
	}

	public FFM_ParseSMS(ArrayList<GSM_Raw_SMS> rawSMSList) {
		super();
		this.rawSMSList = rawSMSList;
	}

	public void loadRawSMS() {

		try {

			this.rawSMSList = new ArrayList<GSM_Raw_SMS>();

			FFMDatabase db = new FFMDatabase();
			Connection connection = db.getDBConnection(false); // Set auto
																// commit to
																// false
			Statement statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			String query = "select rc_id, " + "msisdn_from, " + "msisdn_to, "
					+ "rc_sms_text, "
					+ "to_char(rc_date,'DD-MON-YYYY HH24:MI:SS') rcdate, "
					+ "rc_staff_code, " + "rc_status, " + "rc_remarks "
					+ "from GSM_RECIEVING_DATA " + "where rc_status = '"
					+ FFM_ParseSMS.status_Raw + "' order by rcdate asc";

			ResultSet rawDataList = statement.executeQuery(query);

			SimpleDateFormat format = new SimpleDateFormat(
					"dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

			while (rawDataList.next()) {

				GSM_Raw_SMS rawSms = new GSM_Raw_SMS();

				String rcdate = rawDataList.getString("rcdate");

				// format.parse(rcdate);

				rawSms.setRc_id(rawDataList.getInt("rc_id"));
				rawSms.setMSISDN(rawDataList.getString("msisdn_from"));
				rawSms.setSmsText(rawDataList.getString("rc_sms_text")
						.replaceAll("\\s+", " ").trim().toUpperCase()); // Remove
																		// all
																		// starting
																		// and
																		// trailing
																		// spaces,
																		// and
																		// replaces
																		// multiple
																		// spaces
																		// with
																		// a
																		// single
																		// space,
																		// and
																		// finally
																		// convert
																		// to
																		// upper
																		// case.

				rawSms.setSmsDate(format.parse(rcdate)); // Parse the date and
															// save it.

				// rawSms.setSmsDate(rawDataList.getDate(5));
				rawSms.setStaffCode(rawDataList.getString("rc_staff_code"));
				rawSms.setStatus(rawDataList.getString("rc_status"));

				this.rawSMSList.add(rawSms);

			}
			statement.close();

		} catch (Exception ex) {
			System.out.println("Load RawSMS List: " + "");
			ex.printStackTrace();
		}

	}

	public SMS_Activity_Start getActivityStart(Connection connection,
			String staffCode, String activityDetail, String status,
			Date activityDate) {

		// TO-DO: Complete this method
		Statement statement = null;

		try {

			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy",
					Locale.ENGLISH);

			statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			String query = "select ID, STAFF_CODE, ACTIVITY_DETAIL,to_char(START_DATE,'DD-MON-YYYY HH24:MI:SS') startDate,STATUS,RC_ID "
					+ " from sms_activity_start "
					+ " where stafF_code = '"
					+ staffCode
					+ "' and activity_detail = '"
					+ activityDetail
					+ "' and status = '"
					+ FFM_ParseSMS.status_Started
					+ "' and to_date(start_date,'DD-MON-YY') =  to_date('"
					+ format.format(activityDate).toUpperCase()
					+ "','DD-MON-YY')" + " order by ID, START_DATE asc";

			System.out.println("Activity Started: " + query);

			ResultSet rawDataList = statement.executeQuery(query);

			format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",
					Locale.ENGLISH);

			while (rawDataList.next()) {

				SMS_Activity_Start smsActivityStart = new SMS_Activity_Start();

				// format.parse(rcdate);

				smsActivityStart.setID(rawDataList.getInt("ID"));
				smsActivityStart.setStaffCode(rawDataList
						.getString("STAFF_CODE"));
				smsActivityStart.setActivityDetail(rawDataList
						.getString("ACTIVITY_DETAIL"));

				String startdate = rawDataList.getString("startDate");
				smsActivityStart.setStartDate(format.parse(startdate));
				smsActivityStart.setStatus(rawDataList.getString("STATUS"));
				smsActivityStart.setRcID(rawDataList.getInt("RC_ID"));

				// System.out.println("Activity Start: " +
				// smsActivityStart.toString());

				return smsActivityStart;

			}

		} catch (Exception ex) {
			System.out.println("SMS_Activity_Start getActivityStart: " + "");
			ex.printStackTrace();
		}

		return null;
	}

	public void parseRawSMS(FFM_Cache cache) {

		try {

			FFMDatabase db = new FFMDatabase();
			// Statement statement = db.getDBConnection();
			Connection connection = db.getDBConnection(false); // Set auto
																// commit to
																// false
			SMS_Formatted smsFormatted = null;

			for (Iterator<GSM_Raw_SMS> iterator = rawSMSList.iterator(); iterator
					.hasNext();) {

				GSM_Raw_SMS gsm_Raw_SMS = (GSM_Raw_SMS) iterator.next();

				GSM_Staff st = cache
						.getStaffFromMSISDN(gsm_Raw_SMS.getMSISDN());

				smsFormatted = new SMS_Formatted();

				System.out.println("Process RAW: " + gsm_Raw_SMS.toString());

				String staffType = "";

				if (st != null)
					staffType = st.getDesignationCode();
				else {

					// If staff code is not found against any phone number then
					// mark this record as invalid and continue to next record
					smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
					smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
					smsFormatted.setRcRemarks("Invalid Sender");

					this.updateWrongMessage(connection, smsFormatted);

					continue; // Continue to next item in list

				}

				String msg[] = gsm_Raw_SMS.getSmsText().split(" ");

				smsFormatted.setStaffCode(st.getStaffCode());

				try {
					if (staffType.equals("AMN")) {

						if (msg.length == 2) {

							if (cache.isStaff(msg[0])) { // If it is staff code
															// then it must be a
															// supervisory visit

								String activityType = "Supervisory";

								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());

								} else {

									GSM_FFM_Activity act = cache.getActivity(
											activityType, msg[1]);

									if (act != null && this.isInteger(msg[1])) {
										// System.out.println("Activity ID is :"
										// + act.getActivityID() + " for Code:"
										// + msg[1]);

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);
									} else {
										System.out
												.println("Wrong Activity  Reported");
										// mark this message as wrong activity
										// code
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Invalid);
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted
												.setRcRemarks("Invalid Supervisory Visit Activity Reported");

										this.updateWrongMessage(connection,
												smsFormatted);
									}

								}// End Else recording activity
							} else if (cache.isProvider(msg[0])) { // If it is
																	// provider
																	// code then
																	// it must
																	// be a
																	// provider/independent
																	// visit

								String activityType = "ProviderVisit";

								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());
								} else {

									GSM_FFM_Activity act = cache.getActivity(
											activityType, msg[1]);

									if (act != null && this.isInteger(msg[1])) {
										// System.out.println("Activity ID is :"
										// + act.getActivityID() + " for Code:"
										// + msg[1]);

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);

									} else {
										// System.out.println("Wrong Activity  Reported");
										// mark this message as wrong activity
										// code

										smsFormatted
												.setStatus(FFM_ParseSMS.status_Invalid);
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted
												.setRcRemarks("Invalid Provider Visit Activity Reported");

										this.updateWrongMessage(connection,
												smsFormatted);

									}
								}// End Else recording activity

							} else if (msg[0].equals("OFF")) {

								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());
								} else {
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Format Reported");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else {
								System.out.println("Wrong Code");

								// It is not a provider nor a staff code, report
								// this as error
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Provider/Staff Code");

								this.updateWrongMessage(connection,
										smsFormatted);

							}

						} else if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);
							if (act != null && this.isInteger(msg[0])) {
								// System.out.println("Activity ID is :" +
								// act.getActivityID() + " for Code:" + msg[0]);
								// this.updateMessage(connection,
								// st.getStaffCode(), act.getActivityID(),
								// msg[0], msg[1], msg[2], msg[3], msg[4],
								// "Posted", gsm_Raw_SMS.getSmsDate(),
								// gsm_Raw_SMS.getRc_id(),
								// st.getDesignationCode());

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								// smsFormatted.setParam1(msg[1]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);
							} else {
								// System.out.println("Wrong Activity  Reported");
								// This is not the valid office activity
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Office Activity Reported");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							// System.out.println("Wrong Pattern");

							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format");

							this.updateWrongMessage(connection, smsFormatted);

						}

					} else if (staffType.equals("QAM")) {

						// System.out.println("QAM: " + msg.toString());

						// System.out.println(st.getStaffCode() + ":" +
						// gsm_Raw_SMS.getSmsText());

						if (msg.length == 2) {

							if (cache.isStaff(msg[0])) {

								String activityType = "Supervisory";

								// Record the start of activity
								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());

								} else {

									GSM_FFM_Activity act = cache.getActivity(
											activityType, msg[1]);

									if (act != null && this.isInteger(msg[1])) {
										// System.out.println("Activity ID is :"
										// + act.getActivityID() + " for Code:"
										// + msg[1]);

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);

									} else {
										// System.out.println("Wrong Activity  Reported");

										smsFormatted
												.setStatus(FFM_ParseSMS.status_Invalid);
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted
												.setRcRemarks("Invalid Supervisory Visit Activity Reported");

										this.updateWrongMessage(connection,
												smsFormatted);
									}

								} // End Else recording activity

							} else if (cache.isProvider(msg[0])) {

								String activityType = "ProviderVisit";

								// Record the start of activity
								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());

								} else {

									GSM_FFM_Activity act = cache.getActivity(
											activityType, msg[1]);

									if (act != null && this.isInteger(msg[1])) {
										// System.out.println("Activity ID is :"
										// + act.getActivityID() + " for Code:"
										// + msg[1]);

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);

									} else {
										// System.out.println("Wrong Activity  Reported");
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Invalid);
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted
												.setRcRemarks("Invalid Provider Visit Activity Reported");

										this.updateWrongMessage(connection,
												smsFormatted);
									}
								}// End Else recording activity

							} else if (msg[0].equals("OFF")) {

								if (msg[1].equals("0")) {

									// Saving activity start details
									this.reocrdActivityStart(connection,
											st.getStaffCode(), msg[0],
											gsm_Raw_SMS.getSmsDate(),
											gsm_Raw_SMS.getRc_id());
								} else {
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Format Reported");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else {
								// System.out.println("Wrong Code");

								// It is not a provider nor a staff code, report
								// this as error
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Provider/Staff Code");

								this.updateWrongMessage(connection,
										smsFormatted);

							}

						} else if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);

							if (act != null && this.isInteger(msg[0])) {
								// System.out.println("Activity ID is :" +
								// act.getActivityID() + " for Code:" + msg[0]);

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								// smsFormatted.setParam1(msg[1]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);

								// System.out.println(cache.getActivity(activityType,
								// Integer.parseInt(msg[1])));
							} else {
								// System.out.println("Wrong Activity  Reported");

								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Office Activity Reported");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							// System.out.println("Wrong Pattern");

							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format");

							this.updateWrongMessage(connection, smsFormatted);
						}

					} else if (staffType.equals("FPC")) { // FP Counselor

						// System.out.println("FPC: " + msg.toString());

						// System.out.println(st.getStaffCode() + ":" +
						// gsm_Raw_SMS.getSmsText());

						if (msg.length == 2) {

							if (cache.isProvider(msg[0]) && msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());

								// }

							} else if (msg[0].equals("OFF")
									&& msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
								// }
							} else if (msg[0].equals("HH")
									&& msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
								// }
							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for FPC");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else if (msg.length == 3) {

							if (cache.isProvider(msg[0])) {

								String activityType = "ProviderPlacement";

								if (isInteger(msg[1]) && isInteger(msg[2])) {
									// System.out.println("Correct Activity Provider Visit");

									GSM_FFM_Activity act = cache.getActivity(
											activityType, "0");

									if (act != null) {

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted.setParam3(msg[2]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);

									} else {
										// TODO: As of now no else part is
										// required, if required it can be added
										// later
									}

								} else {
									// System.out.println("Wrong Data");

									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Provider Placement Values");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else if (isInteger(msg[0]) && isInteger(msg[1])
									&& isInteger(msg[2])) {

								String activityType = "HouseholdVisit";

								GSM_FFM_Activity act = cache.getActivity(
										activityType, msg[0]);

								if (act != null) {
									// System.out.println("Household Activity ID is :"
									// + act.getActivityID() + " for Code:" +
									// msg[0]);

									smsFormatted.setActivityID(act
											.getActivityID());
									smsFormatted.setParam1(msg[0]);
									smsFormatted.setParam2(msg[1]);
									smsFormatted.setParam3(msg[2]);
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Posted);
									smsFormatted.setSmsDate(gsm_Raw_SMS
											.getSmsDate());
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted.setStaffDesigCode(st
											.getDesignationCode());

									SMS_Activity_Start sas = this
											.getActivityStart(
													connection,
													st.getStaffCode(),
													"HH",
													FFM_ParseSMS.status_Started,
													gsm_Raw_SMS.getSmsDate());

									if (sas != null) {

										smsFormatted.setStartID(sas.getID());
										smsFormatted.setStartDate(sas
												.getStartDate());

									}

									this.updateMessage(connection, smsFormatted);

								} else {
									// System.out.println("Wrong Activity  Reported");

									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Household Visit Activity Reported");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else {
								// System.out.println("Wrong Code");

								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format - FPC");

								this.updateWrongMessage(connection,
										smsFormatted);

							}

						} else if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);

							if (act != null && this.isInteger(msg[0])) {
								// System.out.println("OfficeVisit Activity ID is :"
								// + act.getActivityID() + " for Code:" +
								// msg[0]);
								// System.out.println(cache.getActivity(activityType,
								// Integer.parseInt(msg[1])));

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);

							} else {
								// System.out.println("Wrong Activity  Reported");

								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Office Activity Reported");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							// System.out.println("Wrong Pattern");

							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format");

							this.updateWrongMessage(connection, smsFormatted);

						}

					} else if (staffType.equals("ISOW")) {

						// System.out.println("ISOW: " + msg.toString());

						// System.out.println(st.getStaffCode() + ":" +
						// gsm_Raw_SMS.getSmsText());

						if (msg.length == 2) {

							if (cache.isProvider(msg[0]) && msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());

								// }

							} else if (msg[0].equals("OFF")
									&& msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
								// }
							} else if (msg[0].equals("HH")
									&& msg[1].equals("0")) {

								// if (msg[1].equals("0")){

								// Saving activity start details
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
								// }
							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for ISOW");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else if (msg.length == 3) {

							if (cache.isProvider(msg[0])) {

								String activityType = "ProviderPlacement";

								if (isInteger(msg[1]) && isInteger(msg[2])) {
									// System.out.println("Correct Activity Provider Visit");

									GSM_FFM_Activity act = cache.getActivity(
											activityType, "0");

									if (act != null) {

										smsFormatted.setActivityID(act
												.getActivityID());
										smsFormatted.setParam1(msg[0]);
										smsFormatted.setParam2(msg[1]);
										smsFormatted.setParam3(msg[2]);
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Posted);
										smsFormatted.setSmsDate(gsm_Raw_SMS
												.getSmsDate());
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted.setStaffDesigCode(st
												.getDesignationCode());

										SMS_Activity_Start sas = this
												.getActivityStart(
														connection,
														st.getStaffCode(),
														msg[0],
														FFM_ParseSMS.status_Started,
														gsm_Raw_SMS
																.getSmsDate());

										if (sas != null) {

											smsFormatted
													.setStartID(sas.getID());
											smsFormatted.setStartDate(sas
													.getStartDate());

										}

										this.updateMessage(connection,
												smsFormatted);

									} else {
										// TODO: As of now no else part is
										// required, if required it can be added
										// later
										smsFormatted
												.setStatus(FFM_ParseSMS.status_Invalid);
										smsFormatted.setRcID(gsm_Raw_SMS
												.getRc_id());
										smsFormatted
												.setRcRemarks("Invalid Format for ISOW");

										this.updateWrongMessage(connection,
												smsFormatted);
									}

								} else {
									// System.out.println("Wrong Data");

									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Provider Placement Values");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else if (isInteger(msg[0]) && isInteger(msg[1])
									&& isInteger(msg[2])) {

								String activityType = "HouseholdVisit";

								GSM_FFM_Activity act = cache.getActivity(
										activityType, msg[0]);

								if (act != null) {
									// System.out.println("Household Activity ID is :"
									// + act.getActivityID() + " for Code:" +
									// msg[0]);

									smsFormatted.setActivityID(act
											.getActivityID());
									smsFormatted.setParam1(msg[0]);
									smsFormatted.setParam2(msg[1]);
									smsFormatted.setParam3(msg[2]);
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Posted);
									smsFormatted.setSmsDate(gsm_Raw_SMS
											.getSmsDate());
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted.setStaffDesigCode(st
											.getDesignationCode());

									SMS_Activity_Start sas = this
											.getActivityStart(
													connection,
													st.getStaffCode(),
													"HH",
													FFM_ParseSMS.status_Started,
													gsm_Raw_SMS.getSmsDate());

									if (sas != null) {

										smsFormatted.setStartID(sas.getID());
										smsFormatted.setStartDate(sas
												.getStartDate());

									}

									this.updateMessage(connection, smsFormatted);

								} else {
									// System.out.println("Wrong Activity  Reported");

									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Household Visit Activity Reported");

									this.updateWrongMessage(connection,
											smsFormatted);
								}

							} else {
								// System.out.println("Wrong Code");

								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format - ISOW");

								this.updateWrongMessage(connection,
										smsFormatted);

							}

						} else if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);

							if (act != null && this.isInteger(msg[0])) {
								// System.out.println("OfficeVisit Activity ID is :"
								// + act.getActivityID() + " for Code:" +
								// msg[0]);
								// System.out.println(cache.getActivity(activityType,
								// Integer.parseInt(msg[1])));

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);

							} else {
								// System.out.println("Wrong Activity  Reported");

								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Office Activity Reported");

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							// System.out.println("Wrong Pattern");

							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format");

							this.updateWrongMessage(connection, smsFormatted);

						}

					} else if (staffType.equals("MT")) {

						if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);

							if (msg[0].equals("START")) {

								// Saving activity start details for classroom
								// training
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
							} else if (act != null && this.isInteger(msg[0])) {

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);
							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for MT.1"); // 1
																					// ->
																					// Implies
																					// single
																					// parameter
																					// reported
																					// by
																					// user

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else if (msg.length == 2) {

							if (msg[0].equals("OFF") && msg[1].equals("0")) {

								// Saving activity start details for office
								// activity
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
							} else if (isInteger(msg[0]) && isInteger(msg[1])) {

								String activityType = "ClassroomTraining";

								GSM_FFM_Activity act = cache.getActivity(
										activityType, msg[1]);

								if (act != null && this.isInteger(msg[1])) {
									// System.out.println("Activity ID is :" +
									// act.getActivityID() + " for Code:" +
									// msg[1]);

									smsFormatted.setActivityID(act
											.getActivityID());
									smsFormatted.setParam1(msg[0]);
									smsFormatted.setParam2(msg[1]);
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Posted);
									smsFormatted.setSmsDate(gsm_Raw_SMS
											.getSmsDate());
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted.setStaffDesigCode(st
											.getDesignationCode());

									SMS_Activity_Start sas = this
											.getActivityStart(
													connection,
													st.getStaffCode(),
													"START",
													FFM_ParseSMS.status_Started,
													gsm_Raw_SMS.getSmsDate());

									if (sas != null) {

										smsFormatted.setStartID(sas.getID());
										smsFormatted.setStartDate(sas
												.getStartDate());

									}

									this.updateMessage(connection, smsFormatted);

								}

							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for MT.2"); // 2
																					// ->
																					// Implies
																					// two
																					// parameters
																					// reported
																					// by
																					// user

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format for MT");

							this.updateWrongMessage(connection, smsFormatted);
						}

					} else if (staffType.equals("CT")) {

						if (msg.length == 1) {

							String activityType = "OfficeVisit";

							GSM_FFM_Activity act = cache.getActivity(
									activityType, msg[0]);

							if (msg[0].equals("START")) {

								// Saving activity start details for classroom
								// training
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
							} else if (act != null && this.isInteger(msg[0])) {

								smsFormatted.setActivityID(act.getActivityID());
								smsFormatted.setParam1(msg[0]);
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Posted);
								smsFormatted.setSmsDate(gsm_Raw_SMS
										.getSmsDate());
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted.setStaffDesigCode(st
										.getDesignationCode());

								SMS_Activity_Start sas = this.getActivityStart(
										connection, st.getStaffCode(), "OFF",
										FFM_ParseSMS.status_Started,
										gsm_Raw_SMS.getSmsDate());

								if (sas != null) {

									smsFormatted.setStartID(sas.getID());
									smsFormatted.setStartDate(sas
											.getStartDate());

								}

								this.updateMessage(connection, smsFormatted);
							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for CT.1"); // 1
																					// ->
																					// Implies
																					// single
																					// parameter
																					// reported
																					// by
																					// user

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else if (msg.length == 2) {

							if (msg[0].equals("OFF") && msg[1].equals("0")) {

								// Saving activity start details for office
								// activity
								this.reocrdActivityStart(connection,
										st.getStaffCode(), msg[0],
										gsm_Raw_SMS.getSmsDate(),
										gsm_Raw_SMS.getRc_id());
							} else if (isInteger(msg[0]) && !msg[1].equals("")) {

								String activityType = "ClinicalTraining";

								GSM_FFM_Activity act = cache.getActivity(
										activityType, msg[0]);

								if (act != null && this.isInteger(msg[0])) {
									// System.out.println("Activity ID is :" +
									// act.getActivityID() + " for Code:" +
									// msg[1]);

									smsFormatted.setActivityID(act
											.getActivityID());
									smsFormatted.setParam1(msg[0]);
									smsFormatted.setParam2(msg[1]);
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Posted);
									smsFormatted.setSmsDate(gsm_Raw_SMS
											.getSmsDate());
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted.setStaffDesigCode(st
											.getDesignationCode());

									SMS_Activity_Start sas = this
											.getActivityStart(
													connection,
													st.getStaffCode(),
													"START",
													FFM_ParseSMS.status_Started,
													gsm_Raw_SMS.getSmsDate());

									if (sas != null) {

										smsFormatted.setStartID(sas.getID());
										smsFormatted.setStartDate(sas
												.getStartDate());

									}

									this.updateMessage(connection, smsFormatted);

								} else {
									// Wrong message pattern
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Format for CT.2"); // 2
																						// ->
																						// Implies
																						// two
																						// parameters
																						// reported
																						// by
																						// user

									this.updateWrongMessage(connection,
											smsFormatted);

								}

							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for CT.2"); // 2
																					// ->
																					// Implies
																					// two
																					// parameters
																					// reported
																					// by
																					// user

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else if (msg.length == 3 || msg.length == 4) {

							// Checking again if provider name is more than one
							// word

							if (isInteger(msg[0]) && !msg[1].equals("")) {

								String activityType = "ClinicalTraining";

								GSM_FFM_Activity act = cache.getActivity(
										activityType, msg[0]);

								if (act != null && this.isInteger(msg[0])) {
									// System.out.println("Activity ID is :" +
									// act.getActivityID() + " for Code:" +
									// msg[1]);

									String provider_name = "";

									if (msg.length == 4) {
										if (!msg[1].equals("")
												&& !msg[2].equals("")
												&& !msg[3].equals(""))
											provider_name = msg[1] + " "
													+ msg[2] + " " + msg[3];
									}

									if (msg.length == 3) {
										if (!msg[1].equals("")
												&& !msg[2].equals(""))
											provider_name = msg[1] + " "
													+ msg[2];
									}

									smsFormatted.setActivityID(act
											.getActivityID());
									smsFormatted.setParam1(msg[0]);
									smsFormatted.setParam2(provider_name);
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Posted);
									smsFormatted.setSmsDate(gsm_Raw_SMS
											.getSmsDate());
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted.setStaffDesigCode(st
											.getDesignationCode());

									SMS_Activity_Start sas = this
											.getActivityStart(
													connection,
													st.getStaffCode(),
													"START",
													FFM_ParseSMS.status_Started,
													gsm_Raw_SMS.getSmsDate());

									if (sas != null) {

										smsFormatted.setStartID(sas.getID());
										smsFormatted.setStartDate(sas
												.getStartDate());

									}

									this.updateMessage(connection, smsFormatted);

								} else {
									// Wrong message pattern
									smsFormatted
											.setStatus(FFM_ParseSMS.status_Invalid);
									smsFormatted
											.setRcID(gsm_Raw_SMS.getRc_id());
									smsFormatted
											.setRcRemarks("Invalid Format for CT.34"); // 34
																						// ->
																						// Implies
																						// 3
																						// or
																						// 4
																						// parameters
																						// reported
																						// by
																						// user

									this.updateWrongMessage(connection,
											smsFormatted);

								}

							} else {
								smsFormatted
										.setStatus(FFM_ParseSMS.status_Invalid);
								smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
								smsFormatted
										.setRcRemarks("Invalid Format for CT.34"); // 34
																					// ->
																					// Implies
																					// 3
																					// or
																					// 4
																					// parameters
																					// reported
																					// by
																					// user

								this.updateWrongMessage(connection,
										smsFormatted);
							}

						} else {
							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks("Invalid Format for CT");

							this.updateWrongMessage(connection, smsFormatted);
						}

					} else if (staffType.equals("MIO")) {

						String t_msg = gsm_Raw_SMS.getSmsText();
						t_msg = t_msg.replaceAll("\\.\\s", "\\.");
						t_msg = t_msg.replaceAll("\\sWW\\s", " WW\\.");

						System.out.println(t_msg);

						String msg2[] = t_msg.split(" ");
						// rawSms.setSmsText(rawDataList.getString("rc_sms_text").replaceAll("\\s+",
						// " ").trim().toUpperCase())

						String mpv_code = "";
						String work_with = "";
						boolean insert_flag = false;
						String error_msg = "";

						if (msg2.length >= 2) {

							if (cache.isProvider(msg2[0])) {
								mpv_code = msg2[0];
								insert_flag = true;
							} else {
								insert_flag = false;
								error_msg = "Wrong Provider Code";
							}

							// Check if previous condition was true, then
							// proceed with checking the work with staff
							// it is not matching for valid staff id
							if (insert_flag == true
									&& msg2[1].substring(0, 2).equals("WW")) {

								work_with = msg2[1].substring(3,
										msg2[1].length()); // Get Worker Code
								insert_flag = true; // Set the flag to true

								// If Staff Code is not valid, then mark message
								// as invalid
								if (work_with.length() >= 10) {
									insert_flag = false;
									error_msg = "Invalid Staff Code";
								}

							} else {
								insert_flag = false;
								if (error_msg.equals(""))
									error_msg = "Work With not available";
							}
						}

						// If there is no error message
						if (insert_flag == true) {

							SimpleDateFormat format = new SimpleDateFormat(
									"dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

							smsFormatted.setSmsDate(gsm_Raw_SMS.getSmsDate());
							smsFormatted.setStatus(FFM_ParseSMS.status_Posted);

							Statement statement = connection.createStatement(
									ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
							String insert = "BEGIN INSERT INTO SFM_PHARMA_VISIT (VISIT_ID, WORKER_CODE, PROVIDER_CODE, WW_CODE,SMS_DATE,RC_ID,TRANSACTION_DATE, STATUS) "
									+ "VALUES (NULL,'"
									+ smsFormatted.getStaffCode()
									+ "', '"
									+ mpv_code
									+ "', '"
									+ work_with
									+ "', "
									+ "to_date('"
									+ format.format(smsFormatted.getSmsDate())
											.toUpperCase()
									+ "','DD-MON-YYYY HH24:MI:SS'), '"
									+ gsm_Raw_SMS.getRc_id()
									+ "', "
									+ "trunc(sysdate),'"
									+ FFM_ParseSMS.status_Posted
									+ "') RETURNING VISIT_ID INTO ?; END;";

							CallableStatement ps = connection
									.prepareCall(insert);
							System.out.println(insert);

							// Get the last record Insert ID
							long insert_id = -1; // Will keep the last records
													// insterted id
							// System.out.println(insert_id);
							ps.registerOutParameter(1, Types.NUMERIC);
							int affectedRows = ps.executeUpdate();

							insert_id = ps.getInt(1);
							// System.out.println("Affected Rows: " +
							// affectedRows + " Inserted id:" + insert_id);

							// Insert products for this visit
							for (int i = 2; i < msg2.length; i++) {

								String product[] = msg2[i].split("\\.");
								try {

									System.out.println(msg2[i] + ": "
											+ product.length);

									if (product.length == 2) {

										if (!this.isInteger(product[1])) {
											product[1] = "0";
										}

										String insert_product = "INSERT INTO SFM_PHARMA_VISIT_PRODUCTS (VISIT_ID, PRODUCT_CODE, QUANTITY, TRANSACTION_DATE, STATUS, RC_ID) "
												+ "VALUES ('"
												+ insert_id
												+ "', '"
												+ product[0]
												+ "', '"
												+ product[1]
												+ "', "
												+ "trunc(sysdate),'"
												+ "Posted"
												+ "', '"
												+ gsm_Raw_SMS.getRc_id()
												+ "'"
												+ ")";

										System.out.println(insert_product);
										statement.executeUpdate(insert_product);
									}

								} catch (Exception ex) {
									System.out
											.println("Error Inserting Product.FFM_ParseSMS: "
													+ msg2[i]);
									ex.printStackTrace();
								}

							} // End For

							// Once all the records are inserted sucessfully,
							// mark this record as posted
							String update = "UPDATE GSM_RECIEVING_DATA set RC_STAFF_CODE = '"
									+ smsFormatted.getStaffCode()
									+ "', RC_STATUS = '"
									+ FFM_ParseSMS.status_Posted
									+ "' where RC_ID = "
									+ gsm_Raw_SMS.getRc_id();
							// System.out.println(update);
							// Uncomment the execution below when complete
							statement.executeUpdate(update);

						} // End if there is error
						else {
							// If Error then mark original message as invalid
							// and update in GSM_RECIEVING_DATA

							smsFormatted.setStatus(FFM_ParseSMS.status_Invalid);
							smsFormatted.setRcID(gsm_Raw_SMS.getRc_id());
							smsFormatted.setRcRemarks(error_msg);

							this.updateWrongMessage(connection, smsFormatted);

						}

						// Once all the data is inserted then commit
						connection.commit();

					} else {
						// Raise Exception here
					}
				} catch (Exception ex) {
					System.out.println("Parse RawSMS Individual item: " + "");
					ex.printStackTrace();
				}

			}// End For

		} catch (Exception ex) {
			System.out.println("Parse RawSMS List: " + "");
			ex.printStackTrace();
		}

	}// End method parseRawSMS();

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	private boolean updateMessage(Connection connection,
			SMS_Formatted smsFormatted) {

		Statement statement = null;

		try {

			statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			SimpleDateFormat format = new SimpleDateFormat(
					"dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

			String insert = "INSERT INTO SMS_SMS_ACTIVITY_RESPONSE_HS (STAFF_CODE, ACTIVITY_ID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, STATUS, SMS_DATE, RC_ID, STAFF_DESIGNATION_CODE,TDATE,START_ID,START_DATE) "
					+ " VALUES (" + "'"
					+ smsFormatted.getStaffCode()
					+ "', '"
					+ smsFormatted.getActivityID()
					+ "', '"
					+ smsFormatted.getParam1()
					+ "', '"
					+ smsFormatted.getParam2()
					+ "', '"
					+ smsFormatted.getParam3()
					+ "', '"
					+ smsFormatted.getParam4()
					+ "', '"
					+ smsFormatted.getParam5()
					+ "', '"
					+ smsFormatted.getStatus()
					+ "', "
					+ "to_date('"
					+ format.format(smsFormatted.getSmsDate()).toUpperCase()
					+ "','DD-MON-YYYY HH24:MI:SS'), '"
					+ smsFormatted.getRcID()
					+ "', '"
					+ smsFormatted.getStaffDesigCode()
					+ "', trunc(sysdate),'"
					+ smsFormatted.getStartID()
					+ "', "
					+ "to_date('"
					+ format.format(smsFormatted.getStartDate()).toUpperCase()
					+ "','DD-MON-YYYY HH24:MI:SS') " + ")";

			// System.out.println(insert);

			statement.executeUpdate(insert);
			// System.out.println(insert);

			/**
			 * Update the GSM_RECIEVING_DATA status as posted
			 */
			String update = "UPDATE GSM_RECIEVING_DATA set RC_STAFF_CODE = '"
					+ smsFormatted.getStaffCode() + "', RC_STATUS = '"
					+ smsFormatted.getStatus() + "' where RC_ID = "
					+ smsFormatted.getRcID();
			// System.out.println(update);
			statement.executeUpdate(update);

			// If we have a valid Start ID then set that record in
			// table:SMS_ACTIVITY_START as POSTED
			if (smsFormatted.getStartID() > 0) {
				update = "UPDATE SMS_ACTIVITY_START set status = '"
						+ FFM_ParseSMS.status_Posted + "' where ID = "
						+ smsFormatted.getStartID();
				statement.executeUpdate(update);
			}

			// System.out.println(update);
			System.out.println("Processed: " + smsFormatted.getRcID());

			statement.executeUpdate(update);
			connection.commit();

			return true;
			// statement.close();

		} catch (Exception ex) {
			System.out.println("Saving RawSMS Individual item: " + "");
			ex.printStackTrace();

			try {
				if (statement != null)
					connection.rollback();
			} catch (Exception exc) {
				System.out
						.println("Through Catch Block RawSMS Individual item: "
								+ "");
				exc.printStackTrace();
			}
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception exc) {
				System.out
						.println("Through Finall Block RawSMS Individual item: "
								+ "");
				exc.printStackTrace();
			}
		}

		return false;
	}// End method updateMessage

	private boolean reocrdActivityStart(Connection connection,
			String staffCode, String activityDetail, Date activityDate,
			int rc_id) {

		Statement statement = null;

		try {

			SimpleDateFormat format = new SimpleDateFormat(
					"dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

			// System.out.println(format.format(activityDate).toUpperCase());

			statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String insert = "INSERT INTO SMS_ACTIVITY_START (STAFF_CODE,ACTIVITY_DETAIL,START_DATE,STATUS,RC_ID) "
					+ "VALUES ('"
					+ staffCode
					+ "', '"
					+ activityDetail
					+ "', "
					+ "to_date('"
					+ format.format(activityDate)
					+ "','DD-MON-YYYY HH24:MI:SS'), "
					// + format.format(activityDate).toUpperCase()
					+ "'"
					+ FFM_ParseSMS.status_Started
					+ "',"
					+ " '"
					+ rc_id
					+ "')";

			// System.out.println(insert);

			statement.executeUpdate(insert);

			String update = "UPDATE GSM_RECIEVING_DATA set RC_STAFF_CODE = '"
					+ staffCode + "', RC_STATUS = '"
					+ FFM_ParseSMS.status_Posted + "' where RC_ID = " + rc_id;
			// System.out.println(update);
			statement.executeUpdate(update);

			connection.commit();
			return true;
		}

		catch (Exception ex) {
			System.out.println("Saving Activity Start : " + "");
			ex.printStackTrace();
		}

		return false;
	}

	@SuppressWarnings("finally")
	private boolean updateWrongMessage(Connection connection,
			SMS_Formatted smsFormatted) {

		Statement statement = null;

		try {

			statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String update = "UPDATE GSM_RECIEVING_DATA set RC_STAFF_CODE = '"
					+ smsFormatted.getStaffCode() + "', RC_STATUS = '"
					+ smsFormatted.getStatus() + "', RC_REMARKS = '"
					+ smsFormatted.getRcRemarks() + "' where RC_ID = "
					+ smsFormatted.getRcID();

			// System.out.println(update);

			statement.executeUpdate(update);
			connection.commit();
			return true;

		} catch (Exception ex) {
			System.out.println("Saving WrongSMS Individual item: " + "");
			ex.printStackTrace();

			try {
				if (statement != null)
					connection.rollback();
			} catch (Exception exc) {
				System.out
						.println("Through Catch Block WrongSMS Individual item: "
								+ "");
				exc.printStackTrace();
			}
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception exc) {
				System.out
						.println("Through Finall Block WrongSMS Individual item: "
								+ "");
				exc.printStackTrace();
			}

			return false; // By Default Return false
		}// End Finally

	}// End method updateWrongMessage

	public void printRawSMS() {

		try {

			Iterator<GSM_Raw_SMS> iterator = this.rawSMSList.iterator();

			while (iterator.hasNext()) {

				GSM_Raw_SMS rsms = iterator.next();

				System.out.println(rsms.toString());

			}

		} catch (Exception e) {
			System.out.println("Print Raw SMS.FFM_ParseSMS: " + "");
			e.printStackTrace();
		}

	}

}
