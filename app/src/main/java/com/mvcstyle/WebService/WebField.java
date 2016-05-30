package com.mvcstyle.WebService;

public class WebField {

//       public final static String BASE_IP = "http://192.168.1.70/RequestandReceiveApp/api/Values/";
    public final static String BASE_IP = "http://180.211.97.84/RequestandReceiveApp/api/Values/";

    //CategoryList
    public final static class Category {
        public final static String WEB_FIELD_ID = "Id";
        public final static String WEB_FIELD_NAME = "CategoryName";
    }

    //1
    public final static class REGISTER_USER {
        public final static String REQUEST_USER_NAME = "userName";
        public final static String REQUEST_FIRST_NAME = "firstName";
        public final static String REQUEST_LAST_NAME = "lastName";
        public final static String REQUEST_EMAIL = "emailId";
        public final static String REQUEST_ADDRESS = "address";
        public final static String REQUEST_PASSWORD = "password";
        public final static String REQUEST_MOBILE = "mobileNumber";
        public final static String REQUEST_GENDER = "gender";
        public final static String REQUEST_SPECIFICATION = "specification";
        public final static String REQUEST_CERTIFICATION = "certification";
        public final static String REQUEST_CATERGORY_ID = "categoryId";
        public final static String REQUEST_OFFICE_ADDRESS = "officeAddress";
        public final static String REQUEST_PROFILE_PIC = "profileImage";
        public final static String REQUEST_LATITUDE = "latitude";
        public final static String REQUEST_LONGITUDE = "longitude";
        public final static String REQUEST_DEVICE_ID = "deviceId";
        public final static String REQUEST_DEVICE_TYPE = "deviceType";
        public final static String REQUEST_USER_TYPE = "userType";

        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_MOBILE_NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";
        public final static String RESPONSE_CATERGORY_ID = "categoryId";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //2
    public final static class LOGIN_USER {
        public final static String REQUEST_USERNAME = "userName";
        public final static String REQUEST_PASSWORD = "password";
        public final static String REQUEST_DEVICE_ID = "deviceId";
        public final static String REQUEST_DEVICE_TYPE = "deviceType";
        public final static String REQUEST_USER_TYPE = "userType";

        public final static String RESPONSE_USER_DETAIL = "userDetail";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_MOBILE = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //3
    public final static class FORGOT_PASSWORD {
        public final static String REQUEST_EMAIL = "emailId";
        public final static String REQUEST_USERTYPE = "userType";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //4
    public final static class CATEGORIES_LIST {
        public final static String RESPONSE_CATEGORIES_LIST = "CategoriesList";

        public final static String RESPONSE_ID = "Id";
        public final static String RESPONSE_CATEGORY_NAME = "CategoryName";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //5
    public final static class PROFESSIONAL_LIST {
        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_DISTANCE_VALUE = "distanceValue";
        public final static String REQUEST_DISTANCE_TYPE = "distanceType";
        public final static String REQUEST_USER_TYPE = "userType";
        public final static String REQUEST_CATERGORY_ID = "categoryId";


        public final static String RESPONSE_PROFESSIONAL_LIST = "ProfessionalList";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_MOBILE_NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";
        public final static String RESPONSE_DISTANCE = "Distance";
        public final static String RESPONSE_LATITUDE = "latitude";
        public final static String RESPONSE_LONGITUDE = "longitude";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //6
    public final static class GET_PROFESSIONAL_PROFILE {
        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";

        public final static String RESPONSE_PROFESSIONAL_LIST = "ProfessionalList";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_CATEGORY_ID = "categoryId";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_MOBILE_NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //7
    public final static class GET_HISTORY_LIST {

        public final static String REQUEST_BOOKING_ID = "bookingId";
        public final static String REQUEST_STATUS = "status";

        public final static String RESPONSE_JOB_TITLE = "jobTitle";
        public final static String RESPONCE_MOBILE_NO = "mobileNumber";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_DESCRIPTION = "Description";
        public final static String RESPONCE_BOOKING_ID = "bookingId";
        public final static String RESPONCE_PROFESSIONAL_MOBILE_NUMBER = "professionalMobileNo";
        public final static String RESPONCE_QUOTE_AMOUNT = "quoteAmount";
        public final static String RESPONCE_APPOINT_DATE = "appointDate";
        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_USERNAME = "userName";

