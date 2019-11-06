package com.example.skyevent.data;

import android.provider.BaseColumns;

public final class DataContract {

    private DataContract()
    {

    }

    public static final class EventEntry implements BaseColumns
    {
        //table name event
        public final static String Table_Name="Event";

        //type: number
        public final static String ID=BaseColumns._ID;

        //
        public final static String EventName="EventName";

        //type: long
        public static final  String Time_For_Event="TimeInMillisecond";

    }

    public static final class PeopleEntry implements BaseColumns
    {
        //table name
        public static final String Table_Name="PeopleList";

        //type: Integer
        public static final String ID=BaseColumns._ID;

        //type: String
        public static final String Name="Name";

        //type: long
        public static final String Phone="Phone";
    }
    public static final class TaskEntry implements BaseColumns{
        public static final String Table_Name="TaskEntry";

        //type: Integer
        public static final String ID=BaseColumns._ID;

        //type: String
        public static final String TaskDetails="Details";

        //type: long
        public static final String TaskDate="Date";
        public static final String TaskStatus="Status";

    }

}
