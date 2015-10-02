package br.com.ciandt.helio.worldwonders.infrastructure;

public class Constants {

    //public static final String HTTP_PROTOCOL = "http";
    public static final String HTTP_PROTOCOL = "http://";

    public static interface Entity {

        public static interface User {

            public static final String TEXT_COUNTRY_BRASIL = "Brasil";
            public static final String TEXT_COUNTRY_CANADA = "Canadá";
            public static final String TEXT_COUNTRY_CHINA = "China";
            public static final String INDICATOR_GENDER_MALE = "M";
            public static final String INDICATOR_GENDER_FEMALE = "F";
            public static final int INDICATOR_NOTIFICATION_ON = 1;
            public static final int INDICATOR_NOTIFICATION_OFF = 0;
        }
    }

    public static interface Bundle {

        public static final String BUNDLE_USER_NAME = "user_name";
        public static final String BUNDLE_USER_EMAIL = "user_email";
        public static final String BUNDLE_USER_PASSWORD = "user_password";
    }

    public static interface RequestCode {

        public static final int MAIN_ACTIVITY = 0;
        public static final int LOGIN_ACTIVITY = 1;
        public static final int REGISTER_ACTIVITY = 2;
    }

    public static interface Integrator {

        public static interface WorldWondersApi {

            // apiary
            //public static final String HOST = "worldwondersapi.apiary-mock.com";
            //public static final String HOST = "private-anon-406b81e0d-worldwondersapi.apiary-mock.com";
            //public static final String HOST = "private-5680b-worldwondersapi.apiary-mock.com";
            //public static final String WORLD_WONDERS_LIST = "/api/v1/worldwonders";

            // parse
            public static final String API_REST_KEY = "ZdZ40VdqX1SJyUoGMvJOYWAnw53zyhjsFlXfF2Rh";
            public static final String API_APPLICTION_ID = "xf4nl2pBvNEkGADXxuN8yLzRchlvTqBoOWV2oCg0";
            public static final String HOST_PARSE = "https://api.parse.com/1";

            // parse endpoints
            //public static final String GET_WONDERS_LIST = HOST_PARSE + "/classes/Wonders";
            //public static final String LOGOUT_USER = HOST + "/logout";
            //public static final String LOGIN_USER = HOST + "/login";
            //public static final String REGISTER_USER = HOST + "/users";

            // parse endpoints (url)
            public static final String GET_WONDERS_LIST = "/classes/Wonders";
        }
    }

    public static interface Service {

        public static interface Tag {

            public static final String COMMAND = "command";
            public static final String RESULT_RECEIVER = "receiver";
            public static final String ERROR_MSG = "errorMsg";
            public static final String BULK_INSERT_COUNT = "bulkInsertCount";
        }

        public static interface Status {

            public static final int FINISHED = 1;
            public static final int ERROR = 2;
        }

        public static interface SyncCommand {

            public static final int PLACE_ALL = 0;
        }

    }

}