        //        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //7.1
    public final static class ADD_CATEGORIES {

        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_CATEGORY_IDS = "CategoryIds";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //7.2
    public final static class SEND_MESSAGE {
        public final static String REQUEST_MOBILE_FROM = "messageFrom";
        public final static String REQUEST_MESSAGE_TO = "messageTo";
        public final static String REQUEST_USER_TYPE = "userType";
        public final static String REQUEST_MESSAGE_TEXT = "messageText";

        public final static String RESPONSE_MESSAGE_ID = "messageId";
        public final static String RESPONSE_STATUS = "status";

    }

    //8
    public final static class GET_NOTIFICATION_LIST {

        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_CATEGORY_ID = "categoryId";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //9
    public final static class GET_USER_DETAILS {

        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_USER_TYPE = "userType";

        //        public final static String RESPONSE_REQUEST_LIST = "RequestedList";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_CATEGORY = "categoryId";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";
        public final static String RESPONSE_MOBILE_NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //9.1
    public final static class GET_QUOTE_DETAILS {

        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_USER_TYPE = "userType";

        //        public final static String RESPONSE_REQUEST_LIST = "RequestedList";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_PROFESSINAL_NUMBER = "professionalMobileNo";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_DESCRIPTION = "description";
        public final static String RESPONSE_QUOTE_AMOUNT = "quoteAmount";
        public final static String RESPONSE_APPOIMNT_DATE = "appointDate";
        public final static String RESPONSE_JOB_TITLE = "jobTitle";
        public final static String RESPONSE_BOOKING_ID = "bookingId";

        public final static String RESPONSE_CONTACT_ADDRESS = "contactadd";


        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //10
    public final static class EDIT_PROFILE {
        public final static String REQUEST_FIRST_NAME = "firstName";
        public final static String REQUEST_LAST_NAME = "lastName";
        public final static String REQUEST_ADDRESS = "address";
        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_GENDER = "gender";
        public final static String REQUEST_PROFILE_IMAGE = "profileImage";
        public final static String REQUEST_LATITUDE = "latitude";
        public final static String REQUEST_LONGITUDE = "longitude";
        public final static String REQUEST_SPECIFICATION = "specification";
        public final static String REQUEST_CERTIFICATION = "certification";
        public final static String REQUEST_OFFICE_ADD = "officeAddress";
        public final static String REQUEST_DEVICE_ID = "deviceId";
        public final static String REQUEST_DEVICE_TYPE = "deviceType";
        public final static String REQUEST_USER_TYPE = "userType";

        public final static String RESPONSE_MESSAGE_ID = "userDetail";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_EMAIL_ID = "emailId";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_MOBILE_NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_SPECIFICATION = "specification";
        public final static String RESPONSE_CERTIFICATION = "certification";
        public final static String RESPONSE_OFFICE_ADDRESS = "officeAddress";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //11
    public final static class CHANGE_PASSWORD {

        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_OLD_PASSWORD = "oldPassword";
        public final static String REQUEST_NEW_PASSWORD = "newPassword";
        public final static String REQUEST_USER_TYPE = "userType";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //12
    public final static class GET_JOB_TITLE_LIST {

        public final static String REQUEST_MOBILE_NO = "mobileNumber";

        public final static String RESPONSE_JOB_TITLE_LIST = "JobTitleList";

        public final static String RESPONSE_JOB_TITLE = "jobTitle";
        public final static String RESPONSE_BOOKING_ID = "bookingId";
        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //13
    public final static class INSERT_USER_PACKAGE {

        public final static String REQUEST_PROFESSIONAL_MOBILE_NO = "professionalMobileNo";
        public final static String REQUEST_JOBS = "jobs";
        public final static String REQUEST_AMOUNT = "amount";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //14
    public final static class BOOKING_QUOTE_JOB_TITLE {

