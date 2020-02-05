package com.delivery.controller.command;


public class TextConstants {

    public static class Parameters {
        public static final String SESSION_LOCALE_PARAMETER = "sessionLocale";
        public static final String LANG_PARAMETER = "lang";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String CONFIRM_PASSWORD = "confirmPassword";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String LOGGED_USERS = "loggedUsers";
        public static final String CURRENT_PAGE = "currentPage";
        public static final String NO_OF_PAGES = "noOfPages";
        public static final String REPORTS = "reports";
        public static final String REPORTS_TO_CHANGE = "reportsToChange";
        public static final String ITEMS = "items";
        public static final String PERSONS = "persons";
        public static final String COMPANY_NAME = "companyName";
        public static final String TAXPAYER_CODE = "taxpayerCode";
        public static final String CONTENT = "content";
        public static final String ID_REPORT_TO_CHANGE = "idReportToChange";
        public static final String ID_ITEM = "idItem";
        public static final String ID_PERSON = "idPerson";
        public static final String QUANTITY = "quantity";

        public static final String ID_REPORT = "idReport";
        public static final String IS_ACCEPTED = "isAccepted";
        public static final String SHOULD_BE_CHANGED = "shouldBeChanged";
        public static final String INSPECTOR_COMMENT = "inspectorComment";
    }


    public static class Routes {
        public static final String REDIRECT = "redirect@";
        public static final String EMPTY_STRING = "";
        public static final String LOGIN_FAIL_INVALID_INPUT = "/WEB-INF/common/login.jsp?dataInvalid=true";
        public static final String TO_PERSONAL_CABINET = "/app/personal-cabinet";
        public static final String MULTILOGIN_ERROR = "/WEB-INF/common/error/multilogin.jsp";
        public static final String INVALID_SESSION_ERROR = "/WEB-INF/common/error/invalidSession.jsp";
        public static final String ACCESS_FORBIDDEN_403 = "/WEB-INF/common/error/403.jsp";

        public static final String BASE = "/WEB-INF/common/base.jsp";
        public static final String USER_NOT_EXIST = "/WEB-INF/common/login.jsp?userExist=false";

        public static final String REGISTRATION_FAIL_INVALID_DATA = "/WEB-INF/common/registration.jsp?dataInvalid=true";
        public static final String REGISTRATION_FAIL_PASSWORDS_DIFFERENT = "/WEB-INF/common/registration.jsp?passwordsDifferent=true";
        public static final String REGISTRATION_FAIL_USER_EXIST = "/WEB-INF/common/registration.jsp?alreadyExist=true";
        public static final String REGISTRATION_SUCCESS = "/WEB-INF/common/registration.jsp?success=true";


        public static final String TO_SHOW_REPORTS = "/WEB-INF/client/show-reports.jsp";
        public static final String TO_SUBMIT_REPORT = "/WEB-INF/client/submit-report.jsp";
        public static final String TO_CHECK_REPORT = "/WEB-INF/inspector/check-report.jsp";
        public static final String TO_EDIT_REPORT = "/WEB-INF/client/edit-report.jsp";
        public static final String REDIRECT_TO_EDIT_REPORT = "/app/edit-report";
        public static final String TO_SET_TAXABLE_ITEMS = "/WEB-INF/inspector/set-taxable.jsp";
        public static final String TO_SUBMIT_COMPLAINT = "/WEB-INF/client/submit-complaint.jsp";

        public static final String TO_HOME = "/WEB-INF/common/welcome.jsp";
        public static final String TO_LOGIN = "/WEB-INF/common/login.jsp";
        public static final String TO_REGISTRATION = "/WEB-INF/common/registration.jsp";
    }

   public static class CommandPaths {
        //account
        public static final String REGISTRATION = "registration";
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String PERSONAL_CABINET = "personal-cabinet";

         //directions
        public static final String HOME = "home";
        public static final String REG_ME = "reg-me";
        public static final String LOG_ME = "log-me";
        public static final String MAKE_COMPLAINT = "make-complaint";
        public static final String MAKE_REPORT = "make-report";
        public static final String CHECK_REPORT = "check-report";
        public static final String SET_TAXABLE = "set-taxable";
        public static final String EDIT_REPORTS = "edit-report";


          //actions
        public static final String SUBMIT_REPORT = "submit-report";
        public static final String SUBMIT_COMPLAINT = "submit-complaint";
        public static final String SHOW_REPORTS = "show-reports";
        public static final String SUBMIT_EDIT_REPORT = "submit-edit-report";
        public static final String SUBMIT_CHECKING_REPORT = "submit-checking-report";
        public static final String SUBMIT_SET_TAXABLE = "submit-set-taxable";

         //servlet
        public static final String APPLICATION_PATH_REGEX = ".*/app/";
        public static final String DEFAULT_PATH = "app/home";
    }

}
