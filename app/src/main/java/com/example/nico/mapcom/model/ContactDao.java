package com.example.nico.mapcom.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT".
*/
public class ContactDao extends AbstractDao<Contact, Long> {

    public static final String TABLENAME = "CONTACT";

    /**
     * Properties of entity Contact.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FirstName = new Property(1, String.class, "firstName", false, "FIRST_NAME");
        public final static Property LastName = new Property(2, String.class, "lastName", false, "LAST_NAME");
        public final static Property Society = new Property(3, String.class, "society", false, "SOCIETY");
        public final static Property PhoneNumber = new Property(4, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Address = new Property(6, String.class, "address", false, "ADDRESS");
        public final static Property Comment = new Property(7, String.class, "comment", false, "COMMENT");
        public final static Property Active = new Property(8, boolean.class, "active", false, "ACTIVE");
        public final static Property Latitude = new Property(9, double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(10, double.class, "longitude", false, "LONGITUDE");
    }

    private DaoSession daoSession;


    public ContactDao(DaoConfig config) {
        super(config);
    }
    
    public ContactDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTACT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FIRST_NAME\" TEXT," + // 1: firstName
                "\"LAST_NAME\" TEXT," + // 2: lastName
                "\"SOCIETY\" TEXT," + // 3: society
                "\"PHONE_NUMBER\" TEXT," + // 4: phoneNumber
                "\"EMAIL\" TEXT," + // 5: email
                "\"ADDRESS\" TEXT," + // 6: address
                "\"COMMENT\" TEXT," + // 7: comment
                "\"ACTIVE\" INTEGER NOT NULL ," + // 8: active
                "\"LATITUDE\" REAL NOT NULL ," + // 9: latitude
                "\"LONGITUDE\" REAL NOT NULL );"); // 10: longitude
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTACT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String firstName = entity.getFirstName();
        if (firstName != null) {
            stmt.bindString(2, firstName);
        }
 
        String lastName = entity.getLastName();
        if (lastName != null) {
            stmt.bindString(3, lastName);
        }
 
        String society = entity.getSociety();
        if (society != null) {
            stmt.bindString(4, society);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(5, phoneNumber);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(7, address);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(8, comment);
        }
        stmt.bindLong(9, entity.getActive() ? 1L: 0L);
        stmt.bindDouble(10, entity.getLatitude());
        stmt.bindDouble(11, entity.getLongitude());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String firstName = entity.getFirstName();
        if (firstName != null) {
            stmt.bindString(2, firstName);
        }
 
        String lastName = entity.getLastName();
        if (lastName != null) {
            stmt.bindString(3, lastName);
        }
 
        String society = entity.getSociety();
        if (society != null) {
            stmt.bindString(4, society);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(5, phoneNumber);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(7, address);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(8, comment);
        }
        stmt.bindLong(9, entity.getActive() ? 1L: 0L);
        stmt.bindDouble(10, entity.getLatitude());
        stmt.bindDouble(11, entity.getLongitude());
    }

    @Override
    protected final void attachEntity(Contact entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Contact readEntity(Cursor cursor, int offset) {
        Contact entity = new Contact( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // firstName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // society
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phoneNumber
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // address
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // comment
            cursor.getShort(offset + 8) != 0, // active
            cursor.getDouble(offset + 9), // latitude
            cursor.getDouble(offset + 10) // longitude
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Contact entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFirstName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSociety(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhoneNumber(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAddress(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setComment(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setActive(cursor.getShort(offset + 8) != 0);
        entity.setLatitude(cursor.getDouble(offset + 9));
        entity.setLongitude(cursor.getDouble(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Contact entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Contact entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Contact entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