        public final static String REQUEST_CATEGORY_ID = "categoryId";
        public final static String REQUEST_JOB_TITLE = "jobTitle";
        public final static String REQUEST_MOBILE_NO = "mobileNumber";

        public final static String RESPONCE_BOOKING_ID = "bookingId";
        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //15
    public final static class BOOKING_QUOTE_DETAILS {

        public final static String REQUEST_ADDRESS = "address";
        public final static String REQUEST_DESCRIPTION = "description";
        public final static String REQUEST_BOOKING_ID = "bookingId";
        public final static String REQUEST_PROFESSIONAL_MOBILE_NO = "professionalMobileNo";
        public final static String REQUEST_QUOTE_AMOUNT = "quoteAmount";
        public final static String REQUEST_APPOINT_DATE = "appointDate";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //16
    public final static class BOOKING_QUOTE {

        public final static String REQUEST_USER_ID = "userId";
        public final static String REQUEST_PROFESSIONAL_ID = "professionalId";
        public final static String REQUEST_BOOKING_ID = "bookingId";
        public final static String REQUEST_PROFESSIONAL_MOBILE_NO = "professionalMobileNo";
        public final static String REQUEST_QUOTE_AMOUNT = "quoteAmount";

//        public final static String REQUEST_MOBILE_NO = "mobileNumber";
//        public final static String REQUEST_ADDRESS = "address";
//        public final static String REQUEST_DESCRIPTION = "description";
//        public final static String REQUEST_BOOK_DATE = "bookDate";
//        public final static String REQUEST_APPOINT_DATE = "appointDate";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //pro_4
    public final static class GET_PROFESSIONAL_REQUEST_DATA {

        //        public final static String REQUEST_MOBILE_NUMBER = "mobileNumber";
        public final static String REQUEST_PROFESSIONAL_NUMBER = "professionalMobileNo";
        //        public final static String REQUEST_ISBEFORE = "isBefore";
//        public final static String REQUEST_PAGE_SIZE = "pageSize";
//        public final static String REQUEST_USER_TYPE = "userType";


        public final static String RESPONSE_REQUEST_LIST = "RequestedList";
        public final static String RESPONSE_USER_NAME = "userName";
        public final static String RESPONSE_FIRST_NAME = "firstName";
        public final static String RESPONSE_LAST_NAME = "lastName";
        public final static String RESPONSE_PROFESSINAL__NUMBER = "mobileNumber";
        public final static String RESPONSE_GENDER = "gender";
        public final static String RESPONSE_PROFILE_IMAGE = "profileImage";
        public final static String RESPONSE_BOOKING_ID = "bookingId";

        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

    //pro_5
    public final static class GET_HISTORY_LIST_PROFESSIONAL {

        public final static String REQUEST_PROFESSIONAL_MOBILE_NUMBER = "professionalMobileNo";
        public final static String REQUEST_STATUS = "status";

        public final static String RESPONSE_HISTORY = "History";
        public final static String RESPONCE_MOBILE_NO = "mobileNumber";
        public final static String RESPONSE_ADDRESS = "address";
        public final static String RESPONSE_DESCRIPTION = "Description";
        public final static String RESPONSE_JOB_TITLE = "jobTitle";
        public final static String RESPONCE_BOOKING_ID = "bookingId";
        public final static String RESPONCE_PROFESSIONAL_MOBILE_NUMBER = "professionalMobileNo";
        public final static String RESPONCE_QUOTE_AMOUNT = "quoteAmount";
        public final static String RESPONCE_APPOINT_DATE = "appointDate";
        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_USERNAME = "userName";

        //        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";

    }

    //pro_6
    public final static class ACCEPT_REJECT_REQUEST {

        //        public final static String REQUEST_MOBILE_NO = "mobileNumber";
        public final static String REQUEST_PROFESSIONAL_MOBILE_NO = "professionalMobileNo";
        public final static String REQUEST_BOOKING_ID = "bookingId";
        public final static String REQUEST_STATUS = "status";
//        public final static String REQUEST_TITLE = "title";


        public final static String RESPONSE_STATUS = "status";
        public final static String RESPONSE_MESSAGE = "message";
    }

}