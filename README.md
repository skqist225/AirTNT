# AirTNT

Air TNT web project inspired by Airbnb

@AboutProject
class ProjectVersionInfo {

public static final int Java = 11;

public static final String SpringBoot = "2.54";

public static final String Maven = "3.8";

public static final float TomCat = 9.0;
}

///Change countries table character set
ALTER TABLE airtnt.countries CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

///Drop foreign key check
SET FOREIGN_KEY_CHECKS = 0;
