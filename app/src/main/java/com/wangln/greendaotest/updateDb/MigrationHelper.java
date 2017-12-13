//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wangln.greendaotest.updateDb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.wangln.greendaotest.dao.DaoMaster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.internal.DaoConfig;

public final class MigrationHelper {
    public static boolean DEBUG = true;
    private static String TAG = "wang";

    public MigrationHelper() {
    }

    public static void migrate(SQLiteDatabase db, Class... daoClasses) {
        StandardDatabase database = new StandardDatabase(db);
        //如果表不存在需要创建，不然有新表的时候会报错。
        DaoMaster.createAllTables(database,true);
        if(DEBUG) {
            Log.d(TAG, "【Database Version】" + db.getVersion());
            Log.d(TAG, "【Generate temp table】start");
        }

        generateTempTables(database, daoClasses);
        if(DEBUG) {
            Log.d(TAG, "【Generate temp table】complete");
        }

        dropAllTables(database, true, daoClasses);
        createAllTables(database, false, daoClasses);
        if(DEBUG) {
            Log.d(TAG, "【Restore data】start");
        }

        restoreData(database, daoClasses);
        if(DEBUG) {
            Log.d(TAG, "【Restore data】complete");
        }

    }
    private static void generateTempTables(Database db, Class... daoClasses) {
        for(int i = 0; i < daoClasses.length; ++i) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            StringBuilder insertTableStringBuilder = new StringBuilder();
            insertTableStringBuilder.append("CREATE TEMPORARY TABLE ").append(tempTableName);
            insertTableStringBuilder.append(" AS SELECT * FROM ").append(tableName).append(";");
            db.execSQL(insertTableStringBuilder.toString());
            if(DEBUG) {
                Log.d(TAG, "【Table】" + tableName + "\n ---Columns-->" + getColumnsStr(daoConfig));
                Log.d(TAG, "【Generate temp table】" + tempTableName);
            }
        }

    }

    private static String getColumnsStr(DaoConfig daoConfig) {
        if(daoConfig == null) {
            return "no columns";
        } else {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < daoConfig.allColumns.length; ++i) {
                builder.append(daoConfig.allColumns[i]);
                builder.append(",");
            }

            if(builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }

            return builder.toString();
        }
    }

    private static void dropAllTables(Database db, boolean ifExists, @NonNull Class... daoClasses) {
        reflectMethod(db, "dropTable", ifExists, daoClasses);
        if(DEBUG) {
            Log.d(TAG, "【Drop all table】");
        }

    }

    private static void createAllTables(Database db, boolean ifNotExists, @NonNull Class... daoClasses) {
        reflectMethod(db, "createTable", ifNotExists, daoClasses);
        if(DEBUG) {
            Log.d(TAG, "【Create all table】");
        }

    }

    private static void reflectMethod(Database db, String methodName, boolean isExists, @NonNull Class... daoClasses) {
        if(daoClasses.length >= 1) {
            try {
                Class[] e = daoClasses;
                int var5 = daoClasses.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Class cls = e[var6];
                    Method method = cls.getDeclaredMethod(methodName, new Class[]{Database.class, Boolean.TYPE});
                    method.invoke((Object)null, new Object[]{db, Boolean.valueOf(isExists)});
                }
            } catch (NoSuchMethodException var9) {
                var9.printStackTrace();
            } catch (InvocationTargetException var10) {
                var10.printStackTrace();
            } catch (IllegalAccessException var11) {
                var11.printStackTrace();
            }

        }
    }

    private static void restoreData(Database db, Class... daoClasses) {
        for(int i = 0; i < daoClasses.length; ++i) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            List columns = getColumns(db, tempTableName);
            ArrayList properties = new ArrayList(columns.size());

            for(int dropTableStringBuilder = 0; dropTableStringBuilder < daoConfig.properties.length; ++dropTableStringBuilder) {
                String insertTableStringBuilder = daoConfig.properties[dropTableStringBuilder].columnName;
                if(columns.contains(insertTableStringBuilder)) {
                    properties.add(insertTableStringBuilder);
                }
            }

            if(properties.size() > 0) {
                String var10 = TextUtils.join(",", properties);
                StringBuilder var12 = new StringBuilder();
                var12.append("INSERT INTO ").append(tableName).append(" (");
                var12.append(var10);
                var12.append(") SELECT ");
                var12.append(var10);
                var12.append(" FROM ").append(tempTableName).append(";");
                db.execSQL(var12.toString());
                if(DEBUG) {
                    Log.d(TAG, "【Restore data】 to " + tableName);
                }
            }

            StringBuilder var11 = new StringBuilder();
            var11.append("DROP TABLE ").append(tempTableName);
            db.execSQL(var11.toString());
            if(DEBUG) {
                Log.d(TAG, "【Drop temp table】" + tempTableName);
            }
        }

    }

    private static List<String> getColumns(Database db, String tableName) {
        Object columns = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 0", (String[])null);
            if(null != cursor && cursor.getColumnCount() > 0) {
                columns = Arrays.asList(cursor.getColumnNames());
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            if(null == columns) {
                columns = new ArrayList();
            }

        }

        return (List)columns;
    }
}